fun main() {
    fun part1(input: String): Int {
        var vertical = 0
        var horizontal = 0
        val visits = mutableListOf(Pair(vertical, horizontal))

        input.map {
            when (it) {
                '^' -> vertical++
                'v' -> vertical--
                '>' -> horizontal++
                '<' -> horizontal--
            }
            visits.add(Pair(vertical, horizontal))
        }
        return visits.distinct().size
    }

    fun part2(input: String): Int {
        var vertical = 0
        var horizontal = 0
        var robotVertical = 0
        var robotHorizontal = 0
        var realSanta = true
        val visits = mutableListOf(Pair(vertical, horizontal))

        input.map {
            if (realSanta) {
                when (it) {
                    '^' -> vertical++
                    'v' -> vertical--
                    '>' -> horizontal++
                    '<' -> horizontal--
                }
                visits.add(Pair(vertical, horizontal))
            } else {
                when (it) {
                    '^' -> robotVertical++
                    'v' -> robotVertical--
                    '>' -> robotHorizontal++
                    '<' -> robotHorizontal--
                }
                visits.add(Pair(robotVertical, robotHorizontal))
            }
            realSanta = realSanta.not()
        }
        return visits.distinct().size
    }
    // test if implementation meets criteria from the description, like:
    check(part1(">") == 2)
    check(part1("^>v<") == 4)
    check(part1("^v^v^v^v^v") == 2)
    check(part2("^v") == 3)
    check(part2("^>v<") == 3)
    check(part2("^v^v^v^v^v") == 11)

    val input = readInputFile("Advent2015", "Day03")
    println(part1(input))
    println(part2(input))
}