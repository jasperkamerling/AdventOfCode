fun main() {
    fun part1(input: String): Int {
        var floor = 0
        for (char in input) {
            if (char == '(') floor++
            else floor--
        }
        return floor
    }

    fun part2(input: String): Int {
        var floor = 0
        for ((index, char) in input.withIndex()) {
            if (char == '(') floor++
            else floor--
            if( floor < 0) return index + 1
        }
        throw Exception("Underground not found")
    }
    // test if implementation meets criteria from the description, like:
    check(part1("(())") == 0)
    check(part1("(()(()(") == 3)
    check(part2(")") == 1)
    check(part2("()())") == 5)

    val input = readInputFile("Advent2015", "Day01")
    println(part1(input))
    println(part2(input))
}