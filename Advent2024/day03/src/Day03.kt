import kotlin.text.Regex

class Day03(fileName: String) {
    private val input = fileFromResources(fileName).readText()

    private val pattern = Regex("mul\\((\\d+),(\\d+)\\)")

    fun part1(): Int {
        return pattern.findAll(input)
            .map {it.groupValues }
            .map {Pair(it[1].toInt(), it[2].toInt())}
            .sumOf {it.first * it.second}
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day03Test = Day03("test03.txt")
    val day03 = Day03("real03.txt")


    check(day03Test.part1() == 161)
    println(day03.part1())

    check(day03Test.part2() == 1)
    println(day03.part2())
}