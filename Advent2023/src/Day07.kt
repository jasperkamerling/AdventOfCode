import kotlin.test.asserter

object Day07 {

    class Hand(val cards: String, val score: Int, val rating: String, var index: Int? = null)

    private val orderingMap1: Map<Char, Char> = mapOf(
        'A' to 'A',
        'K' to 'B',
        'Q' to 'C',
        'J' to 'D',
        'T' to 'E',
        '9' to 'F',
        '8' to 'G',
        '7' to 'H',
        '6' to 'I',
        '5' to 'J',
        '4' to 'K',
        '3' to 'L',
        '2' to 'M',
    )

    fun part1(input: List<String>): Int =
        input
            .map { it.split(" ") }
            .map { Hand(it.first(), it.last().toInt(), it.first().stringToScoreChar1()) }
            .sortedByDescending { it.rating }
            .mapIndexed { index, hand -> hand.index = index + 1; hand }
            .sumOf { it.score * it.index!! }


    private fun String.stringToScoreChar1(): String {
        val occurrences = this
            .groupBy { it }
            .map { it.value.size }

        val prefix = when {
            occurrences.max() == 5 -> 'A'
            occurrences.max() == 4 -> 'B'
            occurrences.contains(3) && occurrences.contains(2) -> 'C'
            occurrences.max() == 3 -> 'D'
            occurrences.filter { it == 2 }.size == 2 -> 'E'
            occurrences.max() == 2 -> 'F'
            occurrences.max() == 1 -> 'G'
            else -> 'H'
        }

        return "${prefix}${this.map { char -> orderingMap1[char] }.joinToString("")}"
    }

    private val orderingMap2: Map<Char, Char> = mapOf(
        'A' to 'A',
        'K' to 'B',
        'Q' to 'C',
        'T' to 'E',
        '9' to 'F',
        '8' to 'G',
        '7' to 'H',
        '6' to 'I',
        '5' to 'J',
        '4' to 'K',
        '3' to 'L',
        '2' to 'M',
        'J' to 'Z',
    )

    private const val FIVE_OF_A_KIND = 'A'
    private const val FOUR_OF_A_KIND = 'B'
    private const val FULL_HOUSE = 'C'
    private const val THREE_OF_KIND = 'D'
    private const val TWO_PAIRS = 'E'
    private const val ONE_PAIR = 'F'
    private const val HIGH_CARD = 'G'
    private const val ALL_JOKERS = 'Z'

    fun part2(input: List<String>): Int {
        val hands = input
            .map { it.split(" ") }
            .map { Hand(it.first(), it.last().toInt(), it.first().stringToScoreChar2()) }
            .sortedByDescending { it.rating }
            .mapIndexed { index, hand -> hand.index = index + 1; hand }
        return hands
            .sumOf { it.score * it.index!! }
    }

    private fun String.stringToScoreChar2(): String {
        val occurrences = this
            .filterNot { it == 'J' }
            .groupBy { it }
            .map { it.value.size }
            .sortedDescending()

        var prefix: Char = when {
            occurrences.isEmpty() -> ALL_JOKERS
            occurrences.max() == 5 -> FIVE_OF_A_KIND
            occurrences.max() == 4 -> FOUR_OF_A_KIND
            occurrences.contains(3) && occurrences.contains(2) -> FULL_HOUSE
            occurrences.max() == 3 -> THREE_OF_KIND
            occurrences.filter { it == 2 }.size == 2 -> TWO_PAIRS
            occurrences.max() == 2 -> ONE_PAIR
            occurrences.max() == 1 -> HIGH_CARD
            else -> throw Exception()
        }

        val jokers = this.filter { it == 'J' }.length
        repeat(jokers) {
            prefix = when (prefix) {
                FIVE_OF_A_KIND -> throw Exception() // We shouldn't have jokers
                FOUR_OF_A_KIND -> FIVE_OF_A_KIND
                FULL_HOUSE -> throw Exception() // Already has five cards
                THREE_OF_KIND -> FOUR_OF_A_KIND
                TWO_PAIRS -> FULL_HOUSE
                ONE_PAIR -> THREE_OF_KIND
                HIGH_CARD -> ONE_PAIR
                ALL_JOKERS -> HIGH_CARD
                else -> throw Exception()
            }
        }

        return "${prefix}${this.map { char -> orderingMap2[char] }.joinToString("")}"
    }
}

fun main() {
    val testInput = readInputLines("Day07-test")
    val input = readInputLines("Day07")

    asserter.assertEquals("1: ", 101, Day07.part1(listOf("2AAAA 99", "33332 1")))
    asserter.assertEquals("1: ", 6440, Day07.part1(testInput))
    println(Day07.part1(input))


    asserter.assertEquals("2: ", 5905, Day07.part2(testInput))
    println(Day07.part2(input))
}