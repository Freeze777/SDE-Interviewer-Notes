package leetcode.easy.graph

import java.util.LinkedList

/**
 * 1091. Shortest Path in Binary Matrix
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val graph = HashMap<Int, MutableList<Int>>()
    for (v in 0 until n) graph[v] = mutableListOf()
    for (edge in edges) {
        graph[edge[0]]?.add(edge[1])
        graph[edge[1]]?.add(edge[0])
    }

    val bfsQ = LinkedList<Int>()
    val visited = HashSet<Int>()
    bfsQ.add(source)
    while (bfsQ.isNotEmpty()) {
        val v = bfsQ.remove()
        if (visited.contains(v)) continue
        if (v == destination) return true
        graph[v]?.let { bfsQ.addAll(it) }
        visited.add(v)
    }
    return false
}

fun main() {
    println(validPath(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 0)), 0, 2))

}