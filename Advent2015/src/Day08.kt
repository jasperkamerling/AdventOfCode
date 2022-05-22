fun main() {

    fun memoryCount(input: String): Int {
        return input
            .drop(1).dropLast(1)
            .replace("\\\"", "\"")
            .replace("\\\\", "\\")
            .replace(Regex("\\\\x([\\da-fA-F]{2})")) { Integer.parseInt(it.groups[1]!!.value, 16).toChar().toString() }
            .length
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { it.length - memoryCount(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { it
            .replace("\\\\", "\\\\\\\\")
            .replace("\\\"", "\\\\\\\\\"")
            .replace(Regex("\\\\x([\\da-fA-F]{2})"), "xxxx")
            .length + 4 - it.length }
    }

    val testInput = readInputLines("Day08_test")
    val input = readInputLines("Day08")

    println(part1(testInput))
    check(part1(testInput) == 12)
    println(part1(input))


    check(part2(testInput) == 19)
    println(part2(input))
}