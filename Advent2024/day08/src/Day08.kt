data class Location(val char: Char, val cords: Cords)

class Day08(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
    private val locations = input
        .flatMapIndexed { x, row ->
            row.mapIndexed { y, char -> Location(char, x toc y) }
        }
        .filterNot { it.char == '.' || it.char == '#'}

    private val locationsPerChar = locations.groupBy { it.char }
        .mapValues { it.value.map { it.cords } }

    fun part1(): Int {
        return locations
            .flatMap { determineDiff(it.cords, locationsPerChar[it.char]!!) }
            .distinctBy { it }
            .filter { it.x in input.indices && it.y in input.first().indices }
            .size
    }

    private fun determineDiff(cords: Cords, otherCords: List<Cords>): List<Cords> {
        return otherCords
            .filterNot { cords.x == it.x && cords.y == it.y }
            .map { cords.diff(it) }
            .flatMap { listOf(it + cords, it - cords ) }
    }

    fun part2(): Int {
        return locations.hashCode()
    }
}

fun main() {
    val testInput = Day08("test08.txt")
    val input = Day08("real08.txt")

    check(testInput.part1() == 14)
    println(input.part1())

    check(testInput.part2() == 1)
    println(input.part2())
}
