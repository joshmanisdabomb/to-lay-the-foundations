package net.jidb.to.base.wiki.changelog

import com.google.gson.JsonPrimitive
import net.jidb.to.base.convenience.json.JsonSerialisable

enum class WikiChangelogType : JsonSerialisable {

    INTRODUCED,
    REINTRODUCED,
    CHANGED,
    REMOVED;

    override fun toJson() = JsonPrimitive(name.lowercase())

}