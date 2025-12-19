class Day02(fileName: String) {
    private val input = fileFromResources(fileName)
        .readText()
        .trim()
        .split(",")
        .map { it.split('-').let { it.first().toLong().rangeTo(it.last().toLong()) } }

    fun part1(): Long {
        val funnyNumbers = (0..99999)
            .map { it.toString().repeat(2).toLong() }
        return input
            .flatMap { range -> funnyNumbers.filter { range.contains(it) } }
            .sum()
    }

    fun part2(): Long {
        val maxNumber = input.maxOf { it.last }
        // Take half of the biggest number in the set
        val biggestRepeatableNumber = maxNumber.toString().take(maxNumber.toString().length / 2).toLong()
        val funnyNumbers =
            (1..biggestRepeatableNumber)
                .asSequence()
                .map { it.toString() }
                .flatMap { number -> (2..9).map { number.repeat(it) } }
                .mapNotNull { it.toLongOrNull() }
                .toSet()
        return input.sumOf { range ->
            funnyNumbers.filter { range.contains(it) }.sum()
        }
    }
}

fun main() {
    val testInput = Day02("test02.txt")
    val input = Day02("real02.txt")


    check(testInput.part1() == 1227775554.toLong())
    println(input.part1())

    check(testInput.part2() == 4174379265)
    println(input.part2())
}
