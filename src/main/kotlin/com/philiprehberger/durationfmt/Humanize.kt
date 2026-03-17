package com.philiprehberger.durationfmt

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Formats this [Duration] as a human-readable string.
 *
 * Breaks the duration into days, hours, minutes, seconds, and milliseconds,
 * then formats up to [maxUnits] of the largest non-zero units in the given [style].
 *
 * ```kotlin
 * (2.hours + 30.minutes).humanize()                    // "2 hours, 30 minutes"
 * (2.hours + 30.minutes).humanize(style = Style.SHORT) // "2h 30m"
 * (2.hours + 30.minutes).humanize(style = Style.NARROW) // "2h30m"
 * (90.seconds).humanize(maxUnits = 1)                  // "1 minute"
 * ```
 *
 * @param maxUnits the maximum number of time units to include (default: `2`)
 * @param style the output style (default: [Style.LONG])
 * @return a human-readable duration string, or `"0 milliseconds"` / `"0ms"` for zero duration
 */
fun Duration.humanize(maxUnits: Int = 2, style: Style = Style.LONG): String {
    if (this == Duration.ZERO) {
        return when (style) {
            Style.LONG -> "0 milliseconds"
            Style.SHORT -> "0ms"
            Style.NARROW -> "0ms"
        }
    }

    val totalMs = this.inWholeMilliseconds
    val isNegative = totalMs < 0
    val absMs = kotlin.math.abs(totalMs)

    val d = absMs / 86_400_000
    val h = (absMs % 86_400_000) / 3_600_000
    val m = (absMs % 3_600_000) / 60_000
    val s = (absMs % 60_000) / 1_000
    val ms = absMs % 1_000

    val parts = listOf(
        d to "day",
        h to "hour",
        m to "minute",
        s to "second",
        ms to "millisecond",
    ).filter { it.first > 0 }
        .take(maxUnits)

    if (parts.isEmpty()) {
        return when (style) {
            Style.LONG -> "0 milliseconds"
            Style.SHORT -> "0ms"
            Style.NARROW -> "0ms"
        }
    }

    val prefix = if (isNegative) "-" else ""

    val formatted = when (style) {
        Style.LONG -> parts.joinToString(", ") { (value, unit) ->
            if (value == 1L) "$value $unit" else "$value ${unit}s"
        }
        Style.SHORT -> parts.joinToString(" ") { (value, unit) ->
            "$value${abbreviate(unit)}"
        }
        Style.NARROW -> parts.joinToString("") { (value, unit) ->
            "$value${abbreviate(unit)}"
        }
    }

    return "$prefix$formatted"
}

private fun abbreviate(unit: String): String = when (unit) {
    "day" -> "d"
    "hour" -> "h"
    "minute" -> "m"
    "second" -> "s"
    "millisecond" -> "ms"
    else -> unit
}
