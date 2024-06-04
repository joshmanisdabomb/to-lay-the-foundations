package net.jidb.to.base.wiki.builder

import net.jidb.to.base.wiki.WikiConstantLibrary

class WikiTranslations {

    val translations = mutableMapOf<String, Map<String, String>>()

    fun include(translations: Map<String, Map<String, String>>): WikiTranslations {
        this.translations += translations
        return this
    }

    fun includeConstants() = include(WikiConstantLibrary.translations)

    fun store(page: WikiArticleBuilder) = include(page.translations.mapIndexed { index, map -> page.getTranslationKey(index) to map }.toMap())

}