package leetcode.medium.graph

import java.util.*
import kotlin.collections.HashMap

/**
 * 1129. Shortest Path with Alternating Colors
 * https://leetcode.com/problems/shortest-path-with-alternating-colors/
 */
class ShortestPathAlternatingColors {
    fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
        val redGraph = HashMap<Int, MutableList<Int>>()
        val blueGraph = HashMap<Int, MutableList<Int>>()
        for (i in 0 until n) {
            redGraph[i] = mutableListOf()
            blueGraph[i] = mutableListOf()
        }
        for (i in redEdges.indices) redGraph[redEdges[i][0]]!!.add(redEdges[i][1])
        for (i in blueEdges.indices) blueGraph[blueEdges[i][0]]!!.add(blueEdges[i][1])
        val bfsQ = LinkedList<Triple<Int, Char, Int>>()
        val visited = HashSet<Pair<Int, Char>>()
        val distance = IntArray(n) { -1 }
        bfsQ.addAll(listOf(Triple(0, 'R', 0), Triple(0, 'B', 0)))
        while (bfsQ.isNotEmpty()) {
            val current = bfsQ.poll()
            val currentPair = Pair(current.first, current.second)
            if (visited.contains(currentPair)) continue
            if (distance[current.first] == -1 || distance[current.first] > current.third)
                distance[current.first] = current.third
            visited.add(currentPair)
            val (nextColor, nextGraph) = if (current.second == 'R') 'B' to blueGraph else 'R' to redGraph
            for (i in nextGraph[current.first]!!) bfsQ.add(Triple(i, nextColor, current.third + 1))
        }
        return distance
    }
}

fun main() {
    val s = ShortestPathAlternatingColors()
    println(s.shortestAlternatingPaths(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)), arrayOf(intArrayOf(1, 0))).toList())// [0,1,-1]
    println(s.shortestAlternatingPaths(3, arrayOf(intArrayOf(0, 1)), arrayOf(intArrayOf(2, 1))).toList())// [0,1,-1]
    println(s.shortestAlternatingPaths(3, arrayOf(intArrayOf(1, 0)), arrayOf(intArrayOf(2, 1))).toList())// [0,1,-1]
    println(s.shortestAlternatingPaths(3, arrayOf(intArrayOf(0, 1)), arrayOf(intArrayOf(1, 2))).toList())// [0,1,2]
    println(s.shortestAlternatingPaths(3, arrayOf(intArrayOf(0, 1), intArrayOf(0, 2)), arrayOf(intArrayOf(1, 0))).toList())// [0,1,1]
}