val part1regex = Regex("\\d")
fun part1(input: List<String>): Int {
    return input.map {
        val matches = part1regex.findAll(it)
        return@map (matches.first().value.toInt() * 10) + matches.last().value.toInt()
    }.sum()
}

val part2regex = Regex("(\\d|one|two|three|four|five|six|seven|eight|nine)")

fun stringToNumber(input: String) =
    when(input) {
        "one" -> 1
        "two" -> 2
        "three" -> 3
        "four" -> 4
        "five" -> 5
        "six" -> 6
        "seven" -> 7
        "eight" -> 8
        "nine" -> 9
        else -> input.toInt()
    }
fun part2(input: List<String>): Int {
    return input.map {
        val matches = part2regex.findAll(it)
        val firstMatch = stringToNumber(matches.first().value)
        val lastMatch = stringToNumber(matches.last().value)
        return@map (firstMatch * 10) + lastMatch
    }.sum()
}

fun main() {
    val testInput = readInputLines("Day01-test")
    val input = readInputLines("Day01")

    println(part1(testInput))
    check(part1(testInput) == 142)

    println(part1(input))

    val part2TestInput = readInputLines("Day01-2-test")

    check(part2(part2TestInput) == 281)
    check(part2(listOf("eightwone")) == 81)
    check(part2(listOf("4md")) == 44)
    check(part2(listOf("sixbrqklb347")) == 67)
    println(part2(input))
}