import kotlin.math.absoluteValue

class Day01(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
        .filterNot { it.isEmpty() }
        .map { it.split("   ") }
        .map { Pair(it[0].toInt(), it[1].toInt()) }
        .fold(Pair<List<Int>, List<Int>>(listOf(), listOf()))
        { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) }

    fun part1(): Int =
        input.let { Pair(it.first.sorted(), it.second.sorted()) }
            .let { lists ->
                lists.first.mapIndexed { index, leftLocation -> leftLocation - lists.second[index] }
                    .sumOf { it.absoluteValue }
            }


    fun part2(): Int =
        input.let { pair ->
            pair.first.map { leftNumber ->
                pair.second.count { it == leftNumber } * leftNumber
            }
        }.sum()
}

fun main() {
    val day01Test = Day01("test01.txt")
    val day01 = Day01("real01.txt")


    check(day01Test.part1() == 11)
    println(day01.part1())

    check(day01Test.part2() == 31)
    println(day01.part2())
}
