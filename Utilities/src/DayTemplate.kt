fun part1(input: List<String>): Int {
    return input.size
}

fun part2(input: List<String>): Int {
    return input.size
}

fun main() {
    val testInput = readInputLines("Dayxx-test")
    val input = readInputLines("Dayxx")


    check(part1(testInput) == 0)
    println(part1(input))


    check(part2(testInput) == 0)
    println(part2(input))
}