package com.philiprehberger.durationfmt

/**
 * Output style for humanized duration strings.
 *
 * - [LONG]: Full words separated by commas, e.g. `"2 hours, 30 minutes"`
 * - [SHORT]: Abbreviated units separated by spaces, e.g. `"2h 30m"`
 * - [NARROW]: Abbreviated units with no separator, e.g. `"2h30m"`
 */
enum class Style {
    /** Full word units: "2 hours, 30 minutes" */
    LONG,
    /** Abbreviated units with space separator: "2h 30m" */
    SHORT,
    /** Abbreviated units with no separator: "2h30m" */
    NARROW,
}
