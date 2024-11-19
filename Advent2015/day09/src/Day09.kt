class Dayxx(fileName: String) {
    private val input = fileFromResources(fileName).readLines()

    private val routes: Map<Pair<String, String>, String> = input
        .map { it.split(" = ") }
        .associate { it[0] to it[1] }
        .mapKeys { it.key.split("") }
        .mapKeys { it.key[0] to it.key[1] }


    fun part1(): Int {
        return routes.size
    }

    fun part2(): Int {
        return input.hashCode()
    }
}

fun main() {
    val day09Test = Dayxx("test09.txt")
    val day09 = Dayxx("real09.txt")


    check(day09Test.part1() == 0)
    println(day09.part1())

    check(day09Test.part2() == 1)
    println(day09.part2())
}