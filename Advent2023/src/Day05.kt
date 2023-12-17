import kotlin.math.abs

fun main() {
    val testInput = readInputFile("Day05-test")
    val input = readInputFile("Day05")


    check(part1(testInput) == 35L)
    println(part1(input))


    check(part2(testInput) == 0)
    println(part2(input))
}

private fun part1(input: String): Long {
    val chunks = input.split("\n\n")
    val seeds: List<Long> = chunks[0]
        .removePrefix("seeds: ")
        .split(" ")
        .map { it.toLong() }

    val processors = chunks.drop(1)
        .map { processInput(it) }

    return seeds.map {
        var location = it
        for (map in processors) {
            val mapper: RangeMapping? = map.find { it.source.contains(location) }
            if(mapper != null) {
                location += mapper.delta
            }
        }
        return@map location
    }.min()
}


class BasicMapping(val destination: Long, val source: Long, val range: Long)
class RangeMapping(val source: LongRange, val delta: Long)

fun processInput(input: String) =
    input.split("\n")
        .drop(1)
        .map { it.split(" ") }
        .map { BasicMapping(it[0].toLong(), it[1].toLong(), it[2].toLong()) }
        .map {
            RangeMapping(
                it.source..<it.source + it.range,
                it.destination - it.source
            )
        }

private fun part2(input: String): Int {
    return input.length
}