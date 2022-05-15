import kotlin.math.roundToInt

fun main() {
    fun part1OneLiner(input: List<String>): Int {
        val matrix: List<List<Int>> = input.map { it.map { it.digitToInt() } }
        return (matrix[0].size.rangeTo(0).map { i -> matrix.map { it[i] }.average().roundToInt() }.joinToString("").toInt(2)) *
         (matrix[0].size.rangeTo(0).map { i -> matrix.map { it[i] }.average().roundToInt().xor(1) }.joinToString("").toInt(2))
    }

    fun part1(input: List<String>): Int {
        val matrix: List<List<Int>> = input.map { it.map { it.digitToInt() } }
        var gammaRateBin = ""
        var epsilonRateBin = ""
        for (i in 0 until matrix[0].size) {
            gammaRateBin += matrix.map { it[i] }.average().roundToInt()
            epsilonRateBin += matrix.map { it[i] }.average().roundToInt().xor(1)
        }

        return (gammaRateBin.toInt(2)) * (epsilonRateBin.toInt(2))
    }

    fun part2(input: List<String>): Int {
        val matrix = input.map { it.map { it.digitToInt() } }
        val oxygenMatrix = matrix.toMutableList()
        val co2Matrix = matrix.toMutableList()

        for (i in 0 until matrix[0].size) {
            val oxygen = oxygenMatrix.map { it[i] }.average().roundToInt()
            oxygenMatrix.removeIf { it[i] != oxygen }
            if(oxygenMatrix.size == 1) break
        }

        for (i in 0 until matrix[0].size) {
            val co2 = co2Matrix.map { it[i] }.average()
                .let { if(it == 0.5) 0 else it.roundToInt()xor(1) }
            co2Matrix.removeIf { it[i] != co2 }
            if(co2Matrix.size == 1) break
        }
        val oxygen = oxygenMatrix[0].joinToString("").toInt(2)
        val co2 = co2Matrix[0].joinToString("").toInt(2)

        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day03_test")
    check(part1(testInput) == 198)
    check(part1OneLiner(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInputLines("Day03")
    println(part1(input))
    println(part1OneLiner(input))
    println(part2(input))
}
