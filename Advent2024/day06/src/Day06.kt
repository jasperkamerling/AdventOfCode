import Direction.*

class Location(val isCrate: Boolean, var isVisited: Boolean = false)
enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Guard(var x: Int, var y: Int, var direction: Direction) {

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
}

class Day06(fileName: String) {
    private val input = fileFromResources(fileName).readLines()
        .map { it.toCharArray() }

    private val map: List<List<Location>> = input.map { it.map { char -> Location(char == '#') } }
    private val startingLocation = determineStartingPosition()

    private fun determineStartingPosition(): Guard {
        input.forEachIndexed { index, chars ->
            chars.forEachIndexed { charIndex, char ->
                if (char == '^') {
                    return@determineStartingPosition Guard(index, charIndex, UP)
                }
            }
        }
        throw Exception("Did not find starting location")
    }

    fun part1(): Int {
        val guard = startingLocation
        do {
            getCurrentLocation(guard).isVisited = true
            val facingLocation = getFacingLocation(guard) ?: break

            if (facingLocation.isCrate) {
                guard.changeDirection()
            }

            guard.move()
        } while (true)

        return map.sumOf { row -> row.count { it.isVisited } }
    }

    private fun getCurrentLocation(guard: Guard): Location {
        return map[guard.x][guard.y]
    }

    private fun getFacingLocation(guard: Guard): Location? {
        return when (guard.direction) {
            UP -> map.getOrNull(guard.x - 1)?.getOrNull(guard.y)
            RIGHT -> map.getOrNull(guard.x)?.getOrNull(guard.y + 1)
            DOWN -> map.getOrNull(guard.x + 1)?.getOrNull(guard.y)
            LEFT -> map.getOrNull(guard.x)?.getOrNull(guard.y - 1)
        }
    }

//    fun inBounds(position: Position): Boolean {
//        return position.x > input.size || position.y > input.first().size
//    }

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

    check(day06Test.part2() == 1)
    println(day06.part2())
}