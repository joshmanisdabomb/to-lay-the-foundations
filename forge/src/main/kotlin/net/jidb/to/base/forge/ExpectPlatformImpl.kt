package net.jidb.to.base.forge

import net.minecraftforge.fml.loading.FMLPaths
import java.nio.file.Path

object ExpectPlatformImpl {
    @JvmStatic
    fun getConfigDirectory(): Path {
        return FMLPaths.CONFIGDIR.get()
    }
}