package leetcode.medium.graph

import java.util.*

/**
 * 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix/
 */
class ZeroOneMatrix {
    fun updateMatrix(mat: Array<IntArray>) = nearestZero(mat)
    private fun nearestZero(matrix: Array<IntArray>): Array<IntArray> {
        fun allZeros(): List<Pair<Int, Int>> {
            val zeros = mutableListOf<Pair<Int, Int>>()
            for (row in matrix.indices) for (col in matrix[0].indices) if (matrix[row][col] == 0) zeros.add(row to col)
            return zeros
        }
        val (m, n) = (matrix.size to matrix[0].size)
        val directions = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
        val distance = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        val (bfsQueue, visited) = (LinkedList<Pair<Int, Int>>() to HashSet<Pair<Int, Int>>())
        bfsQueue.addAll(allZeros())
        while (bfsQueue.isNotEmpty()) {
            val (row, col) = bfsQueue.remove()
            if (visited.contains((row to col))) continue
            if (matrix[row][col] == 0) distance[row][col] = 0
            directions.forEach { (x, y) ->
                val (newRow, newCol) = (row + x to col + y)
                if (newRow in 0 until m && newCol in 0 until n) {
                    distance[newRow][newCol] = minOf(distance[newRow][newCol], distance[row][col] + 1)
                    bfsQueue.add(newRow to newCol)
                }
            }
            visited.add((row to col))
        }
        return distance
    }
}

fun main() {
    val m = ZeroOneMatrix()
    println(
        m.updateMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1)))
            .contentDeepToString()
    )
    println(
        m.updateMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0)))
            .contentDeepToString()
    )
    println(
        m.updateMatrix(arrayOf(intArrayOf(0, 1, 1), intArrayOf(1, 1, 1), intArrayOf(1, 1, 1)))
            .contentDeepToString()
    )
    println(
        m.updateMatrix(arrayOf(intArrayOf(0, 1, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1)))
            .contentDeepToString()
    )
    println(
        m.updateMatrix(arrayOf(intArrayOf(0, 1, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 0)))
            .contentDeepToString()
    )
}