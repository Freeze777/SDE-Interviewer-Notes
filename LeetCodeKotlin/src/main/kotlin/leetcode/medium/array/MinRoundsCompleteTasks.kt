package leetcode.medium.array

/**
 * 1986. Minimum Number of Work Sessions to Finish the Tasks
 * https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
 */
class MinRoundsCompleteTasks {

    fun minimumRounds(tasks: IntArray): Int {
        val frequencies = tasks.asList().groupingBy { it }.eachCount().values
        var count = 0
        for (frequency in frequencies) {
            count += if (frequency == 1)
                return -1
            else {
                val (x, y) = solve2x3y(frequency)
                x + y
            }
        }
        return count
    }

    fun solve2x3y(frequency: Int): Pair<Int, Int> {
        if (frequency % 3 == 0)
            return (0 to frequency / 3)
        var freq = frequency
        var x = 0
        var y = 0
        while (true) {
            freq -= 2
            x++
            if (freq == 0) break
            if (freq % 3 == 0) {
                y = freq / 3
                break
            }
        }
        return (x to y)
    }
}

fun main() {
    val mrct = MinRoundsCompleteTasks()
    println(mrct.minimumRounds(intArrayOf(2, 2, 3, 3, 2, 4, 4, 4, 4, 4)))// 4
    println(mrct.minimumRounds(intArrayOf(2, 3, 3)))// -1
    println(mrct.minimumRounds(intArrayOf(4, 4, 4, 4, 4, 4, 4, 4)))// 3
}
