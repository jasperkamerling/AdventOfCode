class C(val x: Int, val y: Int)

// valid coordinates
class VCX(val name: String, val m: C, val a: C, val s: C)
class Day04(fileName: String) {

    private val input: List<CharArray> = fileFromResources(fileName).readLines()
        .map { it.toCharArray() }

    private val xmasCoordinates =
        listOf(
            VCX("R", C(0, 1), C(0, 2), C(0, 3)),
            VCX("DR", C(1, 1), C(2, 2), C(3, 3)),
            VCX("D", C(1, 0), C(2, 0), C(3, 0)),
            VCX("DL", C(1, -1), C(2, -2), C(3, -3)),
            VCX("L", C(0, -1), C(0, -2), C(0, -3)),
            VCX("UL", C(-1, -1), C(-2, -2), C(-3, -3)),
            VCX("U", C(-1, 0), C(-2, 0), C(-3, 0)),
            VCX("UR", C(-1, 1), C(-2, 2), C(-3, 3)),
        )

    fun part1(): Int {
        return input
            .flatMapIndexed { x, chars -> chars.mapIndexed { y, _ -> C(x, y) } }
            .sumOf { coordinates -> checkXmas(coordinates, input) }
    }

    fun checkXmas(c: C, matrix: List<CharArray>): Int {
        // Only check for coordinates that start XMAS
        if (matrix.getOrNull(c.x)?.getOrNull(c.y) != 'X') {
            return 0
        }

        return xmasCoordinates.count {
            matrix.getOrNull(c.x + it.m.x)?.getOrNull(c.y + it.m.y) == 'M' &&
                    matrix.getOrNull(c.x + it.a.x)?.getOrNull(c.y + it.a.y) == 'A' &&
                    matrix.getOrNull(c.x + it.s.x)?.getOrNull(c.y + it.s.y) == 'S'
        }
    }


    fun part2(): Int {
        return input
            .flatMapIndexed { x, chars -> chars.mapIndexed { y, _ -> C(x, y) } }
            .sumOf { coordinates -> checkMas(coordinates, input) }
    }

    fun checkMas(coordinates: C, matrix: List<CharArray>): Int {
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
            return 0
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
