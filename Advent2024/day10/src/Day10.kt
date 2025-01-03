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
            .sumOf { find9(it).distinct().size }

    }

    fun find9(loc: Location<Int>): List<Cords> {
        if(loc.char == 9) return listOf(loc.cords)
        return cords.findNeighbors(loc)
            .filter { it.char == loc.char + 1  }
            .flatMap { find9(it) }
    }

    fun part2(): Int {
        return cords.hashCode()
    }
}


fun main() {
    val testInput1 = Day10("test10-1.txt")
    val testInput2 = Day10("test10-2.txt")
    val testInput3 = Day10("test10-3.txt")
    val testInput4 = Day10("test10-4.txt")
    val input = Day10("real10.txt")


    check(testInput1.part1() == 2)
    check(testInput2.part1() == 4)
    check(testInput3.part1() == 3)
    check(testInput4.part1() == 36)
    println(input.part1())

    check(testInput1.part2() == 1)
    println(input.part2())
}
