package com.philiprehberger.durationfmt

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * Parses a human-readable duration string into a [Duration].
 *
 * Supports various formats including:
 * - `"2h 30m"`, `"2h30m"`
 * - `"1 day, 3 hours"`
 * - `"500ms"`
 * - `"90 seconds"`
 * - `"1d 2h 3m 4s 500ms"`
 *
 * Recognized unit names (case-insensitive):
 * - Days: `d`, `day`, `days`
 * - Hours: `h`, `hr`, `hrs`, `hour`, `hours`
 * - Minutes: `m`, `min`, `mins`, `minute`, `minutes`
 * - Seconds: `s`, `sec`, `secs`, `second`, `seconds`
 * - Milliseconds: `ms`, `millisecond`, `milliseconds`
 *
 * @param input the duration string to parse
 * @return the parsed [Duration]
 * @throws IllegalArgumentException if the input cannot be parsed
 */
fun parseDuration(input: String): Duration {
    val trimmed = input.trim()
    require(trimmed.isNotEmpty()) { "Duration string must not be empty" }

    val pattern = Regex("""(\d+)\s*(ms|milliseconds?|d|days?|h|hrs?|hours?|m|mins?|minutes?|s|secs?|seconds?)""", RegexOption.IGNORE_CASE)
    val matches = pattern.findAll(trimmed).toList()

    require(matches.isNotEmpty()) { "Unable to parse duration from: $input" }

    var total = Duration.ZERO
    for (match in matches) {
        val value = match.groupValues[1].toLong()
        val unit = match.groupValues[2].lowercase()
        total += when (unit) {
            "d", "day", "days" -> value.days
            "h", "hr", "hrs", "hour", "hours" -> value.hours
            "m", "min", "mins", "minute", "minutes" -> value.minutes
            "s", "sec", "secs", "second", "seconds" -> value.seconds
            "ms", "millisecond", "milliseconds" -> value.milliseconds
            else -> throw IllegalArgumentException("Unknown duration unit: $unit")
        }
    }

    return total
}
