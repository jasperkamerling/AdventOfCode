import kotlin.math.absoluteValue

data class Level(
    val previous: Int?,
    val increasing: Boolean = true,
    val decreasing: Boolean = true,
    val safe: Boolean = true
)

class Day02(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
        .filterNot { it.isEmpty() }
        .map { line -> line.split(" ").map { it.toInt() } }

    fun part1(): Int {
        return input.count { isRowSafe(it) }
    }

    private fun isRowSafe(row: List<Int>): Boolean {
        return row.fold(Level(null))
        { acc, i -> checkLevel(acc, i) }
            .safe
    }

    private fun checkLevel(level: Level, next: Int): Level {
        return if (!level.safe) {
            level.copy(previous = next)
        } else if (level.previous == null) {
            level.copy(previous = next)
        } else if (((level.previous - next).absoluteValue !in 1..3)) {
            level.copy(safe = false)
        } else if (level.decreasing && level.previous > next) {
            level.copy(previous = next, decreasing = true, increasing = false)
        } else if (level.increasing && level.previous < next) {
            level.copy(previous = next, increasing = true, decreasing = false)
        } else {
            level.copy(previous = next, safe = false)
        }
    }

    fun part2(): Int {
        return input
            .count { checkAnySafeLevels(it) }
    }

    fun checkAnySafeLevels(row: List<Int>): Boolean {
        if (isRowSafe(row)) return true
        return row.mapIndexed { index, _ ->
            row.toMutableList().apply { removeAt(index) }.toList()
        }.any { isRowSafe(it) }
    }

}

fun main() {
    val day02Test = Day02("test02.txt")
    val day02 = Day02("real02.txt")

    check(day02Test.part1() == 2)
    println(day02.part1())

    check(day02Test.part2() == 4)
    println(day02.part2())
}
