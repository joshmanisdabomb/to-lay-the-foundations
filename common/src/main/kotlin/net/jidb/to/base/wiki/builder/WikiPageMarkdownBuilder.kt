package net.jidb.to.base.wiki.builder

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.jidb.to.base.wiki.WikiConstantLibrary
import net.jidb.to.base.wiki.WikiContent
import net.jidb.to.base.wiki.fragment.WikiCompoundFragment
import net.jidb.to.base.wiki.fragment.WikiFragment
import net.jidb.to.base.wiki.fragment.WikiTextFragment
import net.jidb.to.base.wiki.reference.WikiBasicReference
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import java.nio.file.Files
import java.nio.file.Path

class WikiPageMarkdownBuilder(val files: MutableMap<String, Path> = mutableMapOf()) : WikiPageAbstractBuilder() {

    fun setFile(file: Path, language: String = "en_us"): WikiPageMarkdownBuilder {
        this.files[language] = file
        return this
    }

    override fun getContent() = WikiContent(files.mapValues { this.createTopLevelFragment(Files.readAllLines(it.value), it.key) })

    protected fun createTopLevelFragment(lines: List<String>, language: String = "en_us") : WikiFragment {
        val sections = mutableListOf<Pair<Component, MutableList<MutableList<WikiFragment>>>>()
        for (line in lines) {
            if (line.startsWith("# ")) {
                val heading = getTranslatableCompound(mapOf(language to line.substring(2)))()
                sections.add(heading to mutableListOf(mutableListOf()))
            } else {
                if (sections.isEmpty()) {
                    sections.add(Component.translatable(WikiConstantLibrary.introduction) to mutableListOf(mutableListOf()))
                }
                val section = sections.last().second
                if (line.trim().isEmpty()) {
                    section.add(mutableListOf())
                } else {
                    section.last().add(getFragment(line, language))
                }
            }
        }

        return WikiCompoundFragment(*sections.map { section ->
            WikiCompoundFragment(*section.second.map { paragraph ->
                WikiCompoundFragment(*paragraph.toTypedArray(), paragraph = true)
            }.toTypedArray(), heading = section.first)
        }.toTypedArray())
    }

    protected fun getFragment(line: String, language: String = "en_us"): WikiCompoundFragment {
        val matches = JSON_REGEX.findAll(line)
        val fragments = mutableListOf<WikiFragment>()
        for (match in matches) {
            if (match.value.contains("{")) {
                fragments.add(createTemplateFragment(GSON.fromJson(match.value, JsonObject::class.java).asJsonObject))
            } else {
                fragments.add(WikiTextFragment(getTranslatableCompound(mapOf(language to match.value))()))
            }
        }
        return WikiCompoundFragment(*fragments.toTypedArray())
    }

    private fun createTemplateFragment(json: JsonObject, language: String = "en_us"): WikiFragment {
        return when ((json.get("template") as? JsonPrimitive)?.asString) {
            "introduction" -> createIntroductionFragment(json, language)
            else -> createReferralFragment(json, language)
        }
    }

    private fun createIntroductionFragment(json: JsonObject, language: String = "en_us"): WikiFragment {
        return WikiCompoundFragment(
            WikiTextFragment(this.name(), refer = this.subject.reference),
            WikiTextFragment(getTranslatableCompound(mapOf(language to json.get("description").asString), subject.reference.registry.path)()),
            //TODO read from page builder changelog to show "introduced in ", "reintroduced in " and "removed in ".
            WikiTextFragment(Component.translatable(WikiConstantLibrary.introduced)),
            WikiTextFragment(Component.translatable(WikiConstantLibrary.reintroduced)),
            WikiTextFragment(Component.translatable(WikiConstantLibrary.removed)),
        )
    }

    private fun createReferralFragment(json: JsonObject, language: String = "en_us"): WikiFragment {
        val text = json.get("text") as? JsonObject ?: json
        val component = if (text.has("component")) {
            Component.Serializer.fromJson(text.get("component"))!!
        } else {
            val registry = (text.get("registry") as? JsonPrimitive)?.asString?.let(ResourceLocation::tryParse) ?: subject.reference.registry
            val identifier = (text.get("identifier") as? JsonPrimitive)?.asString?.let(ResourceLocation::tryParse) ?: subject.reference.identifier
            val reference = WikiBasicReference(registry, identifier)
            Component.translatable(reference.translationKey)
        }

        val link = json.get("link")
        val destination = if (link is JsonPrimitive && link.isBoolean && !link.asBoolean) {
            null
        } else {
            val link = link as? JsonObject ?: json
            val registry = (link.get("registry") as? JsonPrimitive)?.asString?.let(ResourceLocation::tryParse) ?: subject.reference.registry
            val identifier = (link.get("identifier") as? JsonPrimitive)?.asString?.let(ResourceLocation::tryParse) ?: subject.reference.identifier
            WikiBasicReference(registry, identifier)
        }

        return WikiTextFragment(component, refer = destination)
    }

    companion object {
        private val JSON_REGEX = "\\{[^{}]*(?:\\{[^{}]*\\}[^{}]*)*\\}|[^{}]+".toRegex()
        private val GSON = GsonBuilder().setLenient().create()
    }

}