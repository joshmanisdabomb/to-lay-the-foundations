package net.jidb.to.base.library

import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

sealed class DeferredRegisterLibrary<I, V>(modid: String, val registryId: ResourceKey<Registry<V>>) : Library<I, V>(modid) {

    val registry = DeferredRegister.create(modid, registryId)

    override fun afterBuild() {
        registry.register()
    }

    override fun afterBuild(entry: LibraryEntry<out I, out V>) {
        registry.register(entry.name) { entry.value }
    }

    override fun getResourceKey(entry: LibraryEntry<out I, out V>) = ResourceKey.create(registryId, entry.identifier)

}