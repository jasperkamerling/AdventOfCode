fun main() {
    fun part1(input: String): Int {
        var counter = 0
        var md5 = ""
        while (!md5.startsWith("00000")) {
            md5 = "${input}${counter}".md5()
            counter++
        }
        return counter - 1
    }

    fun part2(input: String): Int {
        var counter = 0
        var md5 = ""
        while (!md5.startsWith("000000")) {
            md5 = "${input}${counter}".md5()
            counter++
        }
        return counter - 1
    }
    // test if implementation meets criteria from the description, like:
    check(part1("abcdef") == 609043)
    check(part1("pqrstuv") == 1048970)

    val input = readInputFile("Advent2015", "Day04")
    println(part1(input))
    println(part2(input))
}