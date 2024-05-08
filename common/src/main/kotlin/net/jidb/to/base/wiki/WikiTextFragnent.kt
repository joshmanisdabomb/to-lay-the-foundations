package net.jidb.to.base.wiki

import com.google.gson.JsonObject
import net.jidb.to.base.extension.addOrNull
import net.minecraft.network.chat.Component

class WikiTextFragnent(val component: Component, val link: WikiDestination<*>? = null) : WikiFragment() {

    override val type = "text"

    override fun toJson(json: JsonObject) {
        json.add("component", Component.Serializer.toJsonTree(component))
        json.addOrNull("link", link)
    }

}