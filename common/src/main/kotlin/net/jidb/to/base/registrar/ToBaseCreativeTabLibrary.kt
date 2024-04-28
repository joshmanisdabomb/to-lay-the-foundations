package net.jidb.to.base.registrar

import dev.architectury.registry.CreativeTabRegistry
import net.jidb.to.base.ToBaseMod
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

object ToBaseCreativeTabLibrary : DeferredRegisterLibrary<(CreativeModeTab.Builder) -> Unit, CreativeModeTab>(ToBaseMod.MOD_ID, Registries.CREATIVE_MODE_TAB) {

    val tab: CreativeModeTab by LibraryEntry { { tab ->
        tab.title(it.translation).icon { ItemStack(ToBaseItemLibrary.test_item) }
    } }

    override fun transform(input: (CreativeModeTab.Builder) -> Unit) = CreativeTabRegistry.create(input)

    override fun getTranslation(entry: LibraryEntry) = Component.translatable("itemGroup.${modid}.${entry.name}")

}