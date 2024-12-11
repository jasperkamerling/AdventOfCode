data class Equation(val total: Long, val numbers: List<Int>)

class Day07(fileName: String) {
    private val input: List<Equation> = fileFromResources(fileName).readLines()
        .map { it.split(": ") }
        .map { it[0].toLong() to it[1].split(" ").map { it.toInt() } }
        .map { Equation(it.first, it.second) }

    fun part1(): Long {
        return input.filter {
            isValid1(it.total, it.numbers.first().toLong(), it.numbers.drop(1))
        }.sumOf { it.total }
    }

    fun isValid1(total: Long, sum: Long, numbers: List<Int>): Boolean {
        if (sum > total) return false
        if (numbers.isEmpty()) return sum == total
        return isValid1(total, sum + numbers.first(), numbers.drop(1)) || // plus
                isValid1(total, sum * numbers.first(), numbers.drop(1)) // multiply
    }

    fun part2(): Long {
        return input.filter {
            isValid2(
                it.total,
                it.numbers.first().toLong(),
                it.numbers.drop(1)
            )
        }.sumOf { it.total }
    }

    fun isValid2(total: Long, sum: Long, numbers: List<Int>): Boolean {
        if (sum > total) return false
        if (numbers.isEmpty()) return sum == total
        return isValid2(total, sum + numbers.first(), numbers.drop(1)) || // plus
                isValid2(total, sum * numbers.first(), numbers.drop(1)) || // plus
                isValid2(total, "${sum}${numbers.first()}".toLong(), numbers.drop(1)) // concat
    }
}

fun main() {
    val day07Test = Day07("test07.txt")
    val day07 = Day07("real07.txt")

    check(day07Test.part1() == 3749L)
    println(day07.part1())

    check(day07Test.part2() == 11387L)
    println(day07.part2())
}
