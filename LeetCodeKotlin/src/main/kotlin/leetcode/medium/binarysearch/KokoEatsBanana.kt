package leetcode.medium.binarysearch

import kotlin.math.ceil

/**
 * 875. Koko Eating Bananas
 * https://leetcode.com/problems/koko-eating-bananas/
 */
class KokoEatsBanana {

    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var (low, high) = 1 to piles.max()!!
        while (low < high) {
            val mid = low + (high - low) / 2
            if (canEatAllBananas(piles, h, mid)) high = mid
            else low = mid + 1
        }
        return low
    }

    private fun canEatAllBananas(piles: IntArray, h: Int, mid: Int): Boolean {
        var hours = 0
        for (pile in piles) hours += ceil(pile.toDouble() / mid).toInt()
        return hours <= h
    }
}

fun main() {
    val keb = KokoEatsBanana()
    println(keb.minEatingSpeed(intArrayOf(3, 6, 7, 11), 8))// 4
    println(keb.minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 5))// 30
    println(keb.minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 6))// 23
}