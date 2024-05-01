package net.jidb.to.base.impl

import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.library.SimpleDeferredRegisterLibrary
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item

object ToBaseItemLibrary : SimpleDeferredRegisterLibrary<Item>(ToBaseMod.MOD_ID, Registries.ITEM) {

    val test_item by this(::i) {
        Item(Item.Properties().`arch$tab`(ToBaseCreativeTabLibrary.tab))
    }

}