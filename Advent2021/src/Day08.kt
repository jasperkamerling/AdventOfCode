fun main() {
    fun decodeDigit(digit: String): Int {
        return when (digit.length) {
            2 -> 1
            4 -> 4
            3 -> 7
            7 -> 8
            else -> -1
        }
    }



    fun part1(input: List<String>): Int {
        return input.flatMap {
            it.substringAfter(" | ").split(" ").map { decodeDigit(it) }
        }.count { it in arrayOf(1, 4, 7, 8) }
    }


//    fun part2(input: List<String>): Int {
//    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day08_test")
    check(part1(testInput) == 26)
//    check(part2(testInput) == 61229)

    val input = readInputLines("Day08")
    println(part1(input))
//    println(part2(input))
}
