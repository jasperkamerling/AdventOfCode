class Card(val cardNumber: Int, val winning: List<Int>, val numbers: List<Int>)

private val cardNumberRegex = Regex("Card\\s+(\\d+):")

private fun part1(input: List<String>): Int =
    input.map { cardLine ->
        println(cardLine)
        val cardNumber = cardNumberRegex.find(cardLine)!!.groupValues[1].toInt()

        cardLine.split(':')[1]
            .split("|")
            .map { cardNumbers: String ->
                cardNumbers
                    .split(' ')
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
            }
            .let {
                Card(cardNumber, it[0], it[1])
            }
    }.map { card ->
        card.numbers.filter { card.winning.contains(it) }
            .fold(0) { acc, winningNumber ->
                if (acc == 0) 1
                else acc * 2
            }
    }.sum()


private fun part2(input: List<String>): Long {
    val cards: MutableMap<Card, Long> =
        input.map { cardLine ->
            val cardNumber = cardNumberRegex.find(cardLine)!!.groupValues[1].toInt()
            cardLine.split(':')[1]
                .split("|")
                .map { cardNumbers: String ->
                    cardNumbers
                        .split(' ')
                        .filter { it.isNotBlank() }
                        .map { it.toInt() }
                }
                .let {
                    Card(cardNumber, it[0], it[1])
                }
        }.associateWith { 1L }.toMutableMap()

    cards.entries.forEach {
        val card = it.key
        val cardCount = it.value
        val winCount = card.numbers.filter { card.winning.contains(it) }.size

        ((card.cardNumber + 1)..(card.cardNumber + winCount))
            .forEach { cardWon ->
                val wonCard: Card = cards.entries.find { it.key.cardNumber == cardWon }!!.key
                cards[wonCard] = cards[wonCard]!! + cardCount
            }

    }

    return cards.entries.sumOf { it.value }
}

fun main() {
    val testInput = readInputLines("Day04-test")
    val input = readInputLines("Day04")


    check(part1(testInput) == 13)
    println(part1(input))


    check(part2(testInput) == 30L)
    println(part2(input))
}