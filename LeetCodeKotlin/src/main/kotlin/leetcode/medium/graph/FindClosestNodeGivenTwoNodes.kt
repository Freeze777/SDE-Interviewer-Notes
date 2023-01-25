package leetcode.medium.graph

import java.util.*
import kotlin.collections.HashMap

/**
 * 2359. Find Closest Node Given Two Nodes
 * https://leetcode.com/problems/find-closest-node-given-two-nodes/
 */
class FindClosestNodeGivenTwoNodes {
    fun closestMeetingNode(edge: IntArray, n1: Int, n2: Int): Int {
        val (n, graph) = edge.size to HashMap<Int, MutableList<Int>>()
        for (i in 0 until n) graph[i] = mutableListOf()
        for (v in edge.indices) if (edge[v] != -1) graph[v]!!.add(edge[v])
        val (distanceToN1, distanceToN2) = bfs(n1, graph) to bfs(n2, graph)
        val commons = distanceToN2.keys.intersect(distanceToN1.keys)
        if (commons.isEmpty()) return -1
        val nodes = commons.map { it to maxOf(distanceToN1[it]!!, distanceToN2[it]!!) }
            .sortedBy { it.first }.sortedBy { it.second }
        return nodes.first().first
    }

    private fun bfs(start: Int, graph: HashMap<Int, MutableList<Int>>): Map<Int, Int> {
        val (distance, bfsQ) = HashMap<Int, Int>() to LinkedList<Int>()
        val visited = BooleanArray(graph.size)
        bfsQ.add(start)
        distance[start] = 0
        while (bfsQ.isNotEmpty()) {
            val node = bfsQ.remove()
            for (next in graph[node]!!) {
                if (!visited[next]) {
                    distance[next] = distance[node]!! + 1
                    bfsQ.add(next)
                }
            }
            visited[node] = true
        }
        return distance
    }
}

fun main() {
    val fcn = FindClosestNodeGivenTwoNodes()
    println(fcn.closestMeetingNode(intArrayOf(2, 2, 3, -1), 0, 1))//2
    println(fcn.closestMeetingNode(intArrayOf(2, 2, 3, -1), 0, 2))//2
    println(fcn.closestMeetingNode(intArrayOf(2, 2, 3, -1), 0, 3))//3
    println(fcn.closestMeetingNode(intArrayOf(2, 2, 3, -1), 1, 2))//2
    println(fcn.closestMeetingNode(intArrayOf(-1, 2, 3, -1), 0, 1))//-1
    println(fcn.closestMeetingNode(intArrayOf(4, 3, 0, 5, 3, -1), 4, 0))//4
    println(fcn.closestMeetingNode(intArrayOf(4, 4, 4, 5, 1, 2, 2), 1, 1))//1
    println(fcn.closestMeetingNode(intArrayOf(4, 2, 5, 1, 3, 6, 5), 1, 6))//5
}