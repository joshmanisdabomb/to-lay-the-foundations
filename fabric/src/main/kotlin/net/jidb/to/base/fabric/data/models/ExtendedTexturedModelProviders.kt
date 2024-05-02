package net.jidb.to.base.fabric.data.models

import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureMapping
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks

object ExtendedTexturedModelProviders {

    val CUBE_ORIENTABLE_VERTICAL = TexturedModel.createDefault({
        TextureMapping()
            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(it, "_side"))
            .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(it, "_front"))
    }, ExtendedModelTemplates.CUBE_ORIENTABLE_VERTICAL)

}