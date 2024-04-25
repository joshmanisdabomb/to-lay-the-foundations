package net.jidb.to.base.forge

import dev.architectury.platform.forge.EventBuses
import net.jidb.to.base.BaseToMod
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(BaseToMod.MOD_ID)
object BaseToForgeMod {
    init {
        EventBuses.registerModEventBus(BaseToMod.MOD_ID, MOD_BUS)
        BaseToMod.init()
    }
}