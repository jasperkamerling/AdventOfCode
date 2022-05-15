fun main() {
    fun part1(input: List<String>): Int {

        return input.map { it.split('x').map { it.toInt() }.sorted() }.map {
            val l = it[0]
            val w = it[1]
            val h = it[2]
            ((2 * l * w) + (2 * w * h) + (2 * h * l)) + (it[0] * it[1])
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split('x').map { it.toInt() }.sorted() }.map {
            ((it[0] * 2) + (it[1] * 2) + (it[0]*it[1]*it[2]))
        }.sum()
    }
    // test if implementation meets criteria from the description, like:
    check(part1(listOf("2x3x4")) == 58)
    check(part2(listOf("2x3x4")) == 34)
    check(part2(listOf("1x1x10")) == 14)

    val input = readInputLines("Advent2015", "Day02")
    println(part1(input))
    println(part2(input))
}