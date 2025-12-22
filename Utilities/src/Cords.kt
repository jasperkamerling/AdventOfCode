data class Location<C>(val char: C, val cords: Cords)

data class Cords(val x: Int, val y: Int) {
    operator fun plus(other: Cords) =
        this.x + other.x toc this.y + other.y

    operator fun minus(other: Cords) =
        this.x - other.x toc this.y - other.y

    operator fun times(other: Int) =
        this.x * other toc this.y * other

    fun diff(other: Cords): Cords {
        return this.x - other.x toc this.y - other.y
    }
}

/**
 * Everything in the 4 directions (no diagonals)
 */
fun <C> List<Location<C>>.findNeighbours(source: Cords): List<Location<C>> {
    val neighbours = setOf(
        Cords(source.x - 1, source.y),
        Cords(source.x + 1, source.y),
        Cords(source.x, source.y - 1),
        Cords(source.x, source.y + 1)
    )
    return this.filter { neighbours.contains(it.cords) }
}

/**
 * Everything the surrounding 8 tiles (with diagonals)
 */
fun <C> Collection<Location<C>>.findSurrounding(source: Cords): List<Location<C>> {
    val neighbours = setOf(
        Cords(source.x - 1, source.y - 1),
        Cords(source.x - 1, source.y),
        Cords(source.x - 1, source.y + 1),
        Cords(source.x + 1, source.y - 1),
        Cords(source.x + 1, source.y),
        Cords(source.x + 1, source.y + 1),
        Cords(source.x, source.y - 1),
        Cords(source.x, source.y + 1)
    )
    return this.filter { neighbours.contains(it.cords) }
}

fun List<Cords>.printMap(hit: Char = '#', empty: Char = '.') {
    val maxX = this.maxOf { it.x }
    val maxY = this.maxOf { it.y }
    println()
    (0..maxX).forEach { x ->
        (0..maxY).forEach { y ->
            if (this.any { it.x == x && it.y == y }) {
                print(hit)
            } else {
                print(empty)
            }
        }
        println()
    }
}

fun List<Location<*>>.printMap(empty: Char = '.') {
    val maxX = this.maxOf { it.cords.x }
    val maxY = this.maxOf { it.cords.y }
    println()
    (0..maxX).forEach { x ->
        (0..maxY).forEach { y ->
            print(this.find { it.cords.x == x && it.cords.y == y }?.char ?: empty)
        }
        println()
    }
}

fun List<Location<Char>>.printRow(empty: Char = '.') {
    val maxY = this.maxOf { it.cords.y }
    print((0..maxY).map { y -> this.find { it.cords.y == y }?.char ?: empty }.toCharArray())
}

infix fun Int.toc(that: Int): Cords = Cords(this, that)

