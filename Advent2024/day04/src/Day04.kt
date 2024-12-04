class Day04(fileName: String) {
    private val input = fileFromResources(fileName).readLines()

    fun part1(): Int {
        return input.size
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day04Test = Day04("test04.txt")
    val day04 = Day04("real04.txt")


    check(day04Test.part1() == 18)
    println(day04.part1())

    check(day04Test.part2() == 1)
    println(day04.part2())
}
