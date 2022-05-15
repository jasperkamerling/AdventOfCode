import java.math.BigInteger
import java.security.MessageDigest

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)


data class Point(
    val vertical: Int,
    val horizontal: Int
) {
    fun getAdjacent(): List<Point> {
        return listOf(
            Point(vertical - 1, horizontal),
            Point(vertical + 1, horizontal),
            Point(vertical, horizontal - 1),
            Point(vertical, horizontal + 1)
        ).filter { it.vertical > -1 }.filter { it.horizontal > -1 }
    }
}