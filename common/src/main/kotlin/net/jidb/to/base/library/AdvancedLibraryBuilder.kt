package net.jidb.to.base.library

interface AdvancedLibraryBuilder<I : Any, V : Any> {

    fun <J : I, W : V> i(input: J): W

}
