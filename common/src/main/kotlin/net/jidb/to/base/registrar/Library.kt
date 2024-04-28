package net.jidb.to.base.registrar

import net.minecraft.network.chat.Component
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

    protected open fun getTranslation(entry: LibraryEntry): Component {
        throw LibraryException("${entry.name} in ${this@Library} tried to get a translation, but the method was not implemented.")
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

        val translation by lazy { getTranslation(this) }

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