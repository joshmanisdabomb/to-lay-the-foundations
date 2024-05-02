package net.jidb.to.base.fabric.data.models

import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import java.util.*

object ExtendedModelTemplates {

    val CUBE_ORIENTABLE_VERTICAL = create(ResourceLocation("block/orientable_vertical"), TextureSlot.FRONT, TextureSlot.SIDE)

    fun create(model: ResourceLocation, vararg slots: TextureSlot, prefix: String? = null) = ModelTemplate(Optional.of(model), Optional.ofNullable(prefix), *slots)

}