package net.jidb.to.base.fabric.impl.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.jidb.to.base.fabric.data.models.ExtendedBlockModelGenerator
import net.jidb.to.base.impl.ToBaseItemLibrary
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates

class ToBaseModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        val extra = ExtendedBlockModelGenerator(generator)
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(ToBaseItemLibrary.test_item, ModelTemplates.FLAT_ITEM)
    }

}