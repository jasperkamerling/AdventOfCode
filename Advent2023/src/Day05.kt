import java.time.Instant
import java.time.temporal.ChronoUnit

fun main() {
    val testInput = readInputFile("Day05-test")
    val input = readInputFile("Day05")

    check(part1(testInput) == 35L)
    println(part1(input))

    val startTime = Instant.now()
    check(part2(testInput) == 46L)
    println(part2(input))

    println("Time: " + startTime.until(Instant.now(), ChronoUnit.SECONDS))
}

private fun part1(input: String): Long {
    val chunks = input.split("\n\n")
    val seeds: List<Long> = chunks[0]
        .removePrefix("seeds: ")
        .split(" ")
        .map { it.toLong() }

    val processors: List<List<RangeMapping>> = chunks.drop(1)
        .map { processInput(it) }

    return seeds.minOf { seedToLocation(it, processors) }
}

private fun part2(input: String): Long {
    val chunks = input.split("\n\n")
    val seeds: List<LongRange> = chunks[0]
        .removePrefix("seeds: ")
        .split(" ")
        .map { it.toLong() }
        .chunked(2)
        .map { it[0]..<it[0] + it[1] }

    val processors = chunks.drop(1)
        .map { processInput(it) }

    return seeds.minOf { seedRange ->
        println(seedRange)
        seedRange.minOf { seedToLocation(it, processors) }
    }
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

fun seedToLocation(seed: Long, processors: List<List<RangeMapping>>): Long =
    processors.fold(seed) { location: Long, processor: List<RangeMapping> ->
        processor.find { it.source.contains(location) }
            ?.let { location + it.delta } ?: location
    }
