package com.philiprehberger.durationfmt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DurationFmtTest {

    @Test
    fun `humanize long style`() {
        val duration = 2.hours + 30.minutes
        assertEquals("2 hours, 30 minutes", duration.humanize())
    }

    @Test
    fun `humanize short style`() {
        val duration = 2.hours + 30.minutes
        assertEquals("2h 30m", duration.humanize(style = Style.SHORT))
    }

    @Test
    fun `humanize narrow style`() {
        val duration = 2.hours + 30.minutes
        assertEquals("2h30m", duration.humanize(style = Style.NARROW))
    }

    @Test
    fun `humanize with maxUnits 1`() {
        val duration = 1.hours + 30.minutes + 45.seconds
        assertEquals("1 hour", duration.humanize(maxUnits = 1))
    }

    @Test
    fun `humanize with maxUnits 3`() {
        val duration = 1.days + 2.hours + 30.minutes
        assertEquals("1 day, 2 hours, 30 minutes", duration.humanize(maxUnits = 3))
    }

    @Test
    fun `humanize zero duration`() {
        assertEquals("0 milliseconds", 0.seconds.humanize())
        assertEquals("0ms", 0.seconds.humanize(style = Style.SHORT))
    }

    @Test
    fun `humanize singular units`() {
        assertEquals("1 day", 1.days.humanize(maxUnits = 1))
        assertEquals("1 hour", 1.hours.humanize(maxUnits = 1))
        assertEquals("1 minute", 1.minutes.humanize(maxUnits = 1))
        assertEquals("1 second", 1.seconds.humanize(maxUnits = 1))
    }

    @Test
    fun `humanize milliseconds`() {
        assertEquals("500 milliseconds", 500.milliseconds.humanize())
    }

    @Test
    fun `parseDuration hours and minutes`() {
        assertEquals(2.hours + 30.minutes, parseDuration("2h 30m"))
    }

    @Test
    fun `parseDuration compact format`() {
        assertEquals(2.hours + 30.minutes, parseDuration("2h30m"))
    }

    @Test
    fun `parseDuration long words`() {
        assertEquals(1.days + 3.hours, parseDuration("1 day, 3 hours"))
    }

    @Test
    fun `parseDuration milliseconds`() {
        assertEquals(500.milliseconds, parseDuration("500ms"))
    }

    @Test
    fun `parseDuration full format`() {
        val expected = 1.days + 2.hours + 3.minutes + 4.seconds + 500.milliseconds
        assertEquals(expected, parseDuration("1d 2h 3m 4s 500ms"))
    }

    @Test
    fun `parseDuration round-trip`() {
        val original = 2.hours + 30.minutes
        val text = original.humanize(style = Style.SHORT)
        val parsed = parseDuration(text)
        assertEquals(original, parsed)
    }

    @Test
    fun `parseDuration empty string fails`() {
        assertFailsWith<IllegalArgumentException> {
            parseDuration("")
        }
    }

    @Test
    fun `parseDuration invalid input fails`() {
        assertFailsWith<IllegalArgumentException> {
            parseDuration("not a duration")
        }
    }

    @Test
    fun `timeAgo formatting`() {
        assertEquals("30 seconds ago", 30.seconds.timeAgo())
        assertEquals("2 hours ago", 2.hours.timeAgo())
        assertEquals("1 day ago", 1.days.timeAgo())
    }

    @Test
    fun `fromNow formatting`() {
        assertEquals("in 30 seconds", 30.seconds.fromNow())
        assertEquals("in 2 hours", 2.hours.fromNow())
        assertEquals("in 1 day", 1.days.fromNow())
    }

    @Test
    fun `timeAgo singular`() {
        assertEquals("1 second ago", 1.seconds.timeAgo())
        assertEquals("1 minute ago", 1.minutes.timeAgo())
    }
}
