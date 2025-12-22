import kotlin.math.*

class Year2025Day08(fileName: String) {

    typealias Circuit = Set<JunctionBox>

    data class Connection(val box: JunctionBox, val closest: JunctionBox) {
        fun distance() = box.distance(closest)
    }

    data class JunctionBox(val x: Int, val y: Int, val z: Int) {
        fun distance(other: JunctionBox): Double =
            if (this == other) Double.MAX_VALUE
            else sqrt(
                ((x - other.x) * (x - other.x) +
                        (y - other.y) * (y - other.y) +
                        (z - other.z) * (z - other.z)).toDouble()
            )
    }

    private val boxes = fileFromResources(fileName).readLines()
        .map { it.split(",") }
        .map { JunctionBox(it[0].toInt(), it[1].toInt(), it[2].toInt()) }

    fun part1(connections: Int): Int {
        val circuits = mutableSetOf<Circuit>()
        var wires = 0
        boxes.map { box -> box to boxes.minBy { box.distance(it) } }
            .sortedBy { it.first.distance(it.second) }
            .distinctBy { setOf(it.first, it.second) }
            .forEach { (box, closest) ->
                if(wires == connections) return@forEach
                if (circuits.any { circuit -> circuit.containsAll(setOf(box, closest)) }) {
                    // If both are already in a circuit skipt
                    return@forEach
                }
                wires++
                val connectedCircuits = circuits.filter { circuit ->
                    circuit.contains(box) || circuit.contains(closest)
                }

                if (connectedCircuits.isNotEmpty()) {
                    circuits.removeAll(connectedCircuits)
                    circuits.add(connectedCircuits.first() + connectedCircuits.last() + box + closest)
                } else {
                    circuits.add(setOf(box, closest))
                }
            }

        return circuits.map { it.size }
            .sortedDescending()
            .take(3)
            .fold(1)
            { total, it -> total * it }
    }

    fun part2(): Int {
        return boxes.hashCode()
    }


}

fun main() {
    val testInput = Year2025Day08("test-2025-08.txt")
    val input = Year2025Day08("real-2025-08.txt")


    check(testInput.part1(10) == 40)
    println(input.part1(1000))

//    check(testInput.part2() == 1)
//    println(input.part2())
}
