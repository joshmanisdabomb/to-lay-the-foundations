package net.jidb.to.base.registrar

import net.jidb.to.base.ToBaseMod
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item

object ToBaseItemLibrary : SimpleDeferredRegisterLibrary<Item>(ToBaseMod.MOD_ID, Registries.ITEM) {

    val test_item by LibraryEntry {
        Item(Item.Properties().`arch$tab`(ToBaseCreativeTabLibrary.tab))
    }

}