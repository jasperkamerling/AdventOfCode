package com.scramados.`2020`

import java.io.File

fun main() {

    val input = File("C:\\git\\personal\\AdventOfCode\\src\\main\\kotlin\\com\\scramados\\2020\\day8.input")
    val lines = input.readLines().map { it.split(" ").let { Pair(it[0], it[1].toInt()) } }
    // Challenge 1
    println("one: ${test(lines).second}")

    // Challenge 2
    for ((index, line) in lines.withIndex()) {
        val copyLines = lines.toMutableList()
        when (line.first) {
            "jmp" -> copyLines[index] = Pair("nop", line.second)
            "nop" ->  copyLines[index] = Pair("jmp", line.second)
        }
        val acc = test(copyLines)
        if (acc.first) {
            println("two: ${acc.second}")
            break
        }
    }
}


fun test(lines: List<Pair<String, Int>>): Pair<Boolean, Int> {
    var good = true
    val visited = arrayListOf<Int>()
    var pointer = 0
    var accumulator = 0
    while (pointer < lines.size) {
        val line = lines[pointer]
        when (line.first) {
            "nop" -> pointer++
            "jmp" -> pointer += line.second
            "acc" -> {
                accumulator += line.second
                pointer++
            }
        }
        if (visited.contains(pointer)) {
            good = false
            break
        } else {
            visited.add(pointer)
        }
    }
    return Pair(good, accumulator)

}
