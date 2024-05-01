package net.jidb.to.base.library

interface SimpleLibraryBuilder<T : Any> {

    fun <U : T> i(input: U) = input

    operator fun <U : T> invoke(initial: (Library<T, T>.LibraryEntry<U, U>) -> U): Library<T, T>.LibraryEntry<U, U> {
        val library = this as Library<T, T>
        return library.LibraryEntry(::i, initial)
    }

}
