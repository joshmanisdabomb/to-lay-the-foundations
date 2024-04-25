package net.jidb.to.base.quilt

import net.jidb.to.base.fabriclike.BaseToFabricLikeMod
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

object BaseToQuiltMod: ModInitializer {
    override fun onInitialize(mod: ModContainer) {
        BaseToFabricLikeMod.init()
    }
}