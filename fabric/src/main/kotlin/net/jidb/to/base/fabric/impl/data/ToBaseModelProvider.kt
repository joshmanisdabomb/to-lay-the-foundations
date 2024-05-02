package net.jidb.to.base.fabric.impl.data

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.jidb.to.base.fabric.data.models.ExtendedBlockModelGenerator
import net.jidb.to.base.impl.ToBaseBlockLibrary
import net.jidb.to.base.impl.ToBaseItemLibrary
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.models.model.TexturedModel

class ToBaseModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(generator: BlockModelGenerators) {
        val extra = ExtendedBlockModelGenerator(generator)

        generator.createTrivialCube(ToBaseBlockLibrary.test_block)
        extra.createHorizontalBlock(ToBaseBlockLibrary.test_block_2)
        extra.createDirectionalBlock(ToBaseBlockLibrary.test_block_3)
        generator.createAxisAlignedPillarBlock(ToBaseBlockLibrary.test_block_4, TexturedModel.COLUMN)
    }

    override fun generateItemModels(generator: ItemModelGenerators) {
        generator.generateFlatItem(ToBaseItemLibrary.test_item, ModelTemplates.FLAT_ITEM)
    }

}