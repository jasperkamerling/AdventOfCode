package com.scramados.`2020`

import java.io.File

fun main() {
    val input = File("C:\\git\\personal\\AdventOfCode\\src\\main\\kotlin\\com\\scramados\\2020\\day7.input").readLines()
    val bags = input
        .map {
            Pair(it.split(" bags contain")[0],
                if (it.contains("contain no other bags")) listOf()
                else it.split("bags contain ")[1].dropLast(1).split(", ").map {
                    Pair(it.split(" ")[0].toInt(), it.split(" ").subList(1, it.split(" ").size - 1).joinToString(" "))
                })
        }
    println(bags)

    fun one(start: String, name: String = start): List<String> {
        return if (name == "shiny gold") listOf(start)
        else bags.first { it.first == name }.second.map { one(start, it.second) }.flatten()
    }
    println("one: ${bags.map { one(it.first) }.flatten().distinct().size -1}")

    fun two(start: String, name: String = start): Int {
        return if (bags.first { it.first == name }.second.isEmpty()) 1
        else 1 + bags.first { it.first == name }.second.sumBy { it.first * two(start, it.second) }
    }
    println("two: ${two("shiny gold") - 1}")

}
