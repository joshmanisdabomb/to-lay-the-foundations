package net.jidb.to.base.wiki

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.extension.*
import net.jidb.to.base.mixin.BlockStateBaseAccessor
import net.jidb.to.base.mixin.FireBlockAccessor
import net.minecraft.Util
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block

open class WikiSubject<T : Any>(val registry: Registry<T>, val value: T, val renewable: Boolean? = null, val order: Int? = null) : JsonSerialisable {

    val identifier by lazy { registry.getKey(value)!! }

    override fun toJson() = JsonObject().also {
        it.addProperty("registry", registry.key().location().toString())
        it.addProperty("identifier", identifier.toString())
        it.addProperty("order", order)
        if (renewable == null && (value is Block || value is Item)) error("Article subject ${ResourceKey.create(registry.key(), identifier)} is a block or item and should have renewable property specified.")
        it.addOrNull("renewable", renewable)
        it.addOrNull("attributes", getAttributes())
    }

    open fun getAttributes(): JsonObject? = when (value) {
        is Block -> getBlockAttributes(value)
        is Item -> getItemAttributes(value)
        else -> null
    }

    open fun getBlockAttributes(block: Block) = JsonObject().also {
        val item = block.asItem()
        if (item != Items.AIR) {
            it.replace(getItemAttributes(item))
        }

        it.addProperty("hardness", block.defaultDestroyTime())
        it.addProperty("resistance", block.explosionResistance)
        it.addProperty("friction", block.friction)
        it.add("flammability", JsonObject().also { f ->
            BuiltInRegistries.BLOCK.filterIsInstance<FireBlockAccessor>().forEach { fire ->
                val burn = fire.burnOdds.getInt(block)
                val ignite = fire.igniteOdds.getInt(block)
                if (burn > 0 || ignite > 0) {
                    f.addNumberObject(BuiltInRegistries.BLOCK.getKey(fire as Block).toString(), mapOf("burn" to burn, "ignite" to ignite))
                }
            }
        })

        it.addSerialisableArray("states", block.stateDefinition.possibleStates.toTypedArray()) { state -> JsonObject().also {
            it.addProperty("default", state == block.defaultBlockState())
            it.addStringObject("properties", state.values.toList().associate { (k, v) -> k.name to Util.getPropertyName(k, v) })
            it.addProperty("light", state.lightEmission)
            it.addOrNull("map_color", (state as BlockStateBaseAccessor).defaultMapColor.col)
        } }

        it.getOrCreateObject("tags") {
            it.add("minecraft:block", getTagJson(BuiltInRegistries.BLOCK, block))
        }

        //TODO required and recommended tools
        //TODO ticks randomly, tick speed
    }

    open fun getItemAttributes(item: Item) = JsonObject().also {
        val default = item.stack()
        it.addProperty("stack_size", item.maxStackSize)
        it.addProperty("rarity", item.getRarity(default).name.lowercase())

        it.getOrCreateObject("tags") {
            it.add("minecraft:item", getTagJson(BuiltInRegistries.ITEM, item))
        }

        //TODO item dynamic attributes
    }

    protected fun <I : Any> getTagJson(registry: Registry<I>, value: I) = JsonArray().also {
        it.addStrings(registry.getHolderOrThrow(registry.getResourceKey(value).orElseThrow()).tags().map(TagKey<I>::toString).toList().toTypedArray())
    }

}
