class Year2025Day07(fileName: String) {
    private val input = fileFromResources(fileName).readLines()

    fun part1(): Int {
        return input.size
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val testInput = Year2025Day07("test-2025-07.txt")
    val input = Year2025Day07("real-2025-07.txt")


    check(testInput.part1() == 0)
    println(input.part1())

    check(testInput.part2() == 1)
    println(input.part2())
}
