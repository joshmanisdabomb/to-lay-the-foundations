package net.jidb.to.base.wiki

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisableObject
import net.minecraft.resources.ResourceLocation

class WikiArticle(val identifier: ResourceLocation, val pages: Map<String, WikiPage>) : JsonSerialisable {

    init {
        pages.forEach { (k, v) -> v._parent = this }
    }

    override fun toJson() = JsonObject().also {
        it.addSerialisableObject("pages", pages)
    }

}