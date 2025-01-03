class Day10(fileName: String) {
    private val cords: List<Location<Int>> = fileFromResources(fileName).readLines()
        .filterNot { it.isEmpty() }
        .flatMapIndexed { x, row ->
            row.mapIndexed { y, char -> Location(char, x toc y) }
        }
        .filterNot { it.char == '.' }
        .map { Location(it.char.digitToInt(), it.cords) }

    fun part1(): Int {
        cords.printMap()
        return cords.filter { it.char == 0 }
            .sumOf { find9(it).size }
    }

    fun find9(loc: Location<Int>): Set<Cords> {
        if (loc.char == 9) return setOf(loc.cords)
        return cords.findNeighbors(loc)
            .filter { it.char == loc.char + 1 }
            .flatMap { find9(it) }
            .toSet()
    }


    fun part2(): Int {
        cords.printMap()
        return cords.filter { it.char == 0 }
            .sumOf { distinctTrails(it, listOf(it.cords)).size }
    }

    fun distinctTrails(loc: Location<Int>, route: List<Cords>): Set<List<Cords>> {
        if (loc.char == 9) return setOf(route + loc.cords)
        return cords.findNeighbors(loc)
            .filter { it.char == loc.char + 1 }
            .flatMap { distinctTrails(it, route + it.cords) }
            .toSet()
    }
}


fun main() {
    val input = Day10("real10.txt")

    check(Day10("test10-1.txt").part1() == 2)
    check(Day10("test10-2.txt").part1() == 4)
    check(Day10("test10-3.txt").part1() == 3)
    check(Day10("test10-4.txt").part1() == 36)
    println(input.part1())

    check(Day10("test10-5.txt").part2() == 3)
    check(Day10("test10-6.txt").part2() == 13)
    check(Day10("test10-7.txt").part2() == 227)
    check(Day10("test10-8.txt").part2() == 81)
    println(input.part2())
}
