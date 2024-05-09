package net.jidb.to.base.wiki.reference

import net.jidb.to.base.ToBaseMod
import net.minecraft.resources.ResourceLocation

data class WikiImage(override val identifier: ResourceLocation, override val registry: ResourceLocation = IMAGE) : WikiReference() {

    override fun getDefaultTranslationKey(): String {
        if (registry == IMAGE) {
            return "wiki.${identifier.namespace}.image.${identifier.path}"
        }
        return super.getDefaultTranslationKey()
    }

    companion object {
        val IMAGE = ResourceLocation(ToBaseMod.MOD_ID, "image")
    }

}