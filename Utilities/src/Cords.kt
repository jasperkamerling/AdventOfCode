data class Cords(val x: Int, val y: Int) {
    operator fun plus(other: Cords) =
        this.x + other.x toc this.y + other.y
    operator fun minus(other: Cords) =
        this.x - other.x toc this.y - other.y
    fun diff(other: Cords): Cords {
        return this.x - other.x toc this.y - other.y
    }
}

infix fun Int.toc(that: Int): Cords = Cords(this, that)


