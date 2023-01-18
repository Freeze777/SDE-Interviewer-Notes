package leetcode.medium.dp

/**
 * 918. Maximum Sum Circular Subarray
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 */
class MaxSumCircularSubarray {
    fun maxSubarraySumCircular(nums: IntArray): Int {
        if (nums.all { it < 0 }) return nums.max()
        val linearSubArrayMinSum = kadanesAlgorithm(nums) { a, b -> minOf(a, b) }
        val circularSubArrayMaxSum = nums.sum() - linearSubArrayMinSum
        val linearSubArrayMaxSum = kadanesAlgorithm(nums) { a, b -> maxOf(a, b) }
        return maxOf(linearSubArrayMaxSum, circularSubArrayMaxSum)
    }

    private fun kadanesAlgorithm(nums: IntArray, f: (a: Int, b: Int) -> Int): Int {
        var maxSum = nums[0]
        var currSum = 0
        for (num in nums) {
            currSum = f(num, currSum + num)
            maxSum = f(maxSum, currSum)
        }
        return maxSum
    }
}

fun main() {
    val mcs = MaxSumCircularSubarray()
    println(mcs.maxSubarraySumCircular(intArrayOf(-3, -2, -3)))//-2
    println(mcs.maxSubarraySumCircular(intArrayOf(-2, -3, -1)))//-1
    println(mcs.maxSubarraySumCircular(intArrayOf(1, -2, 3, -2)))//3
    println(mcs.maxSubarraySumCircular(intArrayOf(5, -3, 5)))//10
    println(mcs.maxSubarraySumCircular(intArrayOf(3, -1, 2, -1)))//4
    println(mcs.maxSubarraySumCircular(intArrayOf(3, -2, 2, -3)))//3
    println(mcs.maxSubarraySumCircular(intArrayOf(1, 2, 3, -2, -3, -1, 2)))//8
    println(mcs.maxSubarraySumCircular(intArrayOf(1, 2, 3, 4, 5, 6, 100)))//121
    println(mcs.maxSubarraySumCircular(intArrayOf(-1, -2, -3, 100, -4, -5, -10)))//100
}
