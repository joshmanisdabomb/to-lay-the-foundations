package net.jidb.to.base.wiki.reference

import net.minecraft.core.Registry
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.level.block.Block

open class WikiRegistryReference<T : Any>(val registryObj: Registry<T>, val value: T) : WikiReference() {

    override val registry = registryObj.key().location()
    override val identifier = registryObj.getKey(value)!!

    override fun getDefaultTranslationKey() = when (value) {
        is Block -> value.descriptionId
        is Item -> value.descriptionId
        is EntityType<*> -> value.descriptionId
        is MobEffect -> value.descriptionId
        is Enchantment -> value.descriptionId
        else -> super.getDefaultTranslationKey()
    }

    override fun getBlock(): Block? {
        if (value is BlockItem) return value.block
        return super.getBlock()
    }

    override fun getItem(): Item? {
        if (value is Block) return value.asItem()
        return super.getItem()
    }

}