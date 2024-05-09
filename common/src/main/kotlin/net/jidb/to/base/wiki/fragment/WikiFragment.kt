package net.jidb.to.base.wiki.fragment

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable

abstract class WikiFragment : JsonSerialisable {

    abstract val type: String

    abstract fun toJson(json: JsonObject)

    override fun toJson() = JsonObject().also {
        toJson(it)
        it.addProperty("type", type)
    }

}
