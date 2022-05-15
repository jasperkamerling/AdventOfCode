fun main() {
    fun part1(input: List<Int>): Int {
        return input.drop(1).filterIndexed { i, s -> s > input[i] }.count()
    }

    fun part2(input: List<Int>): Int {
        return input.drop(3).filterIndexed { i, s -> s > input[i] }.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputNumbers("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputNumbers("Day01")
    println(part1(input))
    println(part2(input))
}
