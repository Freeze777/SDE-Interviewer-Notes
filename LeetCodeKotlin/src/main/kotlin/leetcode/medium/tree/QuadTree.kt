package leetcode.medium.tree


/**
 * 427. Construct Quad Tree
 * https://leetcode.com/problems/construct-quad-tree/
 */
class QuadTree {
    data class Node(var `val`: Boolean, var isLeaf: Boolean) {
        var topLeft: Node? = null
        var topRight: Node? = null
        var bottomLeft: Node? = null
        var bottomRight: Node? = null
    }

    fun construct(grid: Array<IntArray>): Node? {
        if (grid.isEmpty()) return null
        return constructHelper(grid, 0, 0, grid.size)
    }

    private fun constructHelper(grid: Array<IntArray>, row: Int, col: Int, length: Int): Node {
        if (length == 1) return Node(grid[row][col] == 1, true)
        val topLeft = constructHelper(grid, row, col, length / 2)
        val topRight = constructHelper(grid, row, col + length / 2, length / 2)
        val bottomLeft = constructHelper(grid, row + length / 2, col, length / 2)
        val bottomRight = constructHelper(grid, row + length / 2, col + length / 2, length / 2)
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
            topLeft.`val` == topRight.`val` && topRight.`val` == bottomLeft.`val` && bottomLeft.`val` == bottomRight.`val`
        ) {
            return Node(topLeft.`val`, true) // all children are leafs and have the same value
        }
        return Node(`val` = false, isLeaf = false).apply {
            this.topLeft = topLeft
            this.topRight = topRight
            this.bottomLeft = bottomLeft
            this.bottomRight = bottomRight
        }
    }
}

fun main() {
    val qt = QuadTree()
    println(
        qt.construct(
            arrayOf(
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 1, 0, 0, 0, 0)
            )
        )
    )
}
