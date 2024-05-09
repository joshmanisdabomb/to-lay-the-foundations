package net.jidb.to.base.wiki.changelog

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.add
import net.jidb.to.base.wiki.fragment.WikiFragment

class WikiChangelogEntry(val fragment: WikiFragment, val type: WikiChangelogType = WikiChangelogType.CHANGED) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        it.add("type", type)
        it.add("fragment", fragment)
    }

}