package net.jidb.to.base.data.models

import net.jidb.to.base.mixin.data.TexturedModelAccessor
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot

object ExtendedTexturedModelProviders {

    val CUBE_ORIENTABLE_VERTICAL = TexturedModelAccessor.createDefault({
        TextureMapping()
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(it, "_side"))
            .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(it, "_front"))
    }, ExtendedModelTemplates.CUBE_ORIENTABLE_VERTICAL)

}