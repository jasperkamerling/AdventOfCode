private val digitRegex = Regex("\\d+")

fun Char.isNotDotOrDigit() =
    this != '.' && !this.isDigit()

private fun part1(input: List<String>): Int {

    // This ensures we don't have to deal with index out of bounds issues
    val framedInput: List<String> =
        listOf(".".repeat(input[0].length + 2)) +
                input.map { ".$it." } +
                listOf(".".repeat(input[0].length + 2))

    var sum = 0
    framedInput.forEachIndexed { lineIndex, row ->
        digitRegex.findAll(row).forEach {
            if (it.range.any { columnIndex ->
                    // Row before
                    framedInput[lineIndex - 1][columnIndex - 1].isNotDotOrDigit() ||
                            framedInput[lineIndex - 1][columnIndex].isNotDotOrDigit() ||
                            framedInput[lineIndex - 1][columnIndex + 1].isNotDotOrDigit() ||
                            // Same Row
                            row[columnIndex - 1].isNotDotOrDigit() ||
                            row[columnIndex + 1].isNotDotOrDigit() ||
                            // Next Row
                            framedInput[lineIndex + 1][columnIndex - 1].isNotDotOrDigit() ||
                            framedInput[lineIndex + 1][columnIndex].isNotDotOrDigit() ||
                            framedInput[lineIndex + 1][columnIndex + 1].isNotDotOrDigit()
                }) {
                sum += it.value.toInt()
            }
        }
    }

    return sum
}

private fun part2(input: List<String>): Int {

    val gears = mutableListOf<Pair<Int, Int>>()
    input.forEachIndexed { lineIndex, line ->
        line.forEachIndexed { rowIndex, char ->
            if (char == '*') gears += Pair(lineIndex, rowIndex)
        }
    }
    println(gears)

    return gears.map { starCord ->
        input.filterIndexed { index, _ ->
            // Filter out only rows before, on or after a gear
            index == starCord.first - 1
                    || index == starCord.first ||
                    index == starCord.first + 1
        }.flatMap { row ->
            // Find al numbers before at or after the gear column
            digitRegex.findAll(row).filter {
                it.range.contains(starCord.second - 1) ||
                        it.range.contains(starCord.second) ||
                        it.range.contains(starCord.second + 1)
            }.map { it.value.toInt() }
        }
    }.filter {
        // Only gears with more than one adjacent number count
        it.size > 1
    }.sumOf {
        // multiply numbers around gear and sum them
        it.reduce { accumulator, element ->
            accumulator * element
        }
    }
}

fun main() {
    val testInput = readInputLines("Day03-test")
    val input = readInputLines("Day03")


    check(part1(testInput) == 4361)
    println(part1(input))


    check(part2(testInput) == 467835)
    println(part2(input))
}