import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String, path: String = "./src/input") = File(path, "$name.txt").readLines()

fun readInputFile(name: String, path: String = "./src/input") = File(path, "$name.txt").readText()

/**
 * Reads integer lines from the given input txt file.
 */
fun readInputNumbers(name: String) = readInputLines(name).map { it.toInt() }

fun readInputSplitNumber(name: String) = readInputFile(name).split(',').map { it.toInt() }

fun fileFromResources(fileName: String): File = File({}.javaClass.getResource(fileName)!!.file)

fun resourceLines(fileName: String): List<String> =
    fileFromResources(fileName).readText().trim().split('\n').map { it.trim() }
