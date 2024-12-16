import kotlin.time.measureTimedValue

fun getFileSystem(data: List<Int>): List<Int?> {
    data class State(var write: Boolean = true, var id: Int = 0, val fs: MutableList<Int?> = mutableListOf()) {
        fun write(size: Int) {
            fs.addAll(appendFiles(size))
            if(write) id++
            write = !write
        }

        private fun appendFiles(size: Int): List<Int?> =
            if (write) {
                List(size) { id }
            } else {
                List(size) { null }
            }
    }

    val state = State()
    data.forEach { size ->
        state.write(size)
    }
    return state.fs
}

class Day09(fileName: String) {
    private val input =
        fileFromResources(fileName).readLines().first()
            .toCharArray()
            .map { it.digitToInt() }

    fun part1(): Long {
        return getFileSystem(input).defrag().checksum()
    }

    fun part2(): Int {
        return input.hashCode()
    }

    private fun List<Int?>.defrag(): List<Int> {
        var mutableFs = this.toMutableList()
        var pointer = 0
        while (mutableFs.hasEmptyBlocks()) {
            val currentId = mutableFs[pointer]
            if (currentId == null) {
                mutableFs[pointer] = mutableFs.last()
                mutableFs.removeLast()
            }
            while (mutableFs.last() == null) {
                mutableFs.removeLast()
            }
            pointer++
        }
        return mutableFs.map { it!! }
    }

    private fun List<Int?>.hasEmptyBlocks(): Boolean =
        this.any { it == null }

    private fun List<Int?>.checksum(): Long =
        this.foldIndexed(0L) { index, acc, id ->
            acc + (index * (id ?: 0))
        }
}

fun main() {
    val testInput = Day09("test09.txt")
    val input = Day09("real09.txt")

    check(testInput.part1() == 1928L)
    measureTimedValue { input.part1() }.let { println("${it.value} - ${it.duration}") }

    check(testInput.part2() == 1)
    measureTimedValue { input.part2() }.let { println("${it.value} - ${it.duration}") }
}
