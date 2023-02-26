package leetcode.hard.dp

/**
 * 72. Edit Distance
 * https://leetcode.com/problems/edit-distance/
 */
class EditDistance {
    fun minDistance(
        word1: String, word2: String,
        i: Int = 0, j: Int = 0,
        cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
    ): Int {
        if (i == word1.length) return word2.length - j
        if (j == word2.length) return word1.length - i
        val key = Pair(i, j)
        if (cache.containsKey(key)) return cache[key]!!
        if (word1[i] == word2[j]) {
            cache[key] = minDistance(word1, word2, i + 1, j + 1, cache)
            return cache[key]!!
        }
        val insert = minDistance(word1, word2, i, j + 1, cache)
        val delete = minDistance(word1, word2, i + 1, j, cache)
        val replace = minDistance(word1, word2, i + 1, j + 1, cache)
        cache[key] = 1 + minOf(insert, delete, replace)
        return cache[key]!!
    }
}

fun main() {
    val ed = EditDistance()
    println(ed.minDistance("horse", "ros"))// 3
    println(ed.minDistance("intention", "execution"))// 5
}