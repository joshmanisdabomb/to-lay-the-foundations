package net.jidb.to.base.extension

fun <T> Boolean.transform(`true`: T, `false`: T) = if (this) `true` else `false`

fun Boolean.transformInt(`true`: Int = 1, `false`: Int = 0) = transform(`true`, `false`)