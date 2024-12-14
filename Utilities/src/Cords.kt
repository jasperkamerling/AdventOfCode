data class Location(val char: Char, val cords: Cords)

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

fun List<Cords>.printMap(hit: Char = '#', empty: Char = '.') {
    val maxX = this.maxOf { it.x }
    val maxY = this.maxOf { it.y }
    println()
    (0..maxX).forEach { x ->
        (0..maxY).forEach { y ->
            if(this.any { it.x == x && it.y == y }) {
                print(hit)
            } else {
                print(empty)
            }
        }
        println()
    }
}

fun List<Location>.printMap(empty: Char = '.') {
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

infix fun Int.toc(that: Int): Cords = Cords(this, that)


