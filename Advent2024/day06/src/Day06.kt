import Direction.*

class Location(val isCrate: Boolean, var isVisited: Boolean = false)
enum class Direction { UP, DOWN, LEFT, RIGHT }
data class Position(val x: Int, val y: Int, val direction: Direction)
class Day06(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
        .map { it.toCharArray() }

    private val map: List<List<Location>> = input.map { it.map { char -> Location(char == '#') } }
    private val startingLocation = determineStartingPosition()

    private fun determineStartingPosition(): Position {
        input.forEachIndexed { index, chars ->
            chars.forEachIndexed { charIndex, char ->
                if (char == '^') {
                    return@determineStartingPosition Position(index, charIndex, UP)
                }
            }
        }
        throw Exception("Did not find starting location")
    }

    fun part1(): Int {
        var position = startingLocation
        do {
            val facingLocation = getFacingLocation(position) ?: continue

            if (facingLocation.isCrate) {
            }
        } while (inBounds(position))

        return map.sumOf { row -> row.count { it.isVisited } }
    }

    private fun getCurrentLocation(position: Position): Location {
        return map[position.x][position.y]
    }

    private fun getFacingLocation(position: Position): Location? {
        return when (position.direction) {
            UP -> map.getOrNull(position.x - 1)?.getOrNull(position.y)
            RIGHT -> map.getOrNull(position.x)?.getOrNull(position.y + 1)
            DOWN -> map.getOrNull(position.x - 1)?.getOrNull(position.y)
            LEFT -> map.getOrNull(position.x)?.getOrNull(position.y - 1)
        }
    }

    fun changeDirection(direction: Direction): Direction {
        return when (direction) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }

    fun move(position: Position): Position {
        position.
    }

    fun inBounds(position: Position): Boolean {
        return position.x > input.size || position.y > input.first().size
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day06Test = Day06("test06.txt")
    val day06 = Day06("real06.txt")


    check(day06Test.part1() == 0)
    println(day06.part1())

    check(day06Test.part2() == 1)
    println(day06.part2())
}