val gameRegex = Regex("Game (\\d+):")

private fun part1(input: List<String>): List<Int> {
    return input.map { line ->
        val gameNumber = gameRegex.find(line)!!.groupValues[1]
        val dice: List<Pair<Int, String>> = line.dropWhile { it != ':' }
            .drop(1)
            .split(";")
            .flatMap {
                it.split(",").map {
                    it.split(" ")
                        .let { Pair(it[1].toInt(), it[2]) }
                }
            }
        if (
            dice.filter { it.second == "red" }.maxOf { it.first } <= 12 &&
            dice.filter { it.second == "green" }.maxOf { it.first } <= 13 &&
            dice.filter { it.second == "blue" }.maxOf { it.first } <= 14
        ) {
            return@map gameNumber.toInt()
        } else return@map null
    }.filterNotNull()
}

private fun part2(input: List<String>): Int {
    return input.sumOf { line ->
        val dice: List<Pair<Int, String>> = line.dropWhile { it != ':' }
            .drop(1)
            .split(";")
            .flatMap {
                it.split(",").map {
                    it.split(" ")
                        .let { Pair(it[1].toInt(), it[2]) }
                }
            }

            return@sumOf  dice.filter { it.second == "red" }.maxOf { it.first } *
                    dice.filter { it.second == "green" }.maxOf { it.first }  *
                    dice.filter { it.second == "blue" }.maxOf { it.first }

    }
}

fun main() {
    val testInput = readInputLines("Day02-test-1")
    val input = readInputLines("Day02")


    println(part1(testInput))
    check(part1(testInput) == listOf(1, 2, 5))
    println(part1(input).sum())


    println(part2(testInput))
    check(part2(testInput) == 2286)
    println(part2(input))
}