package leetcode.medium.binarysearch

/**
 * 1011. Capacity To Ship Packages Within D Days
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */
class CapacityShipPackagesDays {
    fun shipWithinDays(weights: IntArray, days: Int): Int {
        val totalWeight = weights.sum()
        val maxWeight = weights.max()

        var (maxCapacity, minCapacity) = totalWeight to maxWeight
        while (minCapacity < maxCapacity) {
            val currentCapacity = minCapacity + (maxCapacity - minCapacity) / 2
            if (canShip(weights, days, currentCapacity)) {
                maxCapacity = currentCapacity
            } else {
                minCapacity = currentCapacity + 1
            }
        }
        return minCapacity
    }

    private fun canShip(weights: IntArray, days: Int, capacity: Int): Boolean {
        var (daysUsed, currentWeight) = 1 to 0
        for (weight in weights) {
            if (currentWeight + weight > capacity) {
                daysUsed++
                currentWeight = 0
            }
            currentWeight += weight
        }
        return daysUsed <= days
    }
}

fun main() {
    val cspd = CapacityShipPackagesDays()
    println(cspd.shipWithinDays(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5))// 15
    println(cspd.shipWithinDays(intArrayOf(3, 2, 2, 4, 1, 4), 3))// 6
    println(cspd.shipWithinDays(intArrayOf(1, 2, 3, 1, 1), 4))// 3
}