package leetcode.medium.array.prefixsum

/**
 * 974. Subarray Sums Divisible by K
 * https://leetcode.com/problems/subarray-sums-divisible-by-k
 */
class SubarraySumDivisibleByK {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        val (prefixSum, lookup) = computePrefixSum(nums, k)
        var total = 0
        for (index in nums.indices) {
            if (prefixSum[index] == 0) total++
            val target = prefixSum[index] // (prefix[j] - prefix[i]) mod k = 0
            if (lookup.containsKey(target)) {
                val totalPreviousIndices = lookup[target]!!.binarySearch(index).let { if (it < 0) -it - 1 else it }
                total += totalPreviousIndices
            }
        }
        return total
    }

    private fun computePrefixSum(nums: IntArray, k: Int): Pair<IntArray, MutableMap<Int, MutableList<Int>>> {
        val prefixSum = IntArray(nums.size)
        prefixSum[0] = ((nums[0].rem(k)) + k) % k
        val lookup = mutableMapOf(prefixSum[0] to mutableListOf(0))
        for (i in 1 until nums.size) {
            prefixSum[i] = ((prefixSum[i - 1] + nums[i]).rem(k) + k) % k
            lookup.computeIfAbsent(prefixSum[i]) { mutableListOf() }.add(i)
        }
        return Pair(prefixSum, lookup)
    }
}

fun main() {
    val ssdbk = SubarraySumDivisibleByK()
    println(ssdbk.subarraysDivByK(intArrayOf(4, 5, 0, -2, -3, 1), 5))// 7
    println(ssdbk.subarraysDivByK(intArrayOf(-1, 2, 9), 2))// 2
    println(ssdbk.subarraysDivByK(intArrayOf(1, 2, 3), 3))// 3
    println(ssdbk.subarraysDivByK(intArrayOf(1, 2, 3), 4))// 0
}
