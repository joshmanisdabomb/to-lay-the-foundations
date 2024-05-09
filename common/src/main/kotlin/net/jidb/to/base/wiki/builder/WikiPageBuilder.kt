package net.jidb.to.base.wiki.builder

import net.jidb.to.base.wiki.WikiPage

interface WikiPageBuilder {

    var parent: WikiArticleBuilder
    var key: String

    val translations: List<Map<String, String>>

    fun build(translations: WikiTranslations): WikiPage

    fun getTranslationKey(index: Int) = "wiki.${parent.identifier.namespace}.page.${parent.identifier.path}.$key.$index"

}