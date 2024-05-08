package net.jidb.to.base.wiki

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.jidb.to.base.convenience.json.JsonSerialisable

enum class WikiChangelogType : JsonSerialisable {

    INTRODUCED,
    REINTRODUCED,
    CHANGED,
    REMOVED;

    override fun toJson() = JsonPrimitive(name.lowercase())

}
