package net.jidb.to.base.extension

import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

fun ItemLike.stack(count: Int = 1, alter: ItemStack.() -> Unit = {}) = ItemStack(this, count).apply(alter)