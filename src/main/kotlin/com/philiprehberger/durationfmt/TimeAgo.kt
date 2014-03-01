package com.philiprehberger.durationfmt

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Formats this [Duration] as a relative past time string.
 *
 * ```kotlin
 * 30.seconds.timeAgo()  // "30 seconds ago"
 * 2.hours.timeAgo()     // "2 hours ago"
 * 1.days.timeAgo()      // "1 day ago"
 * ```
 *
 * @return a human-readable "X ago" string
 */
fun Duration.timeAgo(): String {
    val ms = this.inWholeMilliseconds
    if (ms < 0) return this.fromNow()

    return "${formatRelative(ms)} ago"
}

/**
 * Formats this [Duration] as a relative future time string.
 *
 * ```kotlin
 * 30.seconds.fromNow()  // "in 30 seconds"
 * 2.hours.fromNow()     // "in 2 hours"
 * ```
 *
 * @return a human-readable "in X" string
 */
fun Duration.fromNow(): String {
    val ms = kotlin.math.abs(this.inWholeMilliseconds)
    return "in ${formatRelative(ms)}"
}

private fun formatRelative(ms: Long): String = when {
    ms < 1_000 -> pluralize(ms, "millisecond")
    ms < 60_000 -> pluralize(ms / 1_000, "second")
    ms < 3_600_000 -> pluralize(ms / 60_000, "minute")
    ms < 86_400_000 -> pluralize(ms / 3_600_000, "hour")
    else -> pluralize(ms / 86_400_000, "day")
}

private fun pluralize(value: Long, unit: String): String =
    if (value == 1L) "$value $unit" else "$value ${unit}s"
