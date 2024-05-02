package net.jidb.to.base.convenience.block

import net.jidb.to.base.extension.horizontalPlacement
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition

open class HorizontalPlacingBlock(properties: Properties) : HorizontalDirectionalBlock(properties) {

    init {
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) = builder.add(FACING).let {}

    override fun codec() = CODEC

    override fun getStateForPlacement(context: BlockPlaceContext) = horizontalPlacement(context)

    companion object {
        val CODEC = simpleCodec(::HorizontalPlacingBlock)
    }

}