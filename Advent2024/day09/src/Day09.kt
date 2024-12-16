import kotlin.time.measureTimedValue

fun getFileSystem(data: List<Int>): List<Int?> {
    data class State(var write: Boolean = true, var id: Int = 0, val fs: MutableList<Int?> = mutableListOf()) {
        fun write(size: Int) {
            fs.addAll(appendFiles(size))
            if (write) id++
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

    private val fs = getFileSystem(input)

    fun part1(): Long {
        return fs.defrag().checksum()
    }

    fun part2(): Long {
        return fs.fastDefrag().checksum()
    }

    private fun List<Int?>.defrag(): List<Int?> {
        val mfs = this.toMutableList()
        var pointer = 0
        while (mfs.hasEmptyBlocks()) {
            val currentId = mfs[pointer]
            if (currentId == null) {
                mfs[pointer] = mfs.last()
                mfs.removeLast()
            }
            while (mfs.last() == null) {
                mfs.removeLast()
            }
            pointer++
        }
        return mfs
    }

    private fun List<Int?>.fastDefrag(): List<Int?> {
        val mfs = this.toMutableList()
        var pointer = 0
        while (true) {
            val firstNullBlockSize = mfs.firstNullBlockSize()

            val lastGroupIndex = mfs.lastBlockSize(firstNullBlockSize)
            val currentId = mfs[pointer]
            if (currentId == null) {
                mfs[pointer] = mfs.last()
                mfs.removeLast()
            }
            while (mfs.last() == null) {
                mfs.removeLast()
            }
            pointer++

            return mfs.toList()
        }
    }

    private fun List<Int?>.hasEmptyBlocks(): Boolean =
        this.any { it == null }

    private fun List<Int?>.checksum(): Long =
        this.foldIndexed(0L) { index, acc, id ->
            acc + (index * (id ?: 0))
        }

}

/** Returns index of start of group */
fun List<Int?>.lastBlockSize(groupSize: Int): Int? {

    this.windowed(groupSize) {

    }

    this.indices.map { index ->
        val cutOff = this.dropLast(index)
        if (cutOff.takeLast(groupSize).all { it == cutOff.last() })
            return this.size - index
    }
    return null
}

fun List<Int?>.firstNullBlockSize(): Int =
    this.dropWhile { it != null }.takeWhile { it == null }.size

fun main() {
    val testInput = Day09("test09.txt")
    val input = Day09("real09.txt")

    check(testInput.part1() == 1928L)
    measureTimedValue { input.part1() }.let { println("${it.value} - ${it.duration}") }

    // Check last block size
    check(listOf(1, 2, null, 3, 3).lastBlockSize(2) == 3)
    check(listOf(1, 2, 4, 4, 4).lastBlockSize(3) == 2)
    check(listOf(1, 2, 4, 4, 4).lastBlockSize(5) == null)

    // Check first null block size
    check(listOf(1, 2, 4, 4, 4).firstNullBlockSize() == 0)
    check(listOf(null, null, 4, 4, 4).firstNullBlockSize() == 2)
    check(listOf(1, null, null, null, 4).firstNullBlockSize() == 3)

    check(testInput.part2() == 2858L)
    measureTimedValue { input.part2() }.let { println("${it.value} - ${it.duration}") }
}
