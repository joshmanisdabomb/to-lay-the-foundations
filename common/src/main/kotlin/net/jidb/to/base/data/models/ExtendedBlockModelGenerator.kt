package net.jidb.to.base.data.models

import net.jidb.to.base.extension.horizontalDirections
import net.jidb.to.base.extension.horizontalIndex
import net.jidb.to.base.extension.loopGet
import net.jidb.to.base.extension.plus
import net.minecraft.core.Direction
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.blockstates.*
import net.minecraft.data.models.blockstates.VariantProperties.Rotation
import net.minecraft.data.models.model.TexturedModel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties

class ExtendedBlockModelGenerator(val generator: BlockModelGenerators) {

    fun createHorizontalBlock(block: Block, model: TexturedModel.Provider = TexturedModel.ORIENTABLE, direction: Direction = Direction.NORTH) {
        val loc = model.create(block, (generator as net.jidb.to.base.mixin.data.BlockModelGeneratorsAccessor).modelOutput)
        generator.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, loc))
                .with(createHorizontalDispatch(direction))
        )
    }

    fun createDirectionalBlock(block: Block, model: TexturedModel.Provider = ExtendedTexturedModelProviders.CUBE_ORIENTABLE_VERTICAL, direction: Direction = Direction.UP) {
        val loc = model.create(block, (generator as net.jidb.to.base.mixin.data.BlockModelGeneratorsAccessor).modelOutput)
        generator.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, loc))
                .with(createDirectionalDispatch(direction))
        )
    }

    companion object {
        fun getHorizontalRotation(of: Direction, plus: Direction = Direction.NORTH) = Rotation.entries[of.plus(plus).horizontalIndex!!]

        fun getDirectionalRotations(of: Direction, plus: Direction = Direction.UP): Pair<Rotation, Rotation> {
            val xRot = Rotation.entries.loopGet((-of.stepY + 1) + (plus.stepY - 1))
            val yRot = Rotation.entries[(if (plus.axis.isHorizontal) of.plus(plus) else of).horizontalIndex ?: 0]
            return xRot to yRot
        }

        fun createHorizontalDispatch(plus: Direction = Direction.NORTH) = PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
            .apply { for (d in horizontalDirections) {
                select(d, Variant.variant().with(VariantProperties.Y_ROT, getHorizontalRotation(d, plus)))
            } }

        fun createDirectionalDispatch(plus: Direction = Direction.UP) = PropertyDispatch.property(BlockStateProperties.FACING)
            .apply { for (d in Direction.entries) {
                val (x, y) = getDirectionalRotations(d, plus)
                select(d, Variant.variant().with(VariantProperties.X_ROT, x).with(VariantProperties.Y_ROT, y))
            } }
    }

}