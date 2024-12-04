class Coordinates(val x: Int, val y: Int)
class Day04(fileName: String) {
    private val input: List<CharArray> = fileFromResources(fileName).readLines()
        .map { it.toCharArray() }

    fun part1(): Int {
        return input
            .flatMapIndexed { x, chars -> chars.mapIndexed { y, _ -> Coordinates(x, y) } }
            .sumOf { coordinates -> checkXmas(coordinates, input) }
    }

    fun checkXmas(coordinates: Coordinates, matrix: List<CharArray>): Int {
        var count = 0
        // Only check for coordinates that start XMAS
        if (matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y) != 'X') {
            return 0
        }

        // DOWN RIGHT
        if (matrix.getOrNull(coordinates.x + 1)?.getOrNull(coordinates.y + 1) == 'M' &&
            matrix.getOrNull(coordinates.x + 2)?.getOrNull(coordinates.y + 2) == 'A' &&
            matrix.getOrNull(coordinates.x + 3)?.getOrNull(coordinates.y + 3) == 'S'
        ) {
            count++
        }
        // RIGHT
        if (matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y + 1) == 'M' &&
            matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y + 2) == 'A' &&
            matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y + 3) == 'S'
        ) {
            count++
        }
        // UP RIGHT
        if (matrix.getOrNull(coordinates.x - 1)?.getOrNull(coordinates.y + 1) == 'M' &&
            matrix.getOrNull(coordinates.x - 2)?.getOrNull(coordinates.y + 2) == 'A' &&
            matrix.getOrNull(coordinates.x - 3)?.getOrNull(coordinates.y + 3) == 'S'
        ) {
            count++
        }

        // DOWN LEFT
        if (matrix.getOrNull(coordinates.x + 1)?.getOrNull(coordinates.y - 1) == 'M' &&
            matrix.getOrNull(coordinates.x + 2)?.getOrNull(coordinates.y - 2) == 'A' &&
            matrix.getOrNull(coordinates.x + 3)?.getOrNull(coordinates.y - 3) == 'S'
        ) {
            count++
        }
        // LEFT
        if (matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y - 1) == 'M' &&
            matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y - 2) == 'A' &&
            matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y - 3) == 'S'
        ) {
            count++
        }
        // UP LEFT
        if (matrix.getOrNull(coordinates.x - 1)?.getOrNull(coordinates.y - 1) == 'M' &&
            matrix.getOrNull(coordinates.x - 2)?.getOrNull(coordinates.y - 2) == 'A' &&
            matrix.getOrNull(coordinates.x - 3)?.getOrNull(coordinates.y - 3) == 'S'
        ) {
            count++
        }

        // DOWN
        if (matrix.getOrNull(coordinates.x + 1)?.getOrNull(coordinates.y) == 'M' &&
            matrix.getOrNull(coordinates.x + 2)?.getOrNull(coordinates.y) == 'A' &&
            matrix.getOrNull(coordinates.x + 3)?.getOrNull(coordinates.y) == 'S'
        ) {
            count++
        }
        // UP
        if (matrix.getOrNull(coordinates.x - 1)?.getOrNull(coordinates.y) == 'M' &&
            matrix.getOrNull(coordinates.x - 2)?.getOrNull(coordinates.y) == 'A' &&
            matrix.getOrNull(coordinates.x - 3)?.getOrNull(coordinates.y) == 'S'
        ) {
            count++
        }
        return count
    }


    fun part2(): Int {
        return input
            .flatMapIndexed { x, chars -> chars.mapIndexed { y, _ -> Coordinates(x, y) } }
            .sumOf { coordinates -> checkMas(coordinates, input) }
    }

    fun checkMas(coordinates: Coordinates, matrix: List<CharArray>): Int {
        var count = 0
        // Only check for coordinates that start A
        if (matrix.getOrNull(coordinates.x)?.getOrNull(coordinates.y) != 'A') {
            return 0
        }

        // We need characters in all edges
        if (matrix.getOrNull(coordinates.x - 1)?.getOrNull(coordinates.y - 1) == null ||
            matrix.getOrNull(coordinates.x - 1)?.getOrNull(coordinates.y + 1) == null ||
            matrix.getOrNull(coordinates.x + 1)?.getOrNull(coordinates.y - 1) == null ||
            matrix.getOrNull(coordinates.x + 1)?.getOrNull(coordinates.y + 1) == null
        ) {
            return 0;
        }
        val upLeft = matrix[coordinates.x - 1][coordinates.y - 1]
        val upRight = matrix[coordinates.x - 1][coordinates.y + 1]
        val downRight = matrix[coordinates.x + 1][coordinates.y + 1]
        val downLeft = matrix[coordinates.x + 1][coordinates.y - 1]
        // M M
        //  A
        // S S
        if (
            upLeft ==  'M' &&
            upRight == 'M' &&
            downLeft == 'S' &&
            downRight == 'S') {
            count ++
        }
        // S M
        //  A
        // S M
        if (
            upLeft ==  'S' &&
            upRight == 'M' &&
            downLeft == 'S' &&
            downRight == 'M') {
            count ++
        }
        // S S
        //  A
        // M M
        if (
            upLeft ==  'S' &&
            upRight == 'S' &&
            downLeft == 'M' &&
            downRight == 'M') {
            count ++
        }
        // M S
        //  A
        // M S
        if (
            upLeft ==  'M' &&
            upRight == 'S' &&
            downLeft == 'M' &&
            downRight == 'S') {
            count ++
        }

        return count
    }

}

fun main() {
    val day04Test = Day04("test04.txt")
    val day04 = Day04("real04.txt")


    check(day04Test.part1() == 18)
    println(day04.part1())

    check(day04Test.part2() == 9)
    println(day04.part2())
}
