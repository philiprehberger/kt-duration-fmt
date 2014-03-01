# kt-duration-fmt

[![CI](https://github.com/philiprehberger/kt-duration-fmt/actions/workflows/ci.yml/badge.svg)](https://github.com/philiprehberger/kt-duration-fmt/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.philiprehberger/duration-fmt)](https://central.sonatype.com/artifact/com.philiprehberger/duration-fmt)

Human-readable duration formatting and parsing for Kotlin.

## Requirements

- Kotlin 1.9+ / Java 17+

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("com.philiprehberger:duration-fmt:0.1.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.philiprehberger:duration-fmt:0.1.0'
}
```

### Maven

```xml
<dependency>
    <groupId>com.philiprehberger</groupId>
    <artifactId>duration-fmt</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

```kotlin
import com.philiprehberger.durationfmt.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

val duration = 2.hours + 30.minutes

duration.humanize()                        // "2 hours, 30 minutes"
duration.humanize(style = Style.SHORT)     // "2h 30m"
duration.humanize(style = Style.NARROW)    // "2h30m"
duration.humanize(maxUnits = 1)            // "2 hours"
```

### Parsing

```kotlin
parseDuration("2h 30m")           // 2.hours + 30.minutes
parseDuration("1 day, 3 hours")   // 1.days + 3.hours
parseDuration("500ms")            // 500.milliseconds
```

### Relative Time

```kotlin
import kotlin.time.Duration.Companion.seconds

30.seconds.timeAgo()   // "30 seconds ago"
2.hours.timeAgo()      // "2 hours ago"
30.seconds.fromNow()   // "in 30 seconds"
```

## API

| Function / Type | Description |
|-----------------|-------------|
| `Duration.humanize(maxUnits, style)` | Format duration as human-readable string |
| `parseDuration(input)` | Parse human-readable string to Duration |
| `Duration.timeAgo()` | Format as relative past time |
| `Duration.fromNow()` | Format as relative future time |
| `Style.LONG` | Full words: "2 hours, 30 minutes" |
| `Style.SHORT` | Abbreviated: "2h 30m" |
| `Style.NARROW` | Compact: "2h30m" |

## Development

```bash
./gradlew test       # Run tests
./gradlew check      # Run all checks
./gradlew build      # Build JAR
```

## License

MIT
