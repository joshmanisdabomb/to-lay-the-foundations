package net.jidb.to.base.extension

import java.text.DecimalFormat
import kotlin.math.min
import kotlin.math.sign

private val decimals = mutableMapOf<Int, DecimalFormat>()

fun Number.sign() = this.toDouble().sign.toInt()
fun <T> Number.transformSign(plus: T, minus: T, neutral: T = plus) = when (this.sign()) {
    1 -> plus
    -1 -> minus
    else -> neutral
}

fun <N : Number, O : Number> N.loop(divisor: O, mod: (N, O) -> N, plus: (N, O) -> N) = mod(plus(mod(this, divisor), divisor), divisor)
fun Int.loop(divisor: Int) = this.loop(divisor, Int::mod, Int::plus)

fun <N : Number> N.dropNegative() = this.takeUnless { it.sign() == -1 }
fun <N : Number> N.dropNegativeOrZero() = this.takeUnless { it.sign() < 1 }
fun <N : Number> N.dropZero() = this.takeUnless { it.sign() == 0 }
fun <N : Number> N.dropPositive() = this.takeUnless { it.sign() == 1 }
fun <N : Number> N.dropPositiveOrZero() = this.takeUnless { it.sign() > -1 }

fun Number.decimalFormat(places: Int = 2, force: Boolean = false): String {
    return if (force) String.format("%.${places}f", this)
    else decimals.computeIfAbsent(places) { DecimalFormat("#.".plus("#".repeat(it))) }.format(this)
}

fun <N : Number> N.exp(exponent: Number, times: (N, N) -> N): N {
    var self = this
    repeat(exponent.toInt() - 1) { self = times(self, this) }
    return self
}
fun <N : Number> N.square(times: (N, N) -> N) = this.exp(2, times)

fun Int.exp(exponent: Number) = this.exp(exponent, Int::times)
fun Int.square(): Int = this.exp(2)
fun Int.sqrt(): Int = kotlin.math.sqrt(this.toFloat()).toInt()

fun Float.exp(exponent: Number) = this.exp(exponent, Float::times)
fun Float.square(): Float = this.exp(2)
fun Float.sqrt(): Float = kotlin.math.sqrt(this)

fun Double.exp(exponent: Number) = this.exp(exponent, Double::times)
fun Double.square(): Double = this.exp(2)
fun Double.sqrt(): Double = kotlin.math.sqrt(this)