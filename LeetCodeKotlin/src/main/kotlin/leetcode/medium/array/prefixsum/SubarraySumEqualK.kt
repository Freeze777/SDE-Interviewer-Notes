package leetcode.medium.array.prefixsum

/**
 * 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
class SubarraySumEqualK {
    fun subarraySumK(nums: IntArray, k: Int): Int {
        val (prefixSum, lookup) = computePrefixSum(nums)
        var total = 0
        for (index in nums.indices) {
            if (prefixSum[index] == k) total++
            val target = prefixSum[index] - k // prefix[j] - prefix[i] = k
            if (lookup.containsKey(target)) {
                val totalPreviousIndices = lookup[target]!!.binarySearch(index).let { if (it < 0) -it - 1 else it }
                total += totalPreviousIndices
            }
        }
        return total
    }

    private fun computePrefixSum(nums: IntArray): Pair<IntArray, MutableMap<Int, MutableList<Int>>> {
        val prefixSum = IntArray(nums.size)
        prefixSum[0] = nums[0]
        val lookup = mutableMapOf(prefixSum[0] to mutableListOf(0))
        for (i in 1 until nums.size) {
            prefixSum[i] = prefixSum[i - 1] + nums[i]
            lookup.computeIfAbsent(prefixSum[i]) { mutableListOf() }.add(i)
        }
        return Pair(prefixSum, lookup)
    }
}

fun main() {
    val ssek = SubarraySumEqualK()
    println(ssek.subarraySumK(intArrayOf(1, 2, 1, 2, 1), 3))// 4
    println(ssek.subarraySumK(intArrayOf(1, 1, 1), 2))// 2
    println(ssek.subarraySumK(intArrayOf(1, 2, 3), 3))// 2
    println(ssek.subarraySumK(intArrayOf(1, 2, 3), 4))// 0
    println(ssek.subarraySumK(intArrayOf(1, 2, 3), 5))// 1
}
