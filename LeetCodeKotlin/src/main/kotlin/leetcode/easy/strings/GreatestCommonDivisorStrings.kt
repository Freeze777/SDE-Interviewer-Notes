package leetcode.easy.strings

/**
 * 1071. Greatest Common Divisor of Strings
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
class GreatestCommonDivisorStrings {
    fun gcdOfStrings(s: String, t: String): String {
        if (s.toSet() != t.toSet()) return ""
        for (candidate in getCandidates(t)) if (s.replace(candidate, "") == "") return candidate
        return ""
    }

    private fun getCandidates(t: String): List<String> {
        val subs = mutableListOf(t)
        for (i in 0..t.length / 2) {
            val sub = t.substring(0, i)
            if (t.replace(sub, "").isEmpty()) subs.add(sub)
        }
        subs.sortBy { -it.length }
        return subs
    }
}

fun main() {
    val gcds = GreatestCommonDivisorStrings()
    println(gcds.gcdOfStrings("ababab", "ab"))//ab
    println(gcds.gcdOfStrings("abaaba", "aba"))//aba
    println(gcds.gcdOfStrings("abababa", "abac"))//""
    println(gcds.gcdOfStrings("abababa", "a"))//""
    println(gcds.gcdOfStrings("aaaaaaaaa", "aaa"))//"aaa"
}

