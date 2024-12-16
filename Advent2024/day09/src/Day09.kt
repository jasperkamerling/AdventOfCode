import kotlin.time.measureTimedValue

fun getFileSystem(data: List<Int>): List<Int?> {
    data class State(val write: Boolean, val id: Int = 0, val fs: List<Int?> = listOf()) {
        fun write(size: Int): State =
            State(
                write = !write,
                id = if (write) id + 1 else id,
                fs = fs + appendFiles(size)
            )

        private fun appendFiles(size: Int): List<Int?> =
            if (write) {
                List(size) { id }
            } else {
                List(size) { null }
            }
    }

    return data.fold(State(true)) { state, size ->
        state.write(size)
    }.fs
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
            mutableFs = mutableFs.dropLastWhile { it == null }.toMutableList()
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
