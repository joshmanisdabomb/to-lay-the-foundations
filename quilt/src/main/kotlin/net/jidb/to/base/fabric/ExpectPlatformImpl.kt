package net.jidb.to.base.fabric

import org.quiltmc.loader.api.QuiltLoader
import java.nio.file.Path

object ExpectPlatformImpl {
    @JvmStatic
    fun getConfigDirectory(): Path {
        return QuiltLoader.getConfigDir()
    }
}