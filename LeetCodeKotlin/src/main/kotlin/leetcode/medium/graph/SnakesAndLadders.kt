package leetcode.medium.graph

import java.util.*
import kotlin.collections.HashMap

/**
 * 909. Snakes and Ladders
 * https://leetcode.com/problems/snakes-and-ladders/
 */
class SnakesAndLadders {
    fun snakesAndLadders(board: Array<IntArray>): Int {
        val (start, N) = 1 to board.size * board.size
        val graph = buildGraph(flattenedBoard(board))
        val distance = IntArray(N + 1) { Int.MAX_VALUE }//distance from 1 to i
        val bfsQ = LinkedList<Int>()
        bfsQ.add(start)
        distance[start] = 0
        while (bfsQ.isNotEmpty()) {
            val node = bfsQ.remove()
            for (next in graph[node]!!) {
                if (distance[next] > distance[node] + 1) {
                    distance[next] = distance[node] + 1
                    bfsQ.add(next)
                }
            }
        }
        return if (distance[N] == Int.MAX_VALUE) -1 else distance[N]
    }

    private fun buildGraph(board: IntArray): Map<Int, MutableList<Int>> {
        val graph = HashMap<Int, MutableList<Int>>()
        for (boardIndex in 1..board.lastIndex) graph[boardIndex] = mutableListOf()
        for (boardIndex in 1..board.lastIndex) {
            for (dieRoll in 1..6) {
                val nextIndex = boardIndex + dieRoll
                if (nextIndex > board.lastIndex) continue
                if (board[nextIndex] == -1) graph[boardIndex]!!.add(nextIndex)
                else graph[boardIndex]!!.add(board[nextIndex])
            }
        }
        return graph
    }

    //boustrophedon style
    private fun flattenedBoard(board: Array<IntArray>): IntArray {
        val n = board.size
        for (i in 0 until n) if (i % 2 == n % 2) board[i].reverse()
        board.reverse()
        val flatten = IntArray(n * n + 1)
        for (i in board.indices) for (j in 1..n) flatten[n * i + j] = board[i][j - 1]
        return flatten
    }
}

fun main() {
    val sal = SnakesAndLadders()
    println(sal.snakesAndLadders(arrayOf(intArrayOf(1, -1, -1), intArrayOf(9, -1, -1), intArrayOf(-1, 6, -1))))//1
    println(sal.snakesAndLadders(arrayOf(intArrayOf(1, 1, -1), intArrayOf(1, 1, 1), intArrayOf(-1, 1, 1))))//-1
    println(sal.snakesAndLadders(arrayOf(intArrayOf(-1, -1), intArrayOf(-1, 3))))//1
    println(
        sal.snakesAndLadders(
            arrayOf(
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 35, -1, -1, 13, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 15, -1, -1, -1, -1)
            )
        )
    )//4
    println(
        sal.snakesAndLadders(
            arrayOf(
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 15, -1, -1, 35, -1),
                intArrayOf(-1, -1, -1, -1, -1, -1),
                intArrayOf(-1, 5, -1, -1, -1, -1)
            )
        )
    )//4
}