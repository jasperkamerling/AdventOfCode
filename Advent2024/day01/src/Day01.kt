class Day01(fileName: String) {
    private val input = fileFromResources(fileName).readLines()

    fun part1(): Int {
        return input.size
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day01Test = Day01("test.txt")
    val day01 = Day01("test.txt")


    check(day01Test.part1() == 0)
    println(day01.part1())

    check(day01Test.part2() == 1)
    println(day01.part2())
}