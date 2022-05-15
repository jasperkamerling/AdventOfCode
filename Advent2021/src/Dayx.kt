fun main() {
    fun part1(input: List<Int>): Int {
        return input.size    }

    fun part2(input: List<Int>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputNumbers("Dayx_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputNumbers("Dayx")
    println(part1(input))
    println(part2(input))
}
