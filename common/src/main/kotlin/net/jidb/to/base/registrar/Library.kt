package net.jidb.to.base.registrar

import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

abstract class Library<I : Any, V : Any>(val modid: String) {

    private val _entries = mutableMapOf<String, LibraryEntry>()
    val entries by lazy { _entries.toMap() }
    val values by lazy { entries.mapValues { it.value.value } }

    var initialised = false
        private set

    fun build(): Map<String, LibraryEntry> {
        if (initialised) {
            throw LibraryException("${this@Library} is already built.")
        }
        for (entry in _entries.values) {
            entry.build()
        }
        initialised = true
        afterBuild()
        return entries
    }

    open fun afterBuild() = Unit

    protected abstract fun build(entry: LibraryEntry): () -> V

    protected open fun getComponent(entry: LibraryEntry) = Component.translatable(getTranslationKey(entry))

    protected open fun getTranslationKey(entry: LibraryEntry): String {
        throw LibraryException("${entry.name} in ${this@Library} tried to get a translation key, but the method was not implemented.")
    }

    protected open fun getResourceKey(entry: LibraryEntry): ResourceKey<V> {
        throw LibraryException("${entry.name} in ${this@Library} tried to get a resource key, but the method was not implemented.")
    }

    override fun toString() = "$modid ${this.javaClass}"

    open inner class LibraryEntry(val initial: (LibraryEntry) -> I) {

        lateinit var property: KProperty<*>
            private set
        val name get() = property.name
        var index by Delegates.notNull<Int>()
            private set

        var initialised = false
            private set
        lateinit var getter: () -> V
            private set
        val value by lazy { getter() }

        val identifier by lazy { ResourceLocation(modid, name) }
        val registryKey by lazy { getResourceKey(this) }
        val component by lazy { getComponent(this) }
        val translationKey by lazy { getResourceKey(this) }

        operator fun provideDelegate(library: Library<I, V>, property: KProperty<*>): LibraryEntry {
            this.property = property
            this.index = _entries.size
            _entries[name] = this
            return this
        }

        operator fun getValue(library: Library<I, V>, property: KProperty<*>): V {
            return value
        }

        fun build() {
            if (initialised) {
                throw LibraryException("${property.name} in ${this@Library} is already built.")
            }
            getter = this@Library.build(this)
            initialised = true
        }

    }

}