class Year2025Day06(fileName: String) {
    private enum class Operation { PLUS, MULTIPLY }
    private data class Calculation(val operation: Operation, val numbers: List<String>)

    // TODO I need to retain the padding from the input file to solve part B
    private val input = fileFromResources(fileName).readLines()
        .filter { it.isNotBlank() }
        .map { it.trim().split("(\\s)+".toRegex()) }

    private val numbers = input.dropLast(1)
    private val operations = input.last()
    private val calculations =
        operations.map { if (it.trim() == "+") Operation.PLUS else Operation.MULTIPLY }
            .mapIndexed { index, operation ->
                Calculation(operation, numbers.map { it[index] })
            }

    fun part1(): Long =
        calculations.sumOf { (operation, numbers) ->
            when (operation) {
                Operation.PLUS -> numbers.sumOf { it.trim().toLong() }
                Operation.MULTIPLY -> numbers.map { it.trim().toLong() }.fold(1) { acc, number -> acc * number }
            }
        }

    fun part2(): Long =
        calculations.reversed().sumOf { (operation, numbers) ->
            val newNumbers = numbers.maxBy { it.length }
                .indices
                .map { i -> numbers.mapNotNull { it.getOrNull(i) } }
                .reversed().map { it.joinToString("").toInt() }
            when (operation) {
                Operation.PLUS -> newNumbers.sumOf { it.toLong() }
                Operation.MULTIPLY -> newNumbers.map { it.toLong() }.fold(1) { acc, number -> acc * number }
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
