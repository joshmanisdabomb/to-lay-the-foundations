package net.jidb.to.base.forge

import dev.architectury.platform.forge.EventBuses
import net.jidb.to.base.ToBaseMod
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ToBaseMod.MOD_ID)
object ToBaseForgeMod {
    init {
        EventBuses.registerModEventBus(ToBaseMod.MOD_ID, MOD_BUS)
        ToBaseMod.init()
    }
}