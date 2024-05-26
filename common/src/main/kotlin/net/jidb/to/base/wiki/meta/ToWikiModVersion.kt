package net.jidb.to.base.wiki.meta

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import java.time.ZonedDateTime

data class ToWikiModVersion(val code: String, val mcVersion: String, val commit: String?, val released: ZonedDateTime?, val name: String = code, val title: String? = null, val changelog: String = "") : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        it.addProperty("name", name)
        it.addProperty("code", code)
        it.addProperty("mc_version", mcVersion)
        it.addProperty("commit", commit)
        it.addProperty("title", title)
        it.addProperty("changelog", changelog)
        it.addProperty("released_at", released?.format(ToWikiMeta.formatter))
    }

}
