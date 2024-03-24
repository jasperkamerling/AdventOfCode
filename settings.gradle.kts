plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.8.0")
}

rootProject.name = "AdventOfCode"

include(
    "Utilities",
    "Advent2015",
    "Advent2020",
    "Advent2021",
    "Advent2022",
    "Advent2023",
)
