package net.jidb.to.base.wiki.meta

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisableArray
import java.time.ZonedDateTime

data class ToWikiBuild(val modIdentifier: String, val modVersion: String, val mcVersion: String, val repository: String, val ref: String, val commit: String, val released: ZonedDateTime, val files: List<ToWikiBuildFile>, val nightly: Boolean = false, val runNumber: Int? = null) : JsonSerialisable {

    constructor(mod: ToWikiMod, version: ToWikiModVersion, files: List<ToWikiBuildFile>? = null) : this(mod.identifier, version.code, version.mcVersion, mod.repository, mod.repositoryBranch, version.commit!!, version.released!!, files ?: listOfNotNull(ToWikiBuildFile(mod, version, null, false), ToWikiBuildFile(mod, version, null, true).takeIf { mod.sources }))

    override fun toJson() = JsonObject().also {
        it.addProperty("nightly", nightly)
        it.addProperty("mod_identifier", modIdentifier)
        it.addProperty("mod_version", modVersion)
        it.addProperty("mc_version", mcVersion)
        it.addProperty("repository", repository)
        it.addProperty("ref_name", ref)
        it.addProperty("commit", commit)
        it.addProperty("run_number", runNumber)
        it.addProperty("released_at", released.format(ToWikiMeta.formatter))
        it.addSerialisableArray("files", files.toTypedArray())
    }

}
