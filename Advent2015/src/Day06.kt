fun main() {

    fun part1(input: List<String>): Int {
        val grid = MutableList(1000) { MutableList(1000) { false } }
        for (line in input) {
            val values = Regex("(toggle |turn \\w+)? ?(\\d+),(\\d+) through (\\d+),(\\d+)")
                .matchEntire(line)?.groupValues
            val action = values?.get(1)!!
            val vStart = values[2].toInt()
            val hStart = values[3].toInt()
            val vEnd = values[4].toInt()
            val hEnd = values[5].toInt()
            if (action == "turn on") {
                for (h in hStart..hEnd) {
                    for (v in vStart..vEnd) {
                        grid[h][v] = true
                    }
                }
            } else if (action == "turn off") {
                for (h in hStart..hEnd) {
                    for (v in vStart..vEnd) {
                        grid[h][v] = false
                    }
                }
            } else if (action == "toggle ") {
                for (h in hStart..hEnd) {
                    for (v in vStart..vEnd) {
                        grid[h][v] = grid[h][v].not()
                    }
                }
            }
        }

        return grid.sumOf { it.count { it } }

    }

    fun part2(input: List<String>): Int {
        val grid = MutableList(1000) { MutableList(1000) { 0 } }
        for (line in input) {
            val values = Regex("(toggle |turn \\w+)? ?(\\d+),(\\d+) through (\\d+),(\\d+)")
                .matchEntire(line)?.groupValues
            val action = values?.get(1)!!
            val vStart = values[2].toInt()
            val hStart = values[3].toInt()
            val vEnd = values[4].toInt()
            val hEnd = values[5].toInt()
            if (action == "turn on") {
                for (h in hStart..hEnd) {
                    for (v in vStart..vEnd) {
                        grid[h][v]++
                    }
                }
            } else if (action == "turn off") {
                for (h in hStart..hEnd) {
                    for (v in vStart..vEnd) {
                        if (grid[h][v] != 0) grid[h][v]--
                    }
                }
            } else if (action == "toggle ") {
                for (h in hStart..hEnd) {
                    for (v in vStart..vEnd) {
                        grid[h][v] += 2
                    }
                }
            }
        }

        return grid.sumOf { it.sum() }

    }
    // test if implementation meets criteria from the description, like:
    check(part1(listOf("turn on 0,0 through 999,999")) == 1000 * 1000)
    check(part1(listOf("toggle 0,0 through 999,0")) == 1000)
    check(part1(listOf("turn on 499,499 through 500,500")) == 4)

    check(part2(listOf("turn on 0,0 through 0,0")) == 1)
    check(part2(listOf("toggle 0,0 through 999,999")) == 2000000)

    val input = readInputLines("Day06")
    println(part1(input))
    println(part2(input))
}