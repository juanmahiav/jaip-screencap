# JAIP SreenCap

[![Java CI](https://github.com/juanmahiav/jaip-screencap/actions/workflows/gradle.yml/badge.svg)](https://github.com/juanmahiav/jaip-screencap/actions/workflows/gradle.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk&logoColor=white)
![Build](https://img.shields.io/badge/build-gradle-blue?logo=gradle&logoColor=white)
![JUnit](https://img.shields.io/badge/tested_with-JUnit5-green?logo=junit5&logoColor=white)

## Purpose
This project offers basic tools for capturing screenshots. It can take region screenshots and was developed as a small helper for JAIP.

## Structure
- `src/main/java` – application sources.
- `src/test/java` – unit tests.
- `build.gradle` – Gradle build logic.
- `settings.gradle` – project settings.

## What it does and how
The default runnable component is `CaptureWindow`, which opens a small Swing window. When the capture button is clicked, a transparent overlay lets the user draw a rectangle. The selected area is saved as a PNG file in the `captures/` directory. The helper class `DesktopCapture` manages the actual `Robot` screenshot, while `EnvironmentDetector` displays basic system information.

## Tests implemented
Two JUnit tests verify core utilities:
1. `EnvironmentDetectorTest` checks that the operating system and Java details
   reported by `EnvironmentDetector.describe()` match the current JVM.
2. `RegionSelectorTest` ensures that the internal rectangle calculation in
   `RegionSelector` works correctly regardless of point order.

## How to build and run
Execute the following from the project root:
```bash
./gradlew build    # compiles sources and runs tests
./gradlew run      # launches the capture GUI
```
The application needs Java 21, which Gradle will download automatically if it's available on the system.

## How to run tests
Run all tests with:
```bash
./gradlew test
```
