package net.jidb.to.base

import net.jidb.to.base.ExpectPlatform.getConfigDirectory
import net.jidb.to.base.registrar.ToBaseCreativeTabLibrary
import net.jidb.to.base.registrar.ToBaseItemLibrary

object ToBaseMod {
    const val MOD_ID = "to_base"

    fun init() {
        ToBaseCreativeTabLibrary.build()
        ToBaseItemLibrary.build()

        println(ToBaseCreativeTabLibrary.tab)
        println(ToBaseItemLibrary.test_item)
        println("CONFIG DIR: ${getConfigDirectory().toAbsolutePath().normalize()}")
    }
}