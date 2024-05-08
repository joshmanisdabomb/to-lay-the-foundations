package net.jidb.to.base.wiki

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation

data class WikiDestination<T : Any>(val registry: Registry<T>, val identifier: ResourceLocation) : JsonSerialisable {

    constructor(registry: Registry<T>, value: T) : this(registry, registry.getKey(value)!!)

    override fun toJson() = JsonObject().also {
        it.addProperty("registry", registry.key().location().toString())
        it.addProperty("identifier", identifier.toString())
    }

}
