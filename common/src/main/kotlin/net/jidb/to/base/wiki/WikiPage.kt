package net.jidb.to.base.wiki

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addOrNull
import net.jidb.to.base.extension.addSerialisableArray
import net.jidb.to.base.extension.addStringArray
import net.jidb.to.base.wiki.changelog.WikiChangelog
import net.jidb.to.base.wiki.recipe.WikiRecipes
import net.jidb.to.base.wiki.reference.WikiImage
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation

class WikiPage(val name: Component, val flavor: Component, val content: WikiContent, val subjects: List<WikiSubject>, val header: WikiImage? = null, val changelog: WikiChangelog? = null, val recipes: WikiRecipes? = null, val tags: List<ResourceLocation> = listOf()) : JsonSerialisable {

    internal lateinit var _parent: WikiArticle
    val parent get() = _parent

    override fun toJson() = JsonObject().also {
        it.add("name", Component.Serializer.toJsonTree(name))
        it.add("flavor", Component.Serializer.toJsonTree(flavor))
        it.add("content", content.toJson())
        it.addSerialisableArray("subjects", subjects.toTypedArray())
        it.addOrNull("header", header)
        it.addOrNull("changelog", changelog?.getScoped(parent))
        it.addOrNull("recipes", recipes)
        it.addStringArray("tags", tags.map(ResourceLocation::toString).toTypedArray())
    }

}
