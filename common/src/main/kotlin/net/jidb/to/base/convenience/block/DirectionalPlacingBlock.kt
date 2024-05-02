package net.jidb.to.base.convenience.block

import net.jidb.to.base.extension.directionalPlayerPlacement
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition

open class DirectionalPlacingBlock(properties: Properties) : DirectionalBlock(properties) {

    init {
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) = builder.add(FACING).let {}

    override fun codec() = CODEC

    override fun getStateForPlacement(context: BlockPlaceContext) = directionalPlayerPlacement(context)

    companion object {
        val CODEC = simpleCodec(::DirectionalPlacingBlock)
    }

}