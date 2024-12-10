import Direction.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

typealias Map = List<List<Location>>

data class Location(var isCrate: Boolean, var isVisited: Boolean = false)

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Guard(
    var x: Int,
    var y: Int,
    var direction: Direction,
    val map: Map,
    val blockedCoordinates: Pair<Int, Int>? = null,
    var steps: Int = 0
) {
    fun visitLocation() {
        map[x][y].isVisited = true
    }

    fun isPathBlocked(): Boolean {
        return getFacingLocation() || getFacingObstacle()
    }

    private fun getFacingLocation(): Boolean {
        return when (direction) {
            UP -> map.getOrNull(x - 1)?.getOrNull(y)
            RIGHT -> map.getOrNull(x)?.getOrNull(y + 1)
            DOWN -> map.getOrNull(x + 1)?.getOrNull(y)
            LEFT -> map.getOrNull(x)?.getOrNull(y - 1)
        }?.isCrate == true
    }

    private fun getFacingObstacle(): Boolean {
        if (blockedCoordinates == null) return false
        return when (direction) {
            UP -> blockedCoordinates.first == x - 1 && blockedCoordinates.second == y
            RIGHT -> blockedCoordinates.first == x && blockedCoordinates.second == y + 1
            DOWN -> blockedCoordinates.first == x + 1 && blockedCoordinates.second == y
            LEFT -> blockedCoordinates.first == x && blockedCoordinates.second == y - 1
        }
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
        steps++
        when (direction) {
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

    private val map = input
        .map { it.toCharArray().map { ch -> Location(ch == '#') } }

    private val startingPosition = input
        .flatMapIndexed { x, row ->
            row.mapIndexed { y, ch -> if (ch == '^') Pair(x, y) else null }
        }.filterNotNull().first()

    private fun getGuard(map: Map, cords: Pair<Int, Int>? = null): Guard =
        Guard(startingPosition.first, startingPosition.second, UP, map, cords)

    fun part1(): Int {
        return getGuard(map)
            .also { walkMap(it) }
            .map.sumOf { row -> row.count { it.isVisited } }
    }

    private fun walkMap(guard: Guard): Guard {
        do {
            guard.visitLocation()

            if (guard.isPathBlocked()) {
                guard.changeDirection()
            }

            guard.move()
        } while (guard.inBounds())

        return guard
    }

    fun part2(): Int = runBlocking {
        map
            .asSequence()
            .flatMapIndexed { x, row ->
                List(row.filterNot { it.isCrate }.size) { y ->
                    getGuard(map, x to y)
                }
            }.map { async { isInfinite(it) } }
            .count { it.await() }
    }

    private fun isInfinite(guard: Guard): Boolean {
        do {
            if (guard.steps > 10000) {
                return true
            }
            if (guard.isPathBlocked()) {
                guard.changeDirection()
            }
            guard.move()
        } while (guard.inBounds())

        return false
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
