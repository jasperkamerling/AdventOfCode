
fun main() {
    val input = fileFromResources("Day01").readText()

    println(input.split("\n\n").map{ it.split("\n").sumOf { it.toInt() } }.max())
    println(input.split("\n\n").map{ it.split("\n").sumOf { it.toInt() } }.sortedDescending().take(3).sum())

}
