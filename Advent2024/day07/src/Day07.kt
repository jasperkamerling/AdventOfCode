data class Equation(val total: Long, val numbers: List<Int>)

class Day07(fileName: String) {
    private val input: List<Equation> = fileFromResources(fileName).readLines()
        .map { it.split(": ") }
        .map { it[0].toLong() to it[1].split(" ").map { it.toInt() } }
        .map { Equation(it.first, it.second) }

    fun part1(): Long {
        return input.filter {
            isValid(
                it.total,
                it.numbers.first().toLong(),
                it.numbers.drop(1)
            )
        }.sumOf { it.total }
    }

    fun isValid(total: Long, sum: Long, numbers: List<Int>): Boolean {
        if (sum > total) return false
        if (numbers.isEmpty()) return sum == total
        return isValid(total, sum + numbers.first(), numbers.drop(1)) || // plus
                isValid(total, sum * numbers.first(), numbers.drop(1)) // multiply
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day07Test = Day07("test07.txt")
    val day07 = Day07("real07.txt")

    check(day07Test.part1() == 3749L)
    println(day07.part1())

    check(day07Test.part2() == 1)
    println(day07.part2())
}
