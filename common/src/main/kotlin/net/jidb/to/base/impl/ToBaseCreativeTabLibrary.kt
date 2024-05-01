package net.jidb.to.base.impl

import dev.architectury.registry.CreativeTabRegistry
import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.library.AdvancedDeferredRegisterLibrary
import net.jidb.to.base.library.DeferredRegisterLibrary
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.MutableComponent
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object ToBaseCreativeTabLibrary : AdvancedDeferredRegisterLibrary<(CreativeModeTab.Builder) -> Unit, CreativeModeTab>(ToBaseMod.MOD_ID, Registries.CREATIVE_MODE_TAB) {

    val tab: CreativeModeTab by this(::i) { { tab ->
        tab.title(it.component).icon { ItemStack(ToBaseItemLibrary.test_item) }
    } }

    override fun <J : (CreativeModeTab.Builder) -> Unit, W : CreativeModeTab> i(input: J) = CreativeTabRegistry.create(input) as W

    override fun getTranslationKey(entry: LibraryEntry<out (CreativeModeTab.Builder) -> Unit, out CreativeModeTab>) = "itemGroup.${modid}.${entry.name}"

}