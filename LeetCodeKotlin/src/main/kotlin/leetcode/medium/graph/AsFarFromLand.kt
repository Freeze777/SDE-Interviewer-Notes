package leetcode.medium.graph

import java.util.*

/**
 * 1162. As Far from Land as Possible
 * https://leetcode.com/problems/as-far-from-land-as-possible/
 */
class AsFarFromLand {
    fun maxDistance(g: Array<IntArray>): Int {
        val distance = Array(g.size) { IntArray(g[0].size) { 0 } }
        val lands = LinkedList<Pair<Int, Int>>()
        g.forEachIndexed { i, ints -> ints.forEachIndexed { j, i1 -> if (i1 == 1) lands.add(Pair(i, j)) } }
        if (lands.isEmpty() || lands.size == g.size * g[0].size) return -1
        val visited = Array(g.size) { BooleanArray(g[0].size) }
        var maxDistance = -1

        while (lands.isNotEmpty()) {
            val land = lands.pop()
            val i = land.first
            val j = land.second
            if (visited[i][j]) continue
            visited[i][j] = true
            maxDistance = maxOf(maxDistance, distance[i][j])
            if (i > 0 && g[i - 1][j] == 0) {
                g[i - 1][j] = 1
                distance[i - 1][j] = distance[i][j] + 1
                lands.add(Pair(i - 1, j))
            }
            if (i < g.size - 1 && g[i + 1][j] == 0) {
                g[i + 1][j] = 1
                distance[i + 1][j] = distance[i][j] + 1
                lands.add(Pair(i + 1, j))
            }
            if (j > 0 && g[i][j - 1] == 0) {
                g[i][j - 1] = 1
                distance[i][j - 1] = distance[i][j] + 1
                lands.add(Pair(i, j - 1))
            }
            if (j < g[0].size - 1 && g[i][j + 1] == 0) {
                g[i][j + 1] = 1
                distance[i][j + 1] = distance[i][j] + 1
                lands.add(Pair(i, j + 1))
            }
        }
        return maxDistance
    }
}

fun main() {
    val af = AsFarFromLand()
    println(af.maxDistance(arrayOf(intArrayOf(1, 0, 1), intArrayOf(0, 0, 0), intArrayOf(1, 0, 1))))// 2
    println(af.maxDistance(arrayOf(intArrayOf(1, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))))// 4
    println(af.maxDistance(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))))// -1
    println(af.maxDistance(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1))))// -1
    println(af.maxDistance(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1))))// 1
    println(af.maxDistance(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1))))// -1
}
