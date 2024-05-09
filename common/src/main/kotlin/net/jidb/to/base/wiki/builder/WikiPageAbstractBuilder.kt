package net.jidb.to.base.wiki.builder

import net.jidb.to.base.wiki.WikiContent
import net.jidb.to.base.wiki.WikiPage
import net.jidb.to.base.wiki.WikiSubject
import net.jidb.to.base.wiki.reference.WikiReference
import net.minecraft.network.chat.Component

abstract class WikiPageAbstractBuilder : WikiPageBuilder {

    override lateinit var parent: WikiArticleBuilder
    override lateinit var key: String

    protected lateinit var name: () -> Component
    protected lateinit var flavor: () -> Component
    val subjects = mutableListOf<WikiSubject>()

    val subject get() = subjects.minOf { it }

    protected val _translations = mutableListOf<Map<String, String>>()
    override val translations get() = _translations.toList()

    protected fun getTranslatableCompound(translations: Map<String, String>, vararg args: Any): () -> Component {
        val index = _translations.count()
        _translations.add(translations)
        return { Component.translatable(this.getTranslationKey(index), *args) }
    }

    fun setName(translation: String) = setName(mapOf("en_us" to translation))
    fun setName(translations: Map<String, String>): WikiPageAbstractBuilder {
        name = getTranslatableCompound(translations)
        return this
    }
    fun setName(component: Component): WikiPageAbstractBuilder {
        name = { component }
        return this
    }
    fun setName(reference: WikiReference): WikiPageAbstractBuilder {
        name = { reference.component }
        return this
    }

    fun setFlavorText(translation: String) = setFlavorText(mapOf("en_us" to translation))
    fun setFlavorText(translations: Map<String, String>): WikiPageAbstractBuilder {
        flavor = getTranslatableCompound(translations)
        return this
    }
    fun setFlavorText(component: Component): WikiPageAbstractBuilder {
        flavor = { component }
        return this
    }
    fun setFlavorText(reference: WikiReference): WikiPageAbstractBuilder {
        flavor = { reference.component }
        return this
    }

    fun addSubject(vararg subjects: WikiSubject): WikiPageAbstractBuilder {
        this.subjects.addAll(subjects)
        return this
    }

    override fun build(translations: WikiTranslations): WikiPage {
        val page = WikiPage(this.name(), this.flavor(), this.getContent(), subjects)
        translations.store(this)
        return page
    }

    abstract fun getContent(): WikiContent

}