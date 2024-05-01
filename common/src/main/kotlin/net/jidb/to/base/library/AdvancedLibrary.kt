package net.jidb.to.base.library

abstract class AdvancedLibrary<I : Any, V : Any>(modid: String) : Library<I, V>(modid), AdvancedLibraryBuilder<I, V>