import kotlin.experimental.inv

val orRegex = Regex("(\\w+) OR (\\w+)")

class Gate(instruction: String) {
    val target: String

    val operation: String?
    var value: Int?
    val argOne: String?
    val argTwo: String?


    init {
        target = instruction.split(" -> ")[1]
        val input = instruction.split(" -> ")[0]

        value = Regex("\\d+").matchEntire(input)?.value?.toInt()
        operation = input.split(" ").find { it.matches(Regex("[A-Z]+")) }
        argOne = input.split(" ").filter { it.matches(Regex("[a-z\\d]+")) }.getOrNull(0)
        argTwo = input.split(" ").filter { it.matches(Regex("[a-z\\d]+")) }.getOrNull(1)

    }
}

fun main() {

    fun calculate(wire: String, board: List<Gate>, cache: MutableList<Pair<String, Int>>): Int {
        // if the wire is an integer return it immediately
        if(wire.matches(Regex("\\d+"))) return wire.toInt()
        // If the cache already contains the value return it
        cache.find { it.first == wire }?.let { return it.second }

        val gate = board.find { it.target == wire }
            ?: throw Exception("no gate found for ${wire}")

        val value: Int

        if (gate.value != null) {
            value = gate.value!!
        } else {
            value = when (gate.operation) {
                "NOT" -> {
                    val inverse = calculate(gate.argOne!!, board, cache).inv()
                    if(inverse < 0) inverse.plus(65536)
                    else inverse
                }
                "AND" -> calculate(gate.argOne!!, board, cache) and calculate(gate.argTwo!!, board, cache)
                "OR" -> calculate(gate.argOne!!, board, cache) or calculate(gate.argTwo!!, board, cache)
                "LSHIFT" -> calculate(gate.argOne!!, board, cache) shl gate.argTwo!!.toInt()
                "RSHIFT" -> calculate(gate.argOne!!, board, cache) shr gate.argTwo!!.toInt()
                else -> calculate(gate.argOne!!, board, cache)
            }
        }

        cache.add(Pair(gate.target, value))
        return value
    }

    fun part1(input: List<String>, target: String): Int {
        val board = input
            .map { Gate(it) }
            .toMutableList()

        val cache = mutableListOf<Pair<String, Int>>()

        return calculate(target, board, cache)
    }

    fun part2(input: List<String>, target: String): Int {
        val board = input
            .map { Gate(it) }
            .toMutableList()

        val cache = mutableListOf<Pair<String, Int>>()
        val first =  calculate(target, board, cache)
        cache.clear()
        board.find { it.target == "b" }!!.value =  first
        return calculate(target, board, cache)
    }

    val testInput = readInputLines("Day07_test")
    val input = readInputLines("Day07")

    check(part1(testInput, "x") == 123)
    check(part1(testInput, "y") == 456)
    println(part1(testInput, "h"))
    check(part1(testInput, "h") == 65412)
    check(part1(testInput, "f") == 492)
    println(part1(input, "a"))
    println(part2(input, "a"))
}