package leetcode.hard.math

/**
 * 149. Max Points on a Line
 * https://leetcode.com/problems/max-points-on-a-line/
 */
class MaxPointsLine {
    fun maxPoints(points: Array<IntArray>): Int {
        val map = mutableMapOf<Triple<Int, Int, Int>, MutableSet<Pair<Int, Int>>>()
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val coefficient = lineCoefficient(points[i], points[j])
                map[coefficient] = map.getOrDefault(coefficient, mutableSetOf()).apply {
                    add(points[i][0] to points[i][1])
                    add(points[j][0] to points[j][1])
                }
            }
        }
        return if (map.isNotEmpty()) map.maxBy { it.value.size }.value.size else 1
    }

    private fun lineCoefficient(firstPoint: IntArray, secondPoint: IntArray): Triple<Int, Int, Int> {
        val a = firstPoint[1] - secondPoint[1]
        val b = secondPoint[0] - firstPoint[0]
        val c = firstPoint[0] * secondPoint[1] - secondPoint[0] * firstPoint[1]
        val gcd = gcd(a, gcd(b, c))
        return Triple(a / gcd, b / gcd, c / gcd)
    }

    private fun gcd(x: Int, y: Int): Int {
        if (y == 0) return x
        return gcd(y, x % y)
    }

}

fun main() {
    val mpl = MaxPointsLine()
    println(mpl.maxPoints(arrayOf(intArrayOf(1, 1), intArrayOf(2, 2), intArrayOf(3, 3))))//3
    println(
        mpl.maxPoints(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(3, 2),
                intArrayOf(5, 3),
                intArrayOf(4, 1),
                intArrayOf(2, 3),
                intArrayOf(1, 4)
            )
        )
    )//4

    println(
        mpl.maxPoints(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 3),
                intArrayOf(3, 2),
                intArrayOf(5, 3),
                intArrayOf(4, 1),
                intArrayOf(1, 4)
            )
        )
    )//4

    println(
        mpl.maxPoints(
            arrayOf(
                intArrayOf(1, 1),
            )
        )
    )//1

    println(
        mpl.maxPoints(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1000, 2000),
            )
        )
    )//2
}