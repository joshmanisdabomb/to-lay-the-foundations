package net.jidb.to.base.wiki.fragment

import com.google.gson.JsonObject
import net.jidb.to.base.extension.addOrNull
import net.jidb.to.base.wiki.reference.WikiReference
import net.minecraft.network.chat.Component

class WikiTextFragment(val component: Component, val refer: WikiReference? = null) : WikiFragment() {

    override val type = "text"

    override fun toJson(json: JsonObject) {
        json.add("component", Component.Serializer.toJsonTree(component))
        json.addOrNull("refer", refer)
    }

}