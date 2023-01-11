package leetcode.medium.graph

/**
 * 1443. Minimum Time to Collect All Apples in a Tree
 * https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
 */
class MinTimeCollectApplesTree {

    fun minTime(n: Int, edges: Array<IntArray>, hasApple: List<Boolean>): Int {
        val visited = BooleanArray(n)
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (vertex in 0 until n) graph[vertex] = mutableListOf()
        for (edge in edges) {
            graph[edge[0]]!!.add(edge[1])
            graph[edge[1]]!!.add(edge[0])
        }
        val totalPath = postOrderWalk(graph, hasApple, visited, 0)
        return if (totalPath == 0) 0 else totalPath - 2
    }

    private fun postOrderWalk(
        graph: Map<Int, MutableList<Int>>, hasApple: List<Boolean>, visited: BooleanArray, root: Int
    ): Int {
        visited[root] = true
        val children = graph[root]!!
        if (children.size == 1 && visited[children[0]]) return if (hasApple[root]) 2 else 0 //leaf node
        var childPath = 0
        for (child in children) {
            if (visited[child]) continue
            childPath += postOrderWalk(graph, hasApple, visited, child)
        }
        return if (childPath == 0 && !hasApple[root]) 0 else childPath + 2
    }
}

fun main() {
    val mtcat = MinTimeCollectApplesTree()
    println(
        mtcat.minTime(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            listOf(false, false, true, false, true, true, false)
        )
    )// 8

    println(
        mtcat.minTime(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            listOf(false, false, true, false, false, true, false)
        )
    )// 6

    println(
        mtcat.minTime(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            listOf(false, false, false, false, false, false, false)
        )
    )// 0

    println(
        mtcat.minTime(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            listOf(true, true, true, true, true, true, true)
        )
    )// 12

    println(
        mtcat.minTime(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            listOf(true, true, true, true, true, true, false)
        )
    )// 10

    println(
        mtcat.minTime(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            listOf(true, true, true, true, true, false, false)
        )
    )// 8

    println(
        mtcat.minTime(
            8,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(1, 4),
                intArrayOf(2, 5),
                intArrayOf(2, 6),
                intArrayOf(4, 7)
            ),
            listOf(true, true, false, true, false, true, true, false)
        )
    )// 10
}
