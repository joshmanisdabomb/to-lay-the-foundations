package net.jidb.to.base.wiki

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.addSerialisable

data class WikiContent(val fragments: Map<String, WikiFragment>) : JsonSerialisable {

    constructor(fragment: WikiFragment) : this(mapOf("en_us" to fragment))

    override fun toJson() = JsonObject().also { it.addSerialisable(fragments) }

}
