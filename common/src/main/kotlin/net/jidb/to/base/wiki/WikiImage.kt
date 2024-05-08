package net.jidb.to.base.wiki

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.minecraft.resources.ResourceLocation

data class WikiImage(val location: ResourceLocation, val registry: ResourceLocation = ResourceLocation(ToBaseMod.MOD_ID, "image")) : JsonSerialisable {

    override fun toJson() = JsonObject().also {
        it.addProperty("location", location.toString())
        it.addProperty("registry", registry.toString())
    }

}