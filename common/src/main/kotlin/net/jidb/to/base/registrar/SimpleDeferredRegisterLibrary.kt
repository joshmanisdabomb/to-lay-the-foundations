package net.jidb.to.base.registrar

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

open class SimpleDeferredRegisterLibrary<T : Any>(modid: String, registry: ResourceKey<Registry<T>>) : DeferredRegisterLibrary<T, T>(modid, registry) {

    override fun transform(input: T) = input

}