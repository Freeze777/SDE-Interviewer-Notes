package leetcode.hard.math

import java.lang.Integer.max
import java.util.*

/**
 * 2246.Longest Path With Different Adjacent Characters
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/
 */
class LongestPathDifferentAdjacentCharacters {
    private data class SubtreePathStat(val maxStartFromRoot: Int, val maxSoFar: Int) {
        fun max(): Int = max(maxStartFromRoot, maxSoFar)
    }

    fun longestPath(parent: IntArray, color: String): Int {
        val graph = HashMap<Int, MutableList<Int>>()
        for (node in parent.indices) graph[node] = mutableListOf()
        for (node in parent.indices) graph[parent[node]]?.add(node)
        return postOrderWalk(graph, 0, color).max()
    }

    private fun postOrderWalk(graph: HashMap<Int, MutableList<Int>>, root: Int, color: String): SubtreePathStat {
        var maxSoFar = 1
        var maxStartFromRoot = 1
        val top2CompatiblePath = PriorityQueue<Int>(2) //min heap
        for (child in graph[root]!!) {
            val childSubtreeStat = postOrderWalk(graph, child, color)
            maxSoFar = max(maxSoFar, childSubtreeStat.maxSoFar)
            if (color[child] != color[root]) { //compatible with root
                maxStartFromRoot = max(maxStartFromRoot, childSubtreeStat.maxStartFromRoot + 1)
                if (top2CompatiblePath.size < 2) top2CompatiblePath.add(childSubtreeStat.maxStartFromRoot)
                else if (top2CompatiblePath.peek() < childSubtreeStat.maxStartFromRoot) {
                    top2CompatiblePath.poll()
                    top2CompatiblePath.add(childSubtreeStat.maxStartFromRoot)
                }
            }
        }
        if (top2CompatiblePath.size == 2)
            maxSoFar = max(maxSoFar, top2CompatiblePath.poll() + top2CompatiblePath.poll() + 1)

        return SubtreePathStat(maxStartFromRoot, max(maxSoFar, maxStartFromRoot))
    }
}

fun main() {
    val lpdc = LongestPathDifferentAdjacentCharacters()
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 1, 1, 2), "abacbe"))//3
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 0), "aabc"))//3
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 0, 0), "ababa"))//3
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 0, 0, 0), "ababab"))//3
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 1, 1, 3), "abacbe"))//4
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 1, 1, 3, 3), "abacbef"))//4
    println(lpdc.longestPath(intArrayOf(-1, 0, 0, 1, 1, 3, 3, 6), "abacbefg"))//5
    println(lpdc.longestPath(intArrayOf(-1, 0, 1), "aab"))//2
}