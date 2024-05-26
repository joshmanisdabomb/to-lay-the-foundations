package net.jidb.to.base.wiki.meta

import com.google.gson.JsonObject
import dev.architectury.platform.Mod
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisableObject

data class ToWikiMod(val identifier: String, val name: String, val short: String, val repository: String, val versions: List<ToWikiModVersion>, val legacy: Boolean = false, val repositoryBranch: String = "main", val tags: Boolean = true, val sources: Boolean = true, val modrinth: Boolean = true, val curseforge: Boolean = true) : JsonSerialisable {

    constructor(mod: Mod, short: String, repository: String, versions: List<ToWikiModVersion>, name: String? = null, legacy: Boolean = false, repositoryBranch: String = "main", tags: Boolean = true, sources: Boolean = true, modrinth: Boolean = true, curseforge: Boolean = true) : this(mod.modId, name ?: mod.name, short, repository, versions, legacy, repositoryBranch, tags, sources, modrinth, curseforge)

    override fun toJson() = JsonObject().also {
        it.addProperty("identifier", identifier)
        it.addProperty("name", name)
        it.addProperty("short", short)
        it.addProperty("repository", repository)
        it.addProperty("legacy", legacy)
        it.addProperty("repository_branch", repositoryBranch)
        it.addProperty("tags", tags)
        it.addProperty("sources", sources)
        it.addProperty("modrinth", modrinth)
        it.addProperty("curseforge", curseforge)
        it.addSerialisableObject("versions", versions.associateBy { it.code })
    }

    fun generateBuilds() = versions.filter { it.released != null && it.commit != null }.map { ToWikiBuild(this, it) }

}
