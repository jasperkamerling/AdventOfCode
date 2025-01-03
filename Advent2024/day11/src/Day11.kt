class Day11(fileName: String) {
    val input = fileFromResources(fileName).readLines().first()
        .split(" ").map { it.toLong() }

    var state: Map<Long, Int> = input.groupingBy { it }.eachCount()

    fun part1(): Int {
        repeat(25) {
            blink()
        }
        return state.values.sum()
    }

    fun part2(): Int {
        repeat(50) {
            blink()
        }
        return state.values.sum()
    }

    fun blink() {
        val newState = mutableMapOf<Long, Int>()
        state.mapKeys { entry -> transformNumber(entry.key) }
            .forEach { t, u ->
                t.forEach {
                    newState[it] = newState[it]?.plus(u) ?: u
                }
            }

        state = newState
    }

    fun transformNumber(number: Long): List<Long> {
        return when {
            number == 0L -> listOf(1)
            number.toString().length % 2 == 0 -> splitLong(number)
            else -> listOf(number * 2024)
        }
    }

    fun splitLong(number: Long): List<Long> {
        return number.toString()
            .let { s -> listOf(s.take(s.length / 2).toLong(), s.takeLast(s.length / 2).toLong()) }
    }
}

fun main() {
    val testInput = Day11("test11.txt")
    val input = Day11("real11.txt")

    check(testInput.part1() == 55312)
    println(input.part1())

    println(input.part2())
}
