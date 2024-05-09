package net.jidb.to.base.wiki

import net.jidb.to.base.ToBaseMod
import net.jidb.to.base.library.AdvancedLibrary

object WikiConstantLibrary : AdvancedLibrary<Map<String, String>, String>(ToBaseMod.MOD_ID) {

    lateinit var translations: Map<String, Map<String, String>>
        private set

    val space by this(::i) { mapOf("en_us" to " ") }
    val comma by this(::i) { mapOf("en_us" to ",") }
    val and by this(::i) { mapOf("en_us" to "and") }

    val introduction by this(::i) { mapOf("en_us" to "Introduction") }
    val introduced by this(::i) { mapOf("en_us" to "introduced in ") }
    val reintroduced by this(::i) { mapOf("en_us" to "reintroduced in ") }
    val removed by this(::i) { mapOf("en_us" to "removed in ") }

    override fun <J : Map<String, String>> i(entry: LibraryEntry<out Map<String, String>, out String>, input: J) = "wiki.$modid.constant.${entry.name}"

    override fun afterBuild() {
        translations = entries.values.associate { it.value to it.input }
    }

}