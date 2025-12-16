rootProject.name = "AdventOfCode"

include(
    "Utilities",
    "Advent2015",
    "Advent2020",
    "Advent2021",
    "Advent2023",
)

mapOf(
    2015 to listOf(9),
    2022 to listOf(1),
    2024 to listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11),
    2025 to listOf(1)
).flatMap { mapEntry ->
    mapEntry.value
        .map { it.toString().padStart(2, '0') }
        .map { day -> "Advent${mapEntry.key}:day${day}" }
}.let { include(it) }
