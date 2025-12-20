class Day03(fileName: String) {
    private val input = resourceTrimmedLines(fileName)
        .map { line -> line.map { it.digitToInt() } }

    fun part1(): Int = input.sumOf { findMaxJoltage(it) }

    private fun findMaxJoltage(batteries: List<Int>): Int {
        val biggest = batteries.dropLast(1).max()
        val biggestIndex = batteries.indexOfFirst { it == biggest }
        return "${biggest}${batteries.drop(biggestIndex + 1).max()}".toInt()
    }

    fun part2(): List<Long> = input.parallelStream().map { findMaxSuperJoltage(it) }.toList()

    private fun findMaxSuperJoltage(batteries: List<Int>): Long {
        var max = batteries
        do {
            max = (0..<max.size)
                .asSequence()
                .map { without -> max.filterIndexed { index, _ -> index != without } }
                .maxBy { it.joinToString("").toBigInteger() }
        } while (max.size != 12)
        return max.joinToString("").toLong()
    }

}

fun main() {
    val testInput = Day03("test03.txt")
    val input = Day03("real03.txt")


    check(testInput.part1() == 357)
    println(input.part1())

    check(testInput.part2() == listOf(987654321111, 811111111119, 434234234278, 888911112111))
    check(testInput.part2().sum() == 3121910778619)
    println(input.part2().sum())
}
