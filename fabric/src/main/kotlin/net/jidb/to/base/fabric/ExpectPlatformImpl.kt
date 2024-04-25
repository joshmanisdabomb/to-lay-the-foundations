package net.jidb.to.base.fabric

import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path

object ExpectPlatformImpl {
    @JvmStatic
    fun getConfigDirectory(): Path {
        return FabricLoader.getInstance().configDir
    }
}