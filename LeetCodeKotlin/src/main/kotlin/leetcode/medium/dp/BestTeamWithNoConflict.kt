package leetcode.medium.dp

import leetcode.utils.randomIntArray

class BestTeamWithNoConflict {
    private data class Player(val score: Int, val age: Int)

    fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
        val players = scores.zip(ages).map { Player(it.first, it.second) }.toMutableList()
        players.sortBy { it.score }
        players.sortBy { it.age }
        val cache = Array(players.size) { IntArray(players.size) { -1 } }
        return bestTeamScoreHelper(players, 0, -1, cache)
    }

    private fun bestTeamScoreHelper(players: List<Player>, index: Int, previous: Int, cache: Array<IntArray>): Int {
        if (index >= players.size) return 0
        if (cache[previous + 1][index] != -1) return cache[previous + 1][index]
        val currentPlayer = players[index]
        val previousPlayer = if (previous == -1) Player(0, Int.MIN_VALUE) else players[previous]
        val isConflict = (currentPlayer.age > previousPlayer.age && currentPlayer.score < previousPlayer.score)
        val take = if (!isConflict) bestTeamScoreHelper(players, index + 1, index, cache) + currentPlayer.score else 0
        val dontTake = bestTeamScoreHelper(players, index + 1, previous, cache)
        cache[previous + 1][index] = maxOf(take, dontTake)
        return cache[previous + 1][index]
    }
}

fun main() {
    val btnc = BestTeamWithNoConflict()
    println(btnc.bestTeamScore(intArrayOf(2, 3, 5, 6, 7), intArrayOf(30, 25, 35, 40, 23)))//14
    println(btnc.bestTeamScore(intArrayOf(1, 3, 5, 10, 15), intArrayOf(1, 2, 3, 4, 5)))//34
    println(btnc.bestTeamScore(intArrayOf(4, 5, 6, 5), intArrayOf(2, 1, 2, 1)))//16
    println(btnc.bestTeamScore(intArrayOf(1, 2, 3, 5), intArrayOf(8, 9, 10, 1)))//6
    println(btnc.bestTeamScore(randomIntArray(10, 1, 1_000_000), randomIntArray(10, 1, 1000)))
    val start = System.currentTimeMillis()
    println(btnc.bestTeamScore(randomIntArray(1000, 1, 1_000_000), randomIntArray(1000, 1, 1000)))
    println("time: ${System.currentTimeMillis() - start} ms")
}
