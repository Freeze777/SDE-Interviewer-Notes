package leetcode.medium.backtracking

/**
 * 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning/
 */
class PalindromePartitions {
    fun partition(s: String) = partition(s, 1, listOf(s[0].toString()))

    private fun partition(s: String, index: Int, partitions: List<String>): List<List<String>> {
        if (index >= s.length) return if (partitions.all { isPalindrome(it) }) listOf(partitions) else listOf()
        val result = mutableListOf<List<String>>()
        val newLast = partitions.last() + s[index]
        result.addAll(partition(s, index + 1, partitions.dropLast(1).plus(newLast)))
        if (isPalindrome(partitions.last()))
            result.addAll(partition(s, index + 1, partitions.plus(s[index].toString())))
        return result
    }

    private fun isPalindrome(s: String): Boolean {
        if (s.length == 1) return true
        for (i in 0 until s.length / 2) if (s[i] != s[s.length - 1 - i]) return false
        return true
    }
}

fun main() {
    val pp = PalindromePartitions()
    println(pp.partition("abba"))//[[a, b, b, a], [a, bb, a], [abba]]
    println(pp.partition("aab"))//[[a, a, b], [aa, b]]
    println(pp.partition("a"))//[[a]]
    println(pp.partition("aa"))//[[a, a], [aa]]
    println(pp.partition("abcd"))//[[a, b, c, d]]
    println(pp.partition("abbab"))//[[a, b, b, a, b], [a, b, bab], [abba, b], [a, bb, a, b]]
    // Huge input
    println(pp.partition("abbaabbaabbaabba").size)//553
    val start = System.currentTimeMillis()
    println(pp.partition("aaaaaaaaaaaaaaaa").size)//32768
    println("${System.currentTimeMillis() - start}ms")
}