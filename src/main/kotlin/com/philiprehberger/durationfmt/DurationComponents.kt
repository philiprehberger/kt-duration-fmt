package com.philiprehberger.durationfmt

import kotlin.time.Duration

/**
 * A structured breakdown of a [Duration] into individual time units.
 *
 * All fields represent the absolute (non-negative) portion of each unit.
 * For example, a duration of 1 day, 2 hours, 3 minutes, 4 seconds, and 500 milliseconds
 * yields `DurationComponents(days=1, hours=2, minutes=3, seconds=4, millis=500)`.
 *
 * @property days the number of whole days
 * @property hours the remaining hours (0..23)
 * @property minutes the remaining minutes (0..59)
 * @property seconds the remaining seconds (0..59)
 * @property millis the remaining milliseconds (0..999)
 */
public data class DurationComponents(
    val days: Long,
    val hours: Int,
    val minutes: Int,
    val seconds: Int,
    val millis: Int,
)

/**
 * Decomposes this [Duration] into its constituent time units.
 *
 * Returns a [DurationComponents] with the absolute breakdown of this duration
 * into days, hours, minutes, seconds, and milliseconds.
 *
 * ```kotlin
 * val c = (1.days + 2.hours + 3.minutes + 4.seconds + 500.milliseconds).components()
 * // DurationComponents(days=1, hours=2, minutes=3, seconds=4, millis=500)
 * ```
 *
 * @return a [DurationComponents] representing the structured breakdown
 */
public fun Duration.components(): DurationComponents {
    val totalMs = kotlin.math.abs(this.inWholeMilliseconds)
    val d = totalMs / 86_400_000
    val h = ((totalMs % 86_400_000) / 3_600_000).toInt()
    val m = ((totalMs % 3_600_000) / 60_000).toInt()
    val s = ((totalMs % 60_000) / 1_000).toInt()
    val ms = (totalMs % 1_000).toInt()
    return DurationComponents(days = d, hours = h, minutes = m, seconds = s, millis = ms)
}
