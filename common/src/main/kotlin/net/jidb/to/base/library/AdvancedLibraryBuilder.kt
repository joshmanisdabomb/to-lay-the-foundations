package net.jidb.to.base.library

interface AdvancedLibraryBuilder<I, V> {

    fun <J : I, W : V> i(input: J): W

}
