package net.jidb.to.base.wiki.meta

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisableObject
import java.time.format.DateTimeFormatter

interface ToWikiMeta : JsonSerialisable {

    val mods: List<ToWikiMod>
    val builds: List<ToWikiBuild>

    override fun toJson() = JsonObject().also {
        it.addSerialisableObject("mods", mods.associateBy { it.identifier })
        it.addSerialisableObject("builds", builds.associateBy { it.commit })
    }

    companion object {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000000'Z'")
    }

}