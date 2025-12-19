import java.lang.System.lineSeparator

class Year2025Day05(fileName: String) {
    private val input = fileFromResources(fileName).readText().split(lineSeparator().repeat(2))

    private val safeRanges = input.first().trim().split(lineSeparator())
        .map { it.split('-').let { it.first().toLong()..it.last().toLong() } }.sortedBy { it.first }
        .toSet()
    private val ids = input.last().trim().split(lineSeparator()).map { it.toLong() }

    fun part1(): Int =
        ids.count { id -> safeRanges.any { it.contains(id) } }


    fun part2Slow(): Long =
        (safeRanges.minOf { it.first }..safeRanges.maxOf { it.last })
            .countLong { id -> safeRanges.any { it.contains(id) } }

    fun part2MemoryHungry(): Int =
        safeRanges.flatMap { it.toSet() }.toSet().size

    fun part2(): Long {
        val distinctRanges = mutableSetOf<LongRange>()
        safeRanges.forEach { range ->
            if (distinctRanges.any { it.contains(range.first) || it.contains(range.last) }) {
                val overlapping = distinctRanges.first { it.contains(range.first) || it.contains(range.last) }
                val start = listOf(range.first, overlapping.first).min()
                val end = listOf(range.last, overlapping.last).max()
                distinctRanges.remove(overlapping)
                distinctRanges.add(start..end)
            } else {
                distinctRanges.add(range)
            }
        }

        return distinctRanges.sumOf { it.last - it.first + 1 }
    }

    private inline fun <T> Iterable<T>.countLong(predicate: (T) -> Boolean): Long {
        var count = 0L
        for (element in this) if (predicate(element)) ++count
        return count
    }
}

fun main() {
    val testInput = Year2025Day05("test05.txt")
    val input = Year2025Day05("real05.txt")


    check(testInput.part1() == 3)
    println(input.part1())

    check(testInput.part2Slow() == 14L)
    check(testInput.part2MemoryHungry() == 14)
    check(testInput.part2() == 14L)
    println("part 2 test complete")
    println(input.part2())
}
