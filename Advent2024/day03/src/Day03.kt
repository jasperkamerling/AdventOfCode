import kotlin.text.Regex

class Day03(fileName: String) {
    private val input = fileFromResources(fileName).readText()

    private val partOnePattern = Regex("mul\\((\\d+),(\\d+)\\)")
    private val partTwoPattern = Regex("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))")

    fun part1(): Int {
        return partOnePattern.findAll(input)
            .map {it.groupValues }
            .map {Pair(it[1].toInt(), it[2].toInt())}
            .sumOf {it.first * it.second}
    }

    data class Result(val sum: Int = 0, val enabled: Boolean = true)

    fun part2(): Int {
        return partTwoPattern.findAll(input)
            .map {it.groupValues.drop(1).filterNot {it.isEmpty()} }
            .fold(Result()) { r, i -> handleInstruction(r, i)}
            .sum
    }

    fun handleInstruction(result: Result, instruction: List<String>) : Result{
        return if(result.enabled && instruction[0].startsWith("mul")) {
            result.copy(sum = result.sum + instruction[1].toInt() * instruction[2].toInt())
        } else if (instruction.contains("do()")) {
            result.copy(enabled = true)
        } else if (instruction.contains("don't()")) {
            result.copy(enabled = false)
        } else {
            result
        }
    }
}

fun main() {
    val day03Test = Day03("test03.txt")
    val day03 = Day03("real03.txt")


    check(day03Test.part1() == 161)
    println(day03.part1())

    val day03Test2 = Day03("test03_2.txt")
    check(day03Test2.part2() == 48)
    println(day03.part2())
}