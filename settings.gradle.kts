rootProject.name = "AdventOfCode"

include(
    "Utilities",
    "Advent2015",
    "Advent2020",
    "Advent2021",
    "Advent2023",
)

rootProject.projectDir.listFiles { it.name.startsWith("Advent") }
    .flatMap { yearDir ->
        val year = yearDir.name.takeLast(4)
        yearDir.listFiles { it.name.startsWith("day") }
            .map { it.name.takeLast(2) }
            .map { "Advent${year}:day${it}" }
    }.let { include(it) }
