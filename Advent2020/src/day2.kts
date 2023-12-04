import java.io.File

val file = File("day2.input")
var challenge1 = 0
for (line in file.readLines()) {
    val items: List<String> = line.split(" ")
    val (min, max) = items[0].split("-").map { it.toInt() }
    val character = items[1].toCharArray()[0]
    val count = items[2].filter { it == character }.count()
    if (count in min..max) challenge1 ++
}
println(challenge1)

var challenge2 = 0
for (line in file.readLines()) {
    val items: List<String> = line.split(" ")
    val (min, max) = items[0].split("-").map { it.toInt() }
    val character = items[1].toCharArray()[0]
    val password = items[2]

    if((password[min - 1] == character).xor(password[max - 1] == character))
        challenge2 ++
}
println(challenge2)
