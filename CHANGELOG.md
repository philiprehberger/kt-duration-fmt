# Changelog

All notable changes to this library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.1.0] - 2026-03-17

### Added
- `Duration.humanize()` extension with `LONG`, `SHORT`, and `NARROW` styles
- `parseDuration()` for parsing human-readable duration strings
- `Duration.timeAgo()` and `Duration.fromNow()` for relative time formatting
- Support for days, hours, minutes, seconds, and milliseconds
- Configurable `maxUnits` to control output granularity
