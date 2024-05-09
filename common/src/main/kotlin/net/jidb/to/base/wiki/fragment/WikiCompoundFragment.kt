package net.jidb.to.base.wiki.fragment

import com.google.gson.JsonObject
import net.jidb.to.base.extension.addSerialisableArray
import net.jidb.to.base.extension.addOrNull
import net.minecraft.network.chat.Component

class WikiCompoundFragment(vararg val fragments: WikiFragment, val paragraph: Boolean = false, val heading: Component? = null) : WikiFragment() {

    override val type = "compound"

    override fun toJson(json: JsonObject) {
        json.addSerialisableArray("fragments", fragments)
        json.addProperty("paragraph", paragraph)
        json.addOrNull("heading", heading?.let(Component.Serializer::toJsonTree))
    }

}