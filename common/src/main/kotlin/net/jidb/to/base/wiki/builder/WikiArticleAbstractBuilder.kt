package net.jidb.to.base.wiki.builder

import net.jidb.to.base.wiki.WikiContent
import net.jidb.to.base.wiki.WikiArticle
import net.jidb.to.base.wiki.WikiSubject
import net.jidb.to.base.wiki.reference.WikiReference
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

abstract class WikiArticleAbstractBuilder(override var identifier: ResourceLocation) : WikiArticleBuilder {

    protected lateinit var name: () -> Component
    protected lateinit var flavor: () -> Component
    val subjects = mutableListOf<WikiSubject>()

    val subject get() = subjects.minOf { it }

    protected val _translations = mutableListOf<Map<String, String>>()
    override val translations get() = _translations.toList()

    protected fun getTranslatableCompound(translations: Map<String, String>, vararg args: Any): () -> Component {
        val index = _translations.count()
        _translations.add(translations)
        return { Component.translatable(this.getTranslationKey(index), *args) }
    }

    fun setName(translation: String) = setName(mapOf("en_us" to translation))
    fun setName(translations: Map<String, String>): WikiArticleAbstractBuilder {
        name = getTranslatableCompound(translations)
        return this
    }
    fun setName(component: Component): WikiArticleAbstractBuilder {
        name = { component }
        return this
    }
    fun setName(reference: WikiReference): WikiArticleAbstractBuilder {
        name = { reference.component }
        return this
    }

    fun setFlavorText(translation: String) = setFlavorText(mapOf("en_us" to translation))
    fun setFlavorText(translations: Map<String, String>): WikiArticleAbstractBuilder {
        flavor = getTranslatableCompound(translations)
        return this
    }
    fun setFlavorText(component: Component): WikiArticleAbstractBuilder {
        flavor = { component }
        return this
    }
    fun setFlavorText(reference: WikiReference): WikiArticleAbstractBuilder {
        flavor = { reference.component }
        return this
    }

    fun addSubject(vararg subjects: WikiSubject): WikiArticleAbstractBuilder {
        this.subjects.addAll(subjects)
        return this
    }

    override fun build(translations: WikiTranslations): WikiArticle {
        val page = WikiArticle(identifier, this.name(), this.flavor(), this.getContent(), subjects)
        translations.store(this)
        return page
    }

    abstract fun getContent(): WikiContent

}