import Direction.*

class Location(val isCrate: Boolean, var isVisited: Boolean = false)
enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Guard(var x: Int, var y: Int, var direction: Direction, val map: List<List<Location>>) {

    fun visitLocation() {
        map[x][y].isVisited = true
    }


    fun isFacingCrate(): Boolean {
        return when (direction) {
            UP -> map.getOrNull(x - 1)?.getOrNull(y)
            RIGHT -> map.getOrNull(x)?.getOrNull(y + 1)
            DOWN -> map.getOrNull(x + 1)?.getOrNull(y)
            LEFT -> map.getOrNull(x)?.getOrNull(y - 1)
        }?.isCrate == true
    }

    fun changeDirection() {
        direction = when (direction) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }

    fun move() {
        return when (direction) {
            UP -> x -= 1
            RIGHT -> y += 1
            DOWN -> x += 1
            LEFT -> y -= 1
        }
    }

    fun inBounds(): Boolean {
        return map.getOrNull(x)?.getOrNull(y) != null
    }
}

class Day06(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
        .map { it.toCharArray() }


    private fun getMap(): List<List<Location>> = input.map { it.map { char -> Location(char == '#') } }

    private fun getGuard(): Guard {
        input.forEachIndexed { index, chars ->
            chars.forEachIndexed { charIndex, char ->
                if (char == '^') {
                    return@getGuard Guard(index, charIndex, UP, getMap())
                }
            }
        }
        throw Exception("Did not find starting location")
    }

    fun part1(): Int {
        return getGuard()
            .also { walkMap(it) }
            .map.sumOf { row -> row.count { it.isVisited } }
    }

    private fun walkMap(guard: Guard): Guard {
        do {
            guard.visitLocation()
            if (guard.isFacingCrate()) {
                guard.changeDirection()
            }

            guard.move()
        } while (guard.inBounds())

        return guard
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day06Test = Day06("test06.txt")
    val day06 = Day06("real06.txt")


    check(day06Test.part1() == 41)
    println("Test part 1 complete")
    println(day06.part1())

    check(day06Test.part2() == 6)
    println("Test part 2 complete")
    println(day06.part2())
}