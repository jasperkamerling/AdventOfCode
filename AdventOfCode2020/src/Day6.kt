package com.scramados.`2020`

import java.io.File

fun main() {

    val input = File("C:\\git\\personal\\AdventOfCode\\src\\main\\kotlin\\com\\scramados\\2020\\day6.input").readText()
    println("one: ${input.split("\n\n").fold(0){s,i->s+i.replace("\n","").toSet().distinct().size}}")
    println("two: ${input.split("\n\n").fold(0){s,i->s+i.split("\n").flatMap{it.asSequence()}.groupingBy{it}.eachCount().filter{it.value==i.split("\n").filter{!it.isEmpty()}.size}.size}}")
}
