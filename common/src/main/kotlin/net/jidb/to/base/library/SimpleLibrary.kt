package net.jidb.to.base.library

open class SimpleLibrary<T : Any>(modid: String) : Library<T, T>(modid), SimpleLibraryBuilder<T>