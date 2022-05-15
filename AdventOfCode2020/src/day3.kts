import java.io.File

val input = File("C:\\git\\personal\\AdventOfCode\\src\\main\\kotlin\\com\\scramados\\2020\\day3.input")

fun <T> Sequence<T>.repeat() = sequence { while (true) yieldAll(this@repeat) }

val smallMatrix = input.readLines().map{it.splitToSequence("").filter{it.isNotEmpty()}}

var totalHits = listOf(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1, 2)).map { step ->

    var rowCounter = 0
    var columnCounter = 0
    var hits: Long = 0

    while (rowCounter < (smallMatrix.size - 1)) {
        columnCounter += step.first
        rowCounter += step.second
        val row: Sequence<String> = smallMatrix[rowCounter].repeat()
        if (row.elementAt(columnCounter) == "#") hits++
    }
    hits
}

// Challenge 1
println(totalHits[1])

// Challenge 2
println(totalHits.reduce { acc, i -> acc * i })

