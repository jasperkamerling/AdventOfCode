class Day05(fileName: String) {
    private val input = fileFromResources(fileName).readText()
        .split("\n\n").let { it[0] to it[1] }

    private val order = input.first.split('\n')
        .map { line -> line.split('|').let { it[0].toInt() to it[1].toInt() } }

    private val shouldNotFollowMap =
        order.map { it.first }.distinct()
            .associateWith { number -> order.filter { it.first == number }.map { it.second } }
    private val updates = input.second.split('\n')
        .map { line -> line.split(',').map { it.toInt() } }

    fun part1(): Int {
        return updates
            .filter { isInOrder(it) }
            .sumOf { it[it.size / 2] }
    }

    private fun isInOrder(update: List<Int>): Boolean {
        update.forEachIndexed { i, number ->
            val shouldNotFollow = shouldNotFollowMap[number] ?: return@forEachIndexed
            val followingNumbers = update.slice(0..i)
            if (followingNumbers.any { next -> shouldNotFollow.contains(next) }) {
                return@isInOrder false
            }
        }
        return true
    }

    fun part2(): Int {
        return updates
            .filterNot { isInOrder(it) }
            .parallelStream()
            .map { sort(it) }
            .toList()
            .sumOf { it[it.size / 2] }
    }

    private fun sort(update: List<Int>): List<Int> {
        var shuffledList = update
        var notSorted = true
        while(notSorted) {
            shuffledList = update.shuffled()
            notSorted = !isInOrder(shuffledList)
        }

        return shuffledList
    }
}

fun main() {
    val day05Test = Day05("test05.txt")
    val day05 = Day05("real05.txt")


    check(day05Test.part1() == 143)
    println(day05.part1())

    check(day05Test.part2() == 123)
    println(day05.part2())
}