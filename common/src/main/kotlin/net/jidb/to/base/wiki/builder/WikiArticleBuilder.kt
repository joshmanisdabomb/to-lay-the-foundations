package net.jidb.to.base.wiki.builder

import net.jidb.to.base.wiki.WikiArticle
import net.minecraft.resources.ResourceLocation

interface WikiArticleBuilder {

    var identifier: ResourceLocation

    val translations: List<Map<String, String>>

    fun build(translations: WikiTranslations): WikiArticle

    fun getTranslationKey(index: Int) = "wiki.${identifier.namespace}.page.${identifier.path}.$index"

}