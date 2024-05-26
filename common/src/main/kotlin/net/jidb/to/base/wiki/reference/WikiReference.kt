package net.jidb.to.base.wiki.reference

import com.google.gson.JsonObject
import net.jidb.to.base.convenience.json.JsonSerialisable
import net.jidb.to.base.wiki.WikiArticle
import net.jidb.to.base.wiki.WikiPage
import net.minecraft.Util
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.block.Block

abstract class WikiReference : JsonSerialisable {

    abstract val registry: ResourceLocation
    abstract val identifier: ResourceLocation

    open val translationKey: String get() = getDefaultTranslationKey()
    val component get() = Component.translatable(translationKey)

    open fun getDefaultTranslationKey() = Util.makeDescriptionId(registry.path, identifier)

    override fun toJson() = JsonObject().also {
        it.addProperty("registry", registry.toString())
        it.addProperty("identifier", identifier.toString())
    }

    override fun toString() = "$registry#$identifier"

    open fun getBlock() = BuiltInRegistries.REGISTRY.get(registry)?.get(identifier) as? Block
    open fun getItem() = BuiltInRegistries.REGISTRY.get(registry)?.get(identifier) as? Item
    open fun getEntity() = BuiltInRegistries.REGISTRY.get(registry)?.get(identifier) as? EntityType<*>
    open fun getEffect() = BuiltInRegistries.REGISTRY.get(registry)?.get(identifier) as? MobEffect
    open fun getEnchantment() = BuiltInRegistries.REGISTRY.get(registry)?.get(identifier) as? Enchantment

    companion object {
        fun of(article: WikiArticle) = of(article.pages.toList().first().second)
        fun of(page: WikiPage) = page.subjects.minOf { it }
        fun of(block: Block) = WikiRegistryReference(BuiltInRegistries.BLOCK, block)
        fun of(item: Item) = WikiRegistryReference(BuiltInRegistries.ITEM, item)
        fun of(entity: EntityType<*>) = WikiRegistryReference(BuiltInRegistries.ENTITY_TYPE, entity)
        fun of(effect: MobEffect) = WikiRegistryReference(BuiltInRegistries.MOB_EFFECT, effect)
        fun of(enchantment: Enchantment) = WikiRegistryReference(BuiltInRegistries.ENCHANTMENT, enchantment)
    }

}
