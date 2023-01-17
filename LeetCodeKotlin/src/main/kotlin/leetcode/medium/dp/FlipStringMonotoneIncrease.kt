package leetcode.medium.dp

import kotlin.math.min

/**
 * 926. Flip String to Monotone Increasing
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/
 */
class FlipStringMonotoneIncrease {

    fun minFlipsMonoIncr(s: String): Int {
        val cache = HashMap<Pair<Int, Char>, Int>()
        fun minFlip(index: Int, prev: Char): Int {
            if (index == s.length) return 0
            val key = Pair(index, prev)
            if (cache.containsKey(key)) return cache[key]!!
            val result = when (Pair(prev, s[index])) {
                Pair('0', '0') -> min(1 + minFlip(index + 1, '1'), minFlip(index + 1, '0'))
                Pair('0', '1') -> min(1 + minFlip(index + 1, '0'), minFlip(index + 1, '1'))
                Pair('1', '0') -> 1 + minFlip(index + 1, '1')
                Pair('1', '1') -> minFlip(index + 1, '1')
                else -> 0
            }
            cache[key] = result
            return result
        }
        return min(minFlip(0, '0'), minFlip(0, '1'))
    }
}

fun main() {
    val fsmi = FlipStringMonotoneIncrease()
    println(fsmi.minFlipsMonoIncr("00110"))//1
    println(fsmi.minFlipsMonoIncr("010110"))//2
    println(fsmi.minFlipsMonoIncr("00011000"))//2
    println(fsmi.minFlipsMonoIncr("000110000"))//2
    println(fsmi.minFlipsMonoIncr("1001100100"))//4
    println(fsmi.minFlipsMonoIncr("10011000111"))//3
    //238
    val start = System.currentTimeMillis()
    println(fsmi.minFlipsMonoIncr("100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111100110001111001100011110011000111"))
    println("Time taken: ${System.currentTimeMillis() - start}ms")
}