package net.jidb.to.base.quilt

import net.jidb.to.base.fabriclike.ToBaseFabriclikeMod
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer

object ToBaseQuiltMod: ModInitializer {
    override fun onInitialize(mod: ModContainer) {
        ToBaseFabriclikeMod.init()
    }
}