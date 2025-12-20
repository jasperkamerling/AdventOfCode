import kotlin.text.trim

class Year2025Day06(fileName: String) {
    private enum class Operation {
        PLUS, MULTIPLY;

        companion object {
            fun get(s: String): Operation =
                when (s.trim()) {
                    "+" -> PLUS
                    "*" -> MULTIPLY
                    else -> throw IllegalArgumentException()
                }
        }
    }

    private data class Calculation(val operation: Operation, val numbers: List<String>)

    private val input = resourceLines(fileName).filter { it.isNotBlank() }
    private val maxLineLength = input.maxOf { it.length }
    private val lines = input.map { it.padEnd(maxLineLength, ' ') }

    fun part1(): Long {
        val columns = lines.map { it.trim().split("(\\s)+".toRegex()) }

        val numbers = columns.dropLast(1)
        val operations = columns.last()
        val calculations = operations.map { Operation.get(it) }
            .mapIndexed { index, operation ->
                Calculation(operation, numbers.map { it[index] })
            }

        return calculations.sum()
    }

    fun part2(): Long =
        lines.first().indices
            .filter { i -> lines.all { line -> line[i].isWhitespace() } }
            .map { it + 1 } // Skip over the whitespace
            .let { listOf(0) + it + lines.first().length - 1 }
            .windowed(2) { (start, end) -> lines.map { it.substring(start, end) } }
            .map { Calculation(Operation.get(it.last()), it.dropLast(1)) }
            .map { (operation, numbers) ->
                Calculation(
                    operation,
                    numbers.map { it.indices }
                        .maxBy { it.last }
                        .reversed()
                        .map { i -> numbers.map { it.getOrElse(i) { ' ' } }.joinToString("").trim() }
                        .filter { it.isNotBlank() })
            }.sum()


    private fun List<Calculation>.sum() =
        sumOf { (operation, numbers) ->
            when (operation) {
                Operation.PLUS -> numbers.sumOf { it.trim().toLong() }
                Operation.MULTIPLY -> numbers.map { it.trim().toLong() }.fold(1) { acc, number -> acc * number }
            }
        }

}

fun main() {
    val testInput = Year2025Day06("test-2025-06.txt")
    val input = Year2025Day06("real-2025-06.txt")

    check(testInput.part1() == 4277556L)
    println(input.part1())

    check(testInput.part2() == 3263827L)
    println(input.part2())
}
