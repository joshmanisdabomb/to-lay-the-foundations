package net.jidb.to.base.library

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

abstract class AdvancedDeferredRegisterLibrary<I : Any, V : Any>(modid: String, registryId: ResourceKey<Registry<V>>) : DeferredRegisterLibrary<I, V>(modid, registryId), AdvancedLibraryBuilder<I, V>