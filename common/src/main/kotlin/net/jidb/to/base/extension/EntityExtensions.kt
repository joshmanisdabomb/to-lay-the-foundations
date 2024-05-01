package net.jidb.to.base.extension

import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import kotlin.reflect.KProperty

fun Entity.replaceMovement(x: Double? = null, y: Double? = null, z: Double? = null) {
    val v = this.deltaMovement
    this.setDeltaMovement(x ?: v.x, y ?: v.y, z ?: v.z)
}

fun Entity.replacePosition(x: Double? = null, y: Double? = null, z: Double? = null) {
    val p = this.position()
    this.setPos(x ?: p.x, y ?: p.y, z ?: p.z)
}

operator fun <T : Any, E : Entity> EntityDataAccessor<T>.getValue(entity: E, property: KProperty<*>): T = entity.entityData.get(this)
operator fun <T : Any, E : Entity> EntityDataAccessor<T>.setValue(entity: E, property: KProperty<*>, value: T) = entity.entityData.set(this, value)

val Player.isSurvival get() = !this.isCreative && !this.isSpectator