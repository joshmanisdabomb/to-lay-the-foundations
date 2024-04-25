package net.jidb.to.base

import dev.architectury.registry.CreativeTabRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import net.jidb.to.base.ExpectPlatform.getConfigDirectory
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

object BaseToMod {
    const val MOD_ID = "to_base"

    private val tabs = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB)
    val tab: RegistrySupplier<CreativeModeTab> = tabs.register("tab") {
        CreativeTabRegistry.create(Component.translatable("itemGroup.$MOD_ID")) {
            ItemStack(item.get())
        }
    }

    private val items = DeferredRegister.create(MOD_ID, Registries.ITEM)
    val item: RegistrySupplier<Item> = items.register("test_item") {
        Item(Item.Properties().`arch$tab`(tab))
    }

    fun init() {
        tabs.register()
        items.register()

        println("CONFIG DIR: ${getConfigDirectory().toAbsolutePath().normalize()}")
    }
}