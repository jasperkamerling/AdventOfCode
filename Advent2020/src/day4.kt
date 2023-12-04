package com.scramados.`2020`

import java.io.File

fun main() {

    val input = File("C:\\git\\personal\\AdventOfCode\\src\\main\\kotlin\\com\\scramados\\2020\\day4.input")
    listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    val passportsWithFields =
            input.readText().split("\n\n").map { passport ->
                passport.split(Regex("[ \n]")).map {
                    val item = it.split(":")
                    Pair(item[0], item[1])
                }
            }
                    .filter { passport -> passport.map { it.first }.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")) }
    println("With required fields: ${passportsWithFields.count()}")

    val correctPassports =
            input.readText()
                    .split("\n\n")
                    .map { it.split(Regex("[ \n]")).map { it.split(":").let { Pair(it[0], it[1]) } } }
                    .filter { passport -> passport.map { it.first }.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")) }
                    .filter { it.first { it.first == "byr" }.second.toInt() in 1920..2002 }
                    .filter { it.first { it.first == "iyr" }.second.toInt() in 2010..2020 }
                    .filter { it.first { it.first == "eyr" }.second.toInt() in 2020..2030 }
                    .filter {
                        it.first { it.first == "hgt" }.second.let {
                            (it.takeLast(2) == "in" && it.dropLast(2).toInt() in 59..76) ||
                                    (it.takeLast(2) == "cm" && it.dropLast(2).toInt() in 150..193)
                        }
                    }
                    .filter { it.first { it.first == "hcl" }.second.matches(Regex("""#[0-9a-f]{6}""")) }
                    .filter { it.first { it.first == "ecl" }.second in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") }
                    .filter { it.first { it.first == "pid" }.second.length == 9 }.count()

    println("With correct fields: $correctPassports")
}
