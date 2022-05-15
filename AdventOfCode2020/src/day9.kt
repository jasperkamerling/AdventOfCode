package com.scramados.`2020`

import java.io.File


fun main() {

    val input = File("C:\\git\\personal\\AdventOfCode\\src\\main\\kotlin\\com\\scramados\\2020\\day9.input").readLines().map { it.toLong() }
    // 1
    var one = 0L
    for ((index, number) in input.withIndex()) {
        if (index < 25) continue
        val newList = input.subList(index - 25, index)
        var found = false
        for (first in newList) {
            for (second in newList) {
                if (first + second == number) {
                    found = true
                    break
                }
            }
            if (found) break
        }
        if (!found) {
            one = number
            println("no combination found for: ${one}")
            break
        }
    }
    for (forward in input.indices) for (reverse in input.indices.reversed()) {
        if (reverse < forward) break
        val sub = input.subList(forward, reverse)
        if (sub.sum() == one) {
            println("Two: ${sub.minOrNull()!! + sub.maxOrNull()!!}")
            break
        }
    }

}
