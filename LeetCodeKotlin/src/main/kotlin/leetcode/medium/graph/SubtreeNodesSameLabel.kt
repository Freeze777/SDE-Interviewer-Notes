package leetcode.medium.graph

/**
 * 1519. Number of Nodes in the Sub-Tree With the Same Label
 * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 */
class SubtreeNodesSameLabel {
    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        val graph = HashMap<Int, MutableList<Int>>()
        for (i in 0 until n) graph[i] = mutableListOf()
        for (edge in edges) {
            graph[edge[0]]?.add(edge[1])
            graph[edge[1]]?.add(edge[0])
        }
        val visited = BooleanArray(n)
        val result = IntArray(n)
        postOrderWalk(0, graph, labels, visited, result)
        return result
    }

    private fun postOrderWalk(
        root: Int,
        graph: HashMap<Int, MutableList<Int>>,
        labels: String,
        visited: BooleanArray,
        result: IntArray
    ): Map<Char, Int> {
        visited[root] = true
        val children = graph[root]!!
        if (children.size == 1 && visited[children[0]]) { // leaf
            result[root] = 1
            return mapOf(labels[root] to 1)
        }
        val labelMap = mutableMapOf(labels[root] to 1)
        for (child in children) {
            if (visited[child]) continue
            val childLabelMap = postOrderWalk(child, graph, labels, visited, result)
            for ((key, value) in childLabelMap) labelMap[key] = labelMap.getOrDefault(key, 0) + value
            result[root] = labelMap[labels[root]]!!
        }
        return labelMap
    }
}

fun main() {
    val snt = SubtreeNodesSameLabel()
    println(
        snt.countSubTrees(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 4),
                intArrayOf(1, 5),
                intArrayOf(2, 3),
                intArrayOf(2, 6)
            ),
            "abaedcd"
        ).toList()
    )//[2, 1, 1, 1, 1, 1, 1]
    println(
        snt.countSubTrees(4, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(0, 3)), "bbbb").toList()
    )//[4, 2, 1, 1]
    println(
        snt.countSubTrees(
            5,
            arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(0, 4)),
            "aabab"
        ).toList()
    )//[3, 2, 1, 1, 1]
    println(
        snt.countSubTrees(
            6,
            arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(3, 4), intArrayOf(4, 5)),
            "cbabaa"
        ).toList()
    )//[1, 2, 1, 1, 2, 1]
    println(
        snt.countSubTrees(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(4, 5),
                intArrayOf(5, 6)
            ),
            "aaabaaa"
        ).toList()
    )//[6, 5, 4, 1, 3, 2, 1]
}