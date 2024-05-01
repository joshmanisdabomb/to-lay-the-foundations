package net.jidb.to.base.extension

import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.level.block.state.properties.EnumProperty

fun Block.horizontalPlacement(context: BlockPlaceContext, property: DirectionProperty = HorizontalDirectionalBlock.FACING) = defaultBlockState().setValue(property, context.horizontalDirection.opposite)
fun Block.horizontalFacePlacement(context: BlockPlaceContext, default: Direction = context.horizontalDirection.opposite, property: DirectionProperty = HorizontalDirectionalBlock.FACING) = defaultBlockState().setValue(property, context.clickedFace.let { if (it.axis == Direction.Axis.Y) default else it })
fun Block.directionalPlayerPlacement(context: BlockPlaceContext, property: DirectionProperty = DirectionalBlock.FACING) = defaultBlockState().setValue(property, context.nearestLookingDirection.opposite)
fun Block.directionalFacePlacement(context: BlockPlaceContext, property: DirectionProperty = DirectionalBlock.FACING) = defaultBlockState().setValue(property, context.clickedFace)
fun Block.pillarPlacement(context: BlockPlaceContext, property: EnumProperty<Direction.Axis> = RotatedPillarBlock.AXIS) = defaultBlockState().setValue(property, context.clickedFace.axis)