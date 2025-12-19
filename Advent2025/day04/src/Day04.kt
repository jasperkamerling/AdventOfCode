class Day04(fileName: String) {
    enum class Tile { EMPTY, PAPER }

    private val tiles = resourceLines(fileName)
        .flatMapIndexed { x, row ->
            row.mapIndexed { y, tile ->
                Location(if (tile == '@') Tile.PAPER else Tile.EMPTY, Cords(x, y))
            }
        }

    fun part1(): Int = tiles.findRemovable().count()


    fun part2(): Int {
        val now = tiles.toMutableList()
        while (now.findRemovable().isNotEmpty()) {
            now.removeAll(now.findRemovable())
        }
        return tiles.size - now.size
    }

    private fun List<Location<Tile>>.findRemovable() =
        filter { it.char == Tile.PAPER }
            .filter { findSurrounding(it.cords).count { it.char == Tile.PAPER } < 4 }
}

fun main() {
    val testInput = Day04("test04.txt")
    val input = Day04("real04.txt")


    check(testInput.part1() == 13)
    println(input.part1())

    check(testInput.part2() == 43)
    println(input.part2())
}
