class Year2025Day07(fileName: String) {
    private val tiles = resourceTrimmedLines(fileName)
        .flatMapIndexed { x, row ->
            row.mapIndexed { y, char ->
                Location(char, Cords(x, y))
            }
        }

    private val manifolds = tiles.filter { it.char == '^' }

    fun part1(): Int {
        var splits = 0
        val beams = mutableSetOf<Int>()
        val tilesWithBeams = tiles.toMutableList()

        (0..tiles.maxOf { it.cords.x })
            .forEach { currentX ->
                tiles.filter { it.cords.x == currentX }
                    .forEach { (char, cords) ->
                        if (char == 'S') beams.add(cords.y)
                        if (char == '^' && cords.y in beams) {
                            ++splits
                            beams.remove(cords.y)
                            beams.add(cords.y - 1)
                            beams.add(cords.y + 1)
                        }
                    }

                tilesWithBeams.addAll(beams.map { beam -> Location('|', Cords(currentX, beam)) })
                tilesWithBeams.removeIf { it.char == '.' && it.cords.y in beams }
                tilesWithBeams.filter { it.cords.x == currentX }.printRow()
                println(splits)
            }
        return splits
    }


    fun part2(): Long {
        val hits: MutableMap<Int, Long> = (0..tiles.maxOf { it.cords.y }).associateWith { 0L }.toMutableMap()
        hits[tiles.find { it.char == 'S' }!!.cords.y] = 1
        manifolds.groupBy { it.cords.x }
            .forEach { (_, manifoldsOnRow) ->
                hits.filter { it.value != 0L }
                    .forEach { (y, count) ->
                        manifoldsOnRow.filter { it.cords.y == y }
                            .forEach { (_, cords) ->
                                hits[cords.y - 1] = hits[cords.y - 1]!! + count
                                hits[cords.y + 1] = hits[cords.y + 1]!! + count
                                hits[cords.y] = hits[cords.y]!! - count

                            }
                    }
            }
        return hits.values.sum()
    }
}

fun main() {
    val testInput = Year2025Day07("test-2025-07.txt")
    val input = Year2025Day07("real-2025-07.txt")

    check(testInput.part1() == 21)
    println(input.part1())

    check(testInput.part2() == 40L)
    println(input.part2())
}
