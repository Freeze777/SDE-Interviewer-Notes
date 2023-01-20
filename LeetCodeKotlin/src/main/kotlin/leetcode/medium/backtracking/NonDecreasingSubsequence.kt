package leetcode.medium.backtracking

import leetcode.utils.randomIntArray

/**
 * 491. Non-decreasing Subsequences
 * https://leetcode.com/problems/non-decreasing-subsequences/
 */
class NonDecreasingSubsequence {
    private fun findSubsequences(prev: List<Int>, current: Int, nums: IntArray): MutableSet<List<Int>> {
        if (current >= nums.size) return if (prev.size >= 2) mutableSetOf(prev) else mutableSetOf()
        val total = findSubsequences(prev, current + 1, nums)
        if (prev.isEmpty() || prev.last() <= nums[current])
            total.addAll(findSubsequences(prev.plus(nums[current]), current + 1, nums))
        return total
    }

    fun findSubsequences(nums: IntArray) = findSubsequences(listOf(), 0, nums).toList()
}

fun main() {
    val nds = NonDecreasingSubsequence()
    println(nds.findSubsequences(intArrayOf(4, 6, 7, 7)))
    println(nds.findSubsequences(intArrayOf(4, 4, 3, 2, 1)))
    println(nds.findSubsequences(intArrayOf(1, 5, 1, 3, 6, 7)))
    println(nds.findSubsequences(intArrayOf(1, 1, 1, 1, 1, 1)))
    // measure worst case time
    val start = System.currentTimeMillis()
    println(nds.findSubsequences(randomIntArray(size = 15, min = 10, max = 10)))
    println("${System.currentTimeMillis() - start}ms")
}
