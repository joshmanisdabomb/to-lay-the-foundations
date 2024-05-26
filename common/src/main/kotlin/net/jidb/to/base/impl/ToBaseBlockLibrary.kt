package net.jidb.to.base.impl

import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.convenience.block.DirectionalPlacingBlock
import net.jidb.to.base.convenience.block.HorizontalPlacingBlock
import net.jidb.to.base.library.SimpleDeferredRegisterLibrary
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor

object ToBaseBlockLibrary : SimpleDeferredRegisterLibrary<Block>(ToBaseMod.MOD_ID, Registries.BLOCK) {

    val test_block by this { Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5f).sound(SoundType.SCAFFOLDING)) }
    val test_block_2 by this { HorizontalPlacingBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5f).sound(SoundType.SCAFFOLDING)) }
    val test_block_3 by this { DirectionalPlacingBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5f).sound(SoundType.SCAFFOLDING)) }
    val test_block_4 by this { RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5f).sound(SoundType.SCAFFOLDING)) }

}