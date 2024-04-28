package net.jidb.to.base.registrar

import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

abstract class DeferredRegisterLibrary<I : Any, T : Any>(modid: String, registry: ResourceKey<Registry<T>>) : Library<I, T>(modid) {

    val registry = DeferredRegister.create(modid, registry)

    override fun build(entry: LibraryEntry): () -> T {
        val supplier = registry.register(entry.name) { entry.initial(entry).let(::transform) }
        return { supplier.get() }
    }

    override fun afterBuild() {
        registry.register()
    }

    abstract fun transform(input: I): T

}