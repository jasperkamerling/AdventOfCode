fun main() {
    fun part1(input: List<String>): Int {
        var position = 0
        var depth = 0
        input.map { it.split(" ") }
            .forEach {
                when (it.first()) {
                    "forward" -> position += it.last().toInt()
                    "down" -> depth += it.last().toInt()
                    "up" -> depth -= it.last().toInt()
                }
            }
        return position * depth
    }

    fun part1version2(input: List<String>): Int {
        val list = input.map { it.split(" ") }
            .map { Pair(it.first(), it.last().toInt()) }

        return (list.filter { it.first == "forward" }.sumOf { it.second }) * (list.filter { it.first == "down" }
            .sumOf { it.second } - list.filter { it.first == "up" }.sumOf { it.second })
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var position = 0
        var depth = 0

        input.map { it.split(" ") }
            .map { Pair(it.first(), it.last().toInt()) }
            .forEach {
                when (it.first) {
                    "forward" -> {
                        position += it.second
                        depth += aim * it.second
                    }
                    "down" -> aim += it.second
                    "up" -> aim -= it.second
                }
            }
        return position * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day02_test")
    val input = readInputLines("Day02")
    // Part 1
    check(part1(testInput) == 150)
    println(part1(input))
    // Part 1 version 2
    check(part1version2(testInput) == 150)
    println(part1version2(input))


    check(part2(testInput) == 900)
    println(part2(input))
}
