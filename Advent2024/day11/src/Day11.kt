class Day11(fileName: String) {
    val input = fileFromResources(fileName).readLines().first()
        .split(" ").map { it.toLong() }

    var state = input

    fun part1(): Int {
        repeat(25) {
            blink()
        }
        return state.count()
    }

    fun blink() {
        state = state.flatMap { transformNumber(it) }
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

    fun part2(): Int {
        return state.count()
    }
}

fun parseTest(s: String) =
    s.split(" ").map { it.toLong() }

fun main() {
    val testInput = Day11("test11.txt")
    val testInputTotal = Day11("test11.txt")
    val input = Day11("real11.txt")

    testInput.blink()
    check(testInput.state == parseTest("253000 1 7"))
    testInput.blink()
    check(testInput.state == parseTest("253 0 2024 14168"))
    testInput.blink()
    check(testInput.state == parseTest("512072 1 20 24 28676032"))
    testInput.blink()
    check(testInput.state == parseTest("512 72 2024 2 0 2 4 2867 6032"))
    testInput.blink()
    check(testInput.state == parseTest("1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32"))
    testInput.blink()
    check(testInput.state == parseTest("2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2"))
    check(testInputTotal.part1() == 55312)
    println(input.part1())

    println(input.part2())
}
