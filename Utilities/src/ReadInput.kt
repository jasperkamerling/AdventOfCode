import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String) = readInputLines("src", name)
fun readInputLines(path: String, name: String) = File("$path/src", "$name.txt").readLines()

fun readInputFile(name: String) = File("src", "$name.txt").readText()
fun readInputFile(path: String, name: String) = File("$path/src", "$name.txt").readText()

/**
 * Reads integer lines from the given input txt file.
 */
fun readInputNumbers(name: String) = readInputLines(name).map { it.toInt() }

fun readInputSplitNumber(name: String) = readInputFile(name).split(",").map { it.toInt() }
