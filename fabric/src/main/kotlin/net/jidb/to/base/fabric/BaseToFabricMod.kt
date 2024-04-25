package net.jidb.to.base.fabric

import net.fabricmc.api.ModInitializer
import net.jidb.to.base.fabriclike.BaseToFabricLikeMod

object BaseToFabricMod: ModInitializer {
    override fun onInitialize() {
        BaseToFabricLikeMod.init()
    }
}
