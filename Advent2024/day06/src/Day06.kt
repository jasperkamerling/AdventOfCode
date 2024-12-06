import Direction.*
import LocationType.*

typealias Map = List<List<Location>>

enum class LocationType {
    EMPTY, CRATE, OBSTACLE, GUARD;
}

data class Location(var type: LocationType, var isVisited: Boolean = false) {
    fun isObstacle(): Boolean {
        return type == CRATE || type == OBSTACLE
    }

    fun display(): Char {
        return when {
            isVisited -> 'X'
            type == EMPTY -> '.'
            type == CRATE -> '#'
            type == OBSTACLE -> 'O'
            type == GUARD -> '^'
            else -> ' '
        }
    }
}

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Guard(var x: Int, var y: Int, var direction: Direction, val map: Map, var steps: Int = 0) {
    fun visitLocation() {
        map[x][y].isVisited = true
    }

    fun isFacingObstacle(): Boolean {
        return getFacingLocation()?.isObstacle() == true
    }

    private fun getFacingLocation(): Location? {
        return when (direction) {
            UP -> map.getOrNull(x - 1)?.getOrNull(y)
            RIGHT -> map.getOrNull(x)?.getOrNull(y + 1)
            DOWN -> map.getOrNull(x + 1)?.getOrNull(y)
            LEFT -> map.getOrNull(x)?.getOrNull(y - 1)
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

    private val baseMap = input
        .map { it.toCharArray().map { ch -> Location(determineLocationType(ch)) } }

    private val startingPosition = baseMap.flatMapIndexed { x, row ->
        row.mapIndexed { y, ch -> if (ch.type == GUARD) Pair(x, y) else null }
    }.filterNotNull().first()

    private fun getMap(): Map = baseMap.map { it.map { it.copy() } }

    private fun determineLocationType(char: Char): LocationType {
        return when (char) {
            '#' -> CRATE
            '^' -> GUARD
            else -> EMPTY
        }
    }

    private fun getGuard(map: Map): Guard {
        return Guard(startingPosition.first, startingPosition.second, UP, map)
    }

    fun part1(): Int {
        return getGuard(getMap())
            .also { walkMap(it) }
            .map.sumOf { row -> row.count { it.isVisited } }
    }

    private fun walkMap(guard: Guard): Guard {
        do {
            guard.visitLocation()
            if (guard.isFacingObstacle()) {
                guard.changeDirection()
            }

            guard.move()
        } while (guard.inBounds())

        return guard
    }

    fun part2(): Int {
        return getMap().asSequence()
            .flatMapIndexed { xIndex, x ->
                x.filterNot { it.type == CRATE }
                    .mapIndexed { yIndex, _ ->
                        getMap().also { it[xIndex][yIndex].apply { type = OBSTACLE } }
                    }

            }.count { isInfinite(getGuard(it)) }
    }

    private fun isInfinite(guard: Guard): Boolean {
        do {
            if (guard.steps > 10000) {
                return true
            }
            if (guard.isFacingObstacle()) {
                guard.changeDirection()
            }
            guard.move()
        } while (guard.inBounds())

        return false
    }

    private fun Map.print() {
        println()
        this.forEach {
            println(it.map { it.display() }.joinToString(""))
        }
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
