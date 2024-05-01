package net.jidb.to.base.extension

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

fun ResourceLocation.modify(pathMod: (name: String) -> String) = ResourceLocationHelper.modify(this, pathMod)
fun ResourceLocation.prefix(prefix: String?, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = ResourceLocationHelper.Companion::defaultGlueWhen) = ResourceLocationHelper.idPrefix(this, prefix, glue, glueWhen)
fun ResourceLocation.suffix(suffix: String?, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = ResourceLocationHelper.Companion::defaultGlueWhen) = ResourceLocationHelper.idSuffix(this, suffix, glue, glueWhen)

val Block.resourceLocation get() = BuiltInRegistries.BLOCK.getKey(this)
val Item.resourceLocation get() = BuiltInRegistries.ITEM.getKey(this)

fun Block.resourceLocation(folder: String? = ResourceLocationHelper.BlockResourceLocationHelper.defaultFolder, pathMod: (name: String) -> String = { it }) = ResourceLocationHelper.BlockResourceLocationHelper.loc(this, folder, pathMod)
fun Block.resourceLocationPrefix(prefix: String?, folder: String? = ResourceLocationHelper.BlockResourceLocationHelper.defaultFolder, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = ResourceLocationHelper.Companion::defaultGlueWhen, pathMod: (name: String) -> String = { it }) = ResourceLocationHelper.BlockResourceLocationHelper.locPrefix(this, prefix, folder, glue, glueWhen, pathMod)
fun Block.resourceLocationSuffix(suffix: String?, folder: String? = ResourceLocationHelper.BlockResourceLocationHelper.defaultFolder, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = ResourceLocationHelper.Companion::defaultGlueWhen, pathMod: (name: String) -> String = { it }) = ResourceLocationHelper.BlockResourceLocationHelper.locSuffix(this, suffix, folder, glue, glueWhen, pathMod)

fun Item.resourceLocation(folder: String? = ResourceLocationHelper.ItemResourceLocationHelper.defaultFolder, pathMod: (name: String) -> String = { it }) = ResourceLocationHelper.ItemResourceLocationHelper.loc(this, folder, pathMod)
fun Item.resourceLocationPrefix(prefix: String?, folder: String? = ResourceLocationHelper.ItemResourceLocationHelper.defaultFolder, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = ResourceLocationHelper.Companion::defaultGlueWhen, pathMod: (name: String) -> String = { it }) = ResourceLocationHelper.ItemResourceLocationHelper.locPrefix(this, prefix, folder, glue, glueWhen, pathMod)
fun Item.resourceLocationSuffix(suffix: String?, folder: String? = ResourceLocationHelper.ItemResourceLocationHelper.defaultFolder, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = ResourceLocationHelper.Companion::defaultGlueWhen, pathMod: (name: String) -> String = { it }) = ResourceLocationHelper.ItemResourceLocationHelper.locSuffix(this, suffix, folder, glue, glueWhen, pathMod)

abstract class ResourceLocationHelper<T>(val defaultFolder: String?) {

    abstract fun registry(obj: T): ResourceLocation

    fun loc(obj: T, folder: String? = this.defaultFolder, pathMod: (name: String) -> String = { it }) = modify(prefix(registry(obj), folder, "/"), pathMod)

    fun prefix(id: ResourceLocation, prefix: String?, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = Companion::defaultGlueWhen) = idPrefix(id, prefix, glue, glueWhen)

    fun suffix(id: ResourceLocation, suffix: String?, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = Companion::defaultGlueWhen) = idSuffix(id, suffix, glue, glueWhen)

    fun locPrefix(obj: T, prefix: String?, folder: String? = this.defaultFolder, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = Companion::defaultGlueWhen, pathMod: (name: String) -> String = { it }) = loc(obj, folder) { stringPrefix(pathMod(it), prefix, glue, glueWhen) }

    fun locSuffix(obj: T, suffix: String?, folder: String? = this.defaultFolder, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = Companion::defaultGlueWhen, pathMod: (name: String) -> String = { it }) = loc(obj, folder) { stringSuffix(pathMod(it), suffix, glue, glueWhen) }

    fun assetPath(obj: T) = registry(obj).path

    object BlockResourceLocationHelper : ResourceLocationHelper<Block>("block") {
        override fun registry(obj: Block) = obj.resourceLocation
    }

    object ItemResourceLocationHelper : ResourceLocationHelper<Item>("item") {
        override fun registry(obj: Item) = obj.resourceLocation
    }

    companion object {
        fun createWithDefaultNamespace(id: String, namespace: String) = if (!id.contains(':')) ResourceLocation(namespace, id) else ResourceLocation(id)

        fun create(path: String, namespace: String, pathMod: (name: String) -> String = { it }) = ResourceLocation(namespace, pathMod(path))

        fun modify(resource: ResourceLocation, pathMod: (name: String) -> String) = create(resource.path, resource.namespace, pathMod)

        fun stringPrefix(path: String, prefix: String?, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = Companion::defaultGlueWhen) = "${if (glueWhen(prefix)) "$prefix$glue" else ""}$path"

        fun stringSuffix(path: String, suffix: String?, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = Companion::defaultGlueWhen) = "$path${if (glueWhen(suffix)) "$glue$suffix" else ""}"

        fun idPrefix(id: ResourceLocation, prefix: String?, glue: String = "_", glueWhen: (prefix: String?) -> Boolean = Companion::defaultGlueWhen) = ResourceLocation(id.namespace, stringPrefix(id.path, prefix, glue, glueWhen))

        fun idSuffix(id: ResourceLocation, suffix: String?, glue: String = "_", glueWhen: (suffix: String?) -> Boolean = Companion::defaultGlueWhen) = ResourceLocation(id.namespace, stringSuffix(id.path, suffix, glue, glueWhen))

        internal fun defaultGlueWhen(str: String?) = str != null
    }

}