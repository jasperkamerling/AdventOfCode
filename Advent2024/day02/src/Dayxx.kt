class Dayxx(fileName: String) {
    private val input = fileFromResources(fileName).readLines()

    fun part1(): Int {
        return input.size
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val dayxxTest = Dayxx("testxx.txt")
    val dayxx = Dayxx("realxx.txt")


    check(dayxxTest.part1() == 0)
    println(dayxx.part1())

    check(dayxxTest.part2() == 1)
    println(dayxx.part2())
}