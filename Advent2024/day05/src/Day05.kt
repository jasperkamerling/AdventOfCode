class Day05(fileName: String) {
    private val input = fileFromResources(fileName).readText()
        .split("\n\n").let { it[0] to it[1] }

    private val order = input.first.split('\n')
        .map { line -> line.split('|').let { it[0].toInt() to it[1].toInt() } }

    private val updates = input.second.split('\n')
        .map { line -> line.split(',').map { it.toInt() } }

    fun part1(): Int {
        return updates
            .filter { isInOrder(it) }
            .sumOf { it[it.size / 2] }
    }

    fun isInOrder(update: List<Int>): Boolean {
        update.forEachIndexed { i, number ->
            val shouldNotFollow = order.filter { it.first == number }.map { it.second }
            val followingNumbers = update.slice(0..i)
            if(followingNumbers.any { next -> shouldNotFollow.contains(next) }) {
                return@isInOrder false
            }
        }
        return true
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day05Test = Day05("test05.txt")
    val day05 = Day05("real05.txt")


    check(day05Test.part1() == 143)
    println(day05.part1())

    check(day05Test.part2() == 1)
    println(day05.part2())
}