package net.jidb.to.base.library

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

open class SimpleDeferredRegisterLibrary<T : Any>(modid: String, registryId: ResourceKey<Registry<T>>) : DeferredRegisterLibrary<T, T>(modid, registryId), SimpleLibraryBuilder<T>