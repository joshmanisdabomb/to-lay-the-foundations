package net.jidb.to.base.wiki.builder

import net.jidb.to.base.wiki.WikiArticle
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class WikiArticleBuilder(val identifier: ResourceLocation, val pages: MutableMap<String, WikiPageBuilder> = mutableMapOf()) {

    init {
        pages.forEach { it.value.parent = this }
    }

    fun addPage(key: String, page: WikiPageBuilder): WikiArticleBuilder {
        page.parent = this
        page.key = key
        pages[key] = page
        return this
    }

    fun build(translations: WikiTranslations) = WikiArticle(identifier, pages.mapValues { it.value.build(translations) })

    companion object {
        fun <T : Any> create(registry: Registry<T>, value: T, pages: MutableMap<String, WikiPageBuilder> = mutableMapOf()) = WikiArticleBuilder(registry.getKey(value)!!, pages)
        fun <T : Any> create(block: Block, pages: MutableMap<String, WikiPageBuilder> = mutableMapOf()) = create(BuiltInRegistries.BLOCK, block, pages)
        fun <T : Any> create(item: Item, pages: MutableMap<String, WikiPageBuilder> = mutableMapOf()) = create(BuiltInRegistries.ITEM, item, pages)
    }

}