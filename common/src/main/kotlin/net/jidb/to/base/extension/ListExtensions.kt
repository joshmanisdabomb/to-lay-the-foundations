package net.jidb.to.base.extension

fun <E> List<E>.loopGet(index: Int) = this[index.loop(this.size)]
fun <E> Array<E>.loopGet(index: Int) = this[index.loop(this.size)]