import Day08.part1
import kotlin.test.asserter

object Day08 {

    private val regex: Regex = Regex("(\\w+) = \\((\\w+), (\\w+)\\)")

    enum class Direction {
        LEFT,
        RIGHT
    }

    fun part1(input: List<String>): Int {
        val instructions = input[0].toCharArray().map { if (it == 'L') Direction.LEFT else Direction.RIGHT }
        val Map = input.drop(2)
            .map { regex.find(it)!!.groupValues }
            .associate { it[1] to (it[2] to it[3]) }

        var steps = 0
        var instructionIndex = 0
        var currentNode = "AAA"
        do {
            val instruction = instructions[instructionIndex]
            currentNode = when (instruction) {
                Direction.LEFT -> Map[currentNode]!!.first
                Direction.RIGHT -> Map[currentNode]!!.second
            }
            if (instructionIndex == instructions.size - 1) {
                instructionIndex = 0
            } else {
                instructionIndex++
            }
            steps++
        } while (currentNode != "ZZZ")

        return steps
    }

}

fun main() {
    val testInput1 = readInputLines("Day08-test-1")
    val testInput2 = readInputLines("Day08-test-2")
    val input = readInputLines("Day08")

    asserter.assertEquals("1: ", 2, part1(testInput1))
    asserter.assertEquals("2: ", 6, part1(testInput2))
    println(part1(input))

    val testInput3 = readInputLines("Day08-test-3")

}
