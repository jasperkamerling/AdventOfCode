private fun part1(input: List<Pair<Int, Long>>): Int =
    input.map { findCorrectSolutions(it.first, it.second) }
        .fold(1) { acc, i -> acc * i }

private fun findCorrectSolutions(time: Int, distance: Long): Int =
    (0..time).filter { warmupSeconds ->
        val runtime = time - warmupSeconds
        (distance - (runtime * warmupSeconds)) < 0
    }.size

private fun part2(time: Long, distance: Long) =
    (0..time).asSequence().filter { (distance - ((time - it) * it)) < 0 }.count()

fun main() {
    val testInput1: List<Pair<Int, Long>> = listOf(
        Pair(7, 9),
        Pair(15, 40),
        Pair(30, 200)
    )

    val input1: List<Pair<Int, Long>> = listOf(
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
    )

    println(findCorrectSolutions(7, 9))
    println(findCorrectSolutions(15, 40))
    println(findCorrectSolutions(30, 200))
    println(part1(testInput1))
    println(part1(input1))

    println(part2(71530, 940200))
    println(part2(999999L, 99999999999L))
}