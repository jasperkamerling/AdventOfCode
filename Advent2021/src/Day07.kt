import kotlin.math.absoluteValue
import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<Int>): Int {
        val targetPos = input.sorted()[input.size / 2]
        return input.sumOf { (it - targetPos).absoluteValue }
    }

    fun calculateFuel(current: Int, target: Int): Int {
        var fuel = 0
        var currentPos = current
        var step = 1
        while (currentPos != target) {
            fuel += step
            step++
            if (currentPos >= target) currentPos--
            else currentPos++
        }
        return fuel
    }

    fun part2(input: List<Int>): Int {
        val floor = input.average().toInt()
        val floorFuel = input.sumOf { calculateFuel(it, floor) }
        val round = input.average().roundToInt()
        val roundFuel = input.sumOf { calculateFuel(it, round) }
        return arrayOf(roundFuel, floorFuel).minOrNull()!!
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputSplitNumber("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInputSplitNumber("Day07")
    println(part1(input))
    println(part2(input))
}
