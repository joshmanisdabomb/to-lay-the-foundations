package net.jidb.to.base.wiki.meta

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import java.time.ZonedDateTime

data class ToWikiBuildFile(val path: String, val type: String? = null, val sources: Boolean = false, val released: ZonedDateTime? = null) : JsonSerialisable {

    constructor(mod: ToWikiMod, version: ToWikiModVersion, type: String? = null, sources: Boolean = false) : this(filename(mod.short, version.mcVersion, version.code, sources), type, sources)
    constructor(mod: ToWikiMod, build: ToWikiBuild, type: String? = null, sources: Boolean = false) : this(filename(mod.short, build.mcVersion, build.modVersion, sources), type, sources)

    override fun toJson() = JsonObject().also {
        it.addProperty("path", path)
        it.addProperty("type", type)
        it.addProperty("sources", sources)
        it.addProperty("released_at", released?.format(ToWikiMeta.formatter))
    }

    companion object {
        fun filename(short: String, mcVersion: String, version: String, sources: Boolean = false): String {
            val raw = "builds/$short-$mcVersion-$version"
            return raw + (if (sources) "-sources" else "") + ".jar"
        }
    }

}
