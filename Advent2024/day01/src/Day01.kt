import kotlin.math.absoluteValue

class Day01(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
        .filterNot { it.isEmpty() }

    fun part1(): Int =
         input.map { it.split("   ") }
            .map { Pair(it[0].toInt(), it[1].toInt()) }
            .fold(Pair<MutableList<Int>, MutableList<Int>>(mutableListOf(), mutableListOf()))
            { acc, pair -> Pair((acc.first + pair.first).toMutableList(), (acc.second + pair.second).toMutableList()) }
            .let { Pair(it.first.sorted(), it.second.sorted()) }
            .let { lists -> lists.first.mapIndexed { index, leftLocation -> leftLocation - lists.second[index]  }
                .sumOf { it.absoluteValue } }


    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day01Test = Day01("test01.txt")
    val day01 = Day01("real01.txt")


    check(day01Test.part1() == 11)
    println(day01.part1())

    check(day01Test.part2() == 1)
    println(day01.part2())
}