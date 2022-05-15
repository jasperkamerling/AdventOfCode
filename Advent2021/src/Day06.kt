import java.util.*

fun main() {
    fun calculateGrowth(input: List<Int>, days: Int): Long {
        var fishDays: MutableMap<Int, Long> = input.groupingBy { it }.fold(0) { acc: Long, _ -> acc + 1 }.toMutableMap()
        for (i in 0..8) fishDays.putIfAbsent(i, 0L)

        for (i in 1..days ) {
            fishDays = fishDays.mapKeys { it.key - 1 }.toMutableMap()
            fishDays[8] = fishDays[-1]!!
            fishDays[6] = fishDays[6]!! + fishDays[-1]!!
            fishDays.remove(-1)
        }
        return fishDays.map { it.value }.sum()
    }

    fun part1(input: List<Int>) = calculateGrowth(input, 80)


    fun part2(input: List<Int>)  = calculateGrowth(input, 256)

    // test if implementation meets criteria from the description, like:
    val testInput = readInputSplitNumber("Day06_test")
    check(part1(testInput) == 5934L) { "${part1(testInput)} did not match" }
    check(part2(testInput) == 26984457539)

    val input = readInputSplitNumber("Day06")
    println(part1(input))
    println(part2(input))
}
