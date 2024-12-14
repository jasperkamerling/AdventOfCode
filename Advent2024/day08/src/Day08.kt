import kotlin.time.measureTimedValue

data class Location(val char: Char, val cords: Cords)

class Day08(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
    private val locations =
        input
            .flatMapIndexed { x, row ->
                row.mapIndexed { y, char -> Location(char, x toc y) }
            }
            .filterNot { it.char == '.' || it.char == '#' }

    private val locationsPerChar =
        locations
            .groupBy { it.char }
            .mapValues { it.value.map { it.cords } }

    fun part1(): Int =
        locations
            .map { it.cords to determineDiffs(it.cords, locationsPerChar[it.char]!!) }
            .flatMap { (source, diffs) -> diffs.flatMap { listOf(it + source, it - source) } }
            .distinctBy { it }
            .filter { it.x in input.indices && it.y in input.first().indices }
            .also { (locations + it.map { Location('#', it) }).printMap() }
            .size

    private fun determineDiffs(cords: Cords, otherCords: List<Cords>): List<Cords> =
        otherCords
            .filterNot { cords.x == it.x && cords.y == it.y }
            .map { cords.diff(it) }

    // TODO figure out what i'm doing wrong
    fun part2(): Int =
        locations
            .map { it.cords to determineDiffs(it.cords, locationsPerChar[it.char]!!) }
            .map { (source, diffs) -> source to multiplyDiffs(diffs) }
            .flatMap { (source, diffs) -> diffs.flatMap { listOf(source + it, source - it) } }
            .distinctBy { it }
            .filter { it.x in input.indices && it.y in input.first().indices }
            .also { (locations + it.map { Location('#', it) }).printMap() }
            .size

    fun multiplyDiffs(diffs: List<Cords>) =
        diffs.flatMap { cords -> (0..50).map { i -> cords * i } }
}

fun main() {
    val testInput = Day08("test08.txt")
    val input = Day08("real08.txt")

    check(testInput.part1() == 14)
    measureTimedValue { input.part1() }.let { println("${it.value} - ${it.duration}") }

    check(Day08("test08-2.txt").part2() == 9)
    check(Day08("test08-3.txt").part2() == 13)
    check(Day08("test08-4.txt").part2() == 4)
    check(testInput.part2() == 34)
    measureTimedValue { input.part2() }.let { println("${it.value} - ${it.duration}") }
}
