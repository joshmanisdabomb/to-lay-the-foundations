package net.jidb.to.base.fabric

import net.fabricmc.api.ModInitializer
import net.jidb.to.base.fabriclike.ToBaseFabriclikeMod

object ToBaseFabricMod: ModInitializer {
    override fun onInitialize() {
        ToBaseFabriclikeMod.init()
    }
}
