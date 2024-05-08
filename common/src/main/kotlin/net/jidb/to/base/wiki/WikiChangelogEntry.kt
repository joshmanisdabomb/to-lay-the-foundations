package net.jidb.to.base.wiki

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.add

class WikiChangelogEntry(val fragment: WikiFragment, val type: WikiChangelogType = WikiChangelogType.CHANGED) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        it.add("type", type)
        it.add("fragment", fragment)
    }

}
