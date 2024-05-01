package net.jidb.to.base.extension

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.core.Direction
import net.minecraft.world.level.block.state.properties.BlockStateProperties

private val perps = mapOf(
    Direction.UP to listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST),
    Direction.DOWN to listOf(Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST),
    Direction.NORTH to listOf(Direction.UP, Direction.WEST, Direction.DOWN, Direction.EAST),
    Direction.EAST to listOf(Direction.UP, Direction.NORTH, Direction.DOWN, Direction.SOUTH),
    Direction.SOUTH to listOf(Direction.UP, Direction.EAST, Direction.DOWN, Direction.WEST),
    Direction.WEST to listOf(Direction.UP, Direction.SOUTH, Direction.DOWN, Direction.NORTH)
)

private val properties = mapOf(
    Direction.UP to BlockStateProperties.UP,
    Direction.DOWN to BlockStateProperties.DOWN,
    Direction.NORTH to BlockStateProperties.NORTH,
    Direction.EAST to BlockStateProperties.EAST,
    Direction.SOUTH to BlockStateProperties.SOUTH,
    Direction.WEST to BlockStateProperties.WEST
)

val Direction.perpendiculars get() = perps[this]!!

val Direction.booleanProperty get() = properties[this]!!

val horizontalDirections = arrayOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)

fun Direction.pose(stack: PoseStack) {
    when (this) {
        Direction.DOWN -> {
            stack.translate(0.0, 1.0, 1.0);
            stack.mulPose(Axis.XP.rotationDegrees(180.0f))
        }
        Direction.NORTH -> {
            stack.translate(1.0, 1.0, 1.0);
            stack.mulPose(Axis.XP.rotationDegrees(90.0f))
            stack.mulPose(Axis.ZP.rotationDegrees(180.0f))
        }
        Direction.EAST -> {
            stack.translate(0.0, 1.0, 1.0);
            stack.mulPose(Axis.XP.rotationDegrees(90.0f))
            stack.mulPose(Axis.ZP.rotationDegrees(-90.0f))
        }
        Direction.SOUTH -> {
            stack.translate(0.0, 1.0, 0.0);
            stack.mulPose(Axis.XP.rotationDegrees(90.0f))
        }
        Direction.WEST -> {
            stack.translate(1.0, 1.0, 0.0);
            stack.mulPose(Axis.XP.rotationDegrees(90.0f))
            stack.mulPose(Axis.ZP.rotationDegrees(90.0f))
        }
        else -> {}
    }
}