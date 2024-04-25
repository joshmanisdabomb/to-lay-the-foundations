package net.jidb.to.base

import dev.architectury.injectables.annotations.ExpectPlatform
import java.nio.file.Path

object ExpectPlatform {
    @JvmStatic
    @ExpectPlatform
    fun getConfigDirectory(): Path {
        throw AssertionError()
    }
}