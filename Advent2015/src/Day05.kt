fun main() {

    fun part1Test(input: String): Boolean {
        return input.matches(Regex(".*([aeiou].*){3}")) &&
                input.matches(Regex(".*(.)\\1.*")) &&
                !input.matches(Regex(".*(ab|cd|pq|xy).*"))
    }

    fun part1(input: List<String>): Int {
        return input.map { part1Test(it) }.filter { it }.size
    }

    fun part2Test(input: String): Boolean {
        return input.matches(Regex(".*(..).*\\1.*")) &&
                input.matches(Regex(".*(.).\\1.*"))
    }

    fun part2(input: List<String>): Int {
        return input.map { part2Test(it) }.filter { it }.size
    }
    // test if implementation meets criteria from the description, like:
    check(part1Test("ugknbfddgicrmopn"))
    check(part1Test("aaa"))
    check(!part1Test("jchzalrnumimnmhp"))
    check(!part1Test("haegwjzuvuyypxyu"))
    check(!part1Test("dvszwmarrgswjxmb"))

    check(part2Test("qjhvhtzxzqqjkmpb"))
    check(part2Test("xxyxx"))
    check(!part2Test("aaa"))
    check(!part2Test("uurcxstgmygtbstg"))
    check(!part2Test("ieodomkazucvgmuy"))


    val input = readInputLines("Advent2015", "Day05")
    println(part1(input))
    println(part2(input))
}