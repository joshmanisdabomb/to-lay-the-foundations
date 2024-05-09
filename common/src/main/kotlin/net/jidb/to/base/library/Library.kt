package net.jidb.to.base.library

import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

sealed class Library<I, V>(val modid: String) {

    internal val _entries = mutableMapOf<String, LibraryEntry<out I, out V>>()
    val entries by lazy { _entries.toMap() }
    val values by lazy { entries.mapValues { it.value.value } }

    var initialised = false
        private set

    fun build(): Map<String, LibraryEntry<out I, out V>> {
        if (initialised) {
            throw LibraryException("${this@Library} is already built.")
        }
        for (entry in _entries.values) {
            entry.build()
            afterBuild(entry)
        }
        initialised = true
        afterBuild()
        return entries
    }

    fun buildOnce(): Map<String, LibraryEntry<out I, out V>> {
        if (initialised) return entries
        return build()
    }

    open fun afterBuild() = Unit

    open fun afterBuild(entry: LibraryEntry<out I, out V>) = Unit

    open fun getComponent(entry: LibraryEntry<out I, out V>) = Component.translatable(getTranslationKey(entry))

    open fun getTranslationKey(entry: LibraryEntry<out I, out V>): String {
        throw LibraryException("${entry.name} in ${this@Library} tried to get a translation key, but the method was not implemented.")
    }

    open fun getResourceKey(entry: LibraryEntry<out I, out V>): ResourceKey<V> {
        throw LibraryException("${entry.name} in ${this@Library} tried to get a resource key, but the method was not implemented.")
    }

    override fun toString() = "$modid ${this.javaClass}"

    protected operator fun <J : I, W : V> invoke(builder: LibraryEntry<out I, out V>.(J) -> W, initial: (LibraryEntry<J, W>) -> J): LibraryEntry<J, W> {
        return LibraryEntry(builder, initial)
    }

    inner class LibraryEntry<J : I, W : V>(val builder: LibraryEntry<out I, out V>.(J) -> W, val initial: (LibraryEntry<J, W>) -> J) {

        lateinit var property: KProperty<*>
            private set
        val name get() = property.name
        var index by Delegates.notNull<Int>()
            private set

        var initialised = false
            private set
        private var _input: J? = null
        val input get() = _input!!
        lateinit var getter: () -> W
            private set
        val value by lazy { getter() }

        val identifier by lazy { ResourceLocation(modid, name) }
        val registryKey by lazy { getResourceKey(this) }
        val component by lazy { getComponent(this) }
        val translationKey by lazy { getTranslationKey(this) }

        operator fun provideDelegate(library: Library<I, V>, property: KProperty<*>): LibraryEntry<J, W> {
            this.property = property
            this.index = library._entries.size
            library._entries[name] = this
            return this
        }

        operator fun getValue(library: Library<I, V>, property: KProperty<*>): W {
            return value
        }

        fun build() {
            if (initialised) {
                throw LibraryException("${property.name} in ${this@Library} is already built.")
            }
            val input = initial(this)
            _input = input
            getter = { builder(this, input) }
            initialised = true
        }

    }

}