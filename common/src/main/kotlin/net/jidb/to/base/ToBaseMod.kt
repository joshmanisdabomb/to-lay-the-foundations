package net.jidb.to.base

import net.jidb.to.base.ExpectPlatform.getConfigDirectory
import net.jidb.to.base.impl.ToBaseCreativeTabLibrary
import net.jidb.to.base.impl.ToBaseItemLibrary

object ToBaseMod {
    const val MOD_ID = "to_base"

    fun init() {
        ToBaseCreativeTabLibrary.build()
        ToBaseItemLibrary.build()

        println("CONFIG DIR: ${getConfigDirectory().toAbsolutePath().normalize()}")
    }
}