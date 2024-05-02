package net.jidb.to.base.impl

import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.library.BlockItemLibrary
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object ToBaseBlockItemLibrary : BlockItemLibrary(ToBaseMod.MOD_ID, ToBaseBlockLibrary) {

    override fun createDefault(block: Block, name: String) = BlockItem(block, Item.Properties().`arch$tab`(ToBaseCreativeTabLibrary.tab))

}