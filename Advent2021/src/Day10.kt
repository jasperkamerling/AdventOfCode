fun main() {


    fun part1(input: List<String>): Int {
        val errors = mutableListOf<Char>()
        input.forEach { thing ->
            var line = thing
            while (line.isNotEmpty()) {
                println(line)
                val open = line[0]
                val close = when (open) {
                    '(' -> line.findLast { it == ')' }
                    '[' -> line.findLast { it == ']' }
                    '<' -> line.findLast { it == '>' }
                    '{' -> line.findLast { it == '}' }
                    else -> null
                }
                if (close == null) {
                    errors.add(open)
                    return@forEach
                }
                line = StringBuilder(line).deleteAt(line.lastIndexOf(close)).toString()
                line = line.drop(1)
            }
        }
        return errors.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputLines("Day10_test")
    check(part1(testInput) == 26397)
//    check(part2(testInput) == 5)

    val input = readInputLines("Day10")
    println(part1(input))
//    println(part2(input))
}
