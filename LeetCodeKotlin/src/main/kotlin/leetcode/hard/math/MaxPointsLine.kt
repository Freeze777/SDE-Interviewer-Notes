package leetcode.hard.math

/**
 * 149. Max Points on a Line
 * https://leetcode.com/problems/max-points-on-a-line/
 */
class MaxPointsLine {
    fun maxPoints(points: Array<IntArray>): Int {
        val lines = mutableMapOf<Triple<Int, Int, Int>, MutableSet<Pair<Int, Int>>>()
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val coefficient = lineCoefficient(points[i], points[j])
                lines[coefficient] = lines.getOrDefault(coefficient, mutableSetOf()).apply {
                    add(points[i][0] to points[i][1])
                    add(points[j][0] to points[j][1])
                }
            }
        }
        return if (lines.isNotEmpty()) lines.maxBy { it.value.size }.value.size else 1
    }

    private fun lineCoefficient(point: IntArray, otherPoint: IntArray): Triple<Int, Int, Int> {
        val a = point[1] - otherPoint[1]
        val b = otherPoint[0] - point[0]
        val c = point[0] * otherPoint[1] - otherPoint[0] * point[1]
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
    println(mpl.maxPoints(arrayOf(intArrayOf(1, 1))))//1
    println(mpl.maxPoints(arrayOf(intArrayOf(1, 1), intArrayOf(1000, 2000))))//2
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
}