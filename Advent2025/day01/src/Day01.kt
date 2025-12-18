class Day01(fileName: String) {
    enum class Direction { L, R }

    private val input = fileFromResources(fileName).readLines()
        .map { Direction.valueOf(it.first().toString()) to it.drop(1).toInt() }

    fun part1(): Int =
        input.runningFold(50) { dial, (direction, amount) ->
            when (direction) {
                Direction.R -> (dial + amount) % 100
                Direction.L -> (100 + (dial - amount)) % 100
            }
        }.count { it == 0 }

    fun part2Simple(): Int =
        input.flatMap { (direction, amount) ->
            when (direction) {
                Direction.R -> List(amount) { 1 }
                Direction.L -> List(amount) { -1 }
            }
        }.runningFold(50) { dial, turn -> (dial + turn) % 100 }
            .count { it == 0 }

    /** This works fine with test data but not with real data */
    fun part2(): Int =
        input.fold(50 to 0) { (dial, zeros), (direction, amount) ->
            when (direction) {
                Direction.R -> (dial + amount) % 100 to (zeros + ((dial + amount) / 100))
                Direction.L -> {
                    if ((dial - amount) > 0) (dial - amount) % 100 to zeros
                    else if (dial == 0) (100 + (dial - amount)) % 100 to (zeros + -((dial - amount) / 100))
                    else (10000 + (dial - amount)) % 100 to (zeros + -((dial - amount) / 100)) + 1
                }

            }
        }.second
}

fun main() {
    val testInput = Day01("test01.txt")
    val testInput2 = Day01("test01_2.txt")
    val input = Day01("real01.txt")


    check(testInput.part1() == 3)
    println(input.part1())

    check(testInput.part2Simple() == 6)
    check(testInput2.part2Simple() == 10)
    println(input.part2Simple())
    check(testInput.part2() == 6)
    check(testInput2.part2() == 10)
    println(input.part2())
}
