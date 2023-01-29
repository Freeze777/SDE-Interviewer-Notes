package leetcode.medium.backtracking

/**
 * 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 */
class LetterCasePermutation {
    fun letterCasePermutation(s: String) = letterCasePermutations(0, s, listOf())
    private fun letterCasePermutations(index: Int, s: String, permutation: List<Char>): List<String> {
        if (index == s.length) return listOf(permutation.joinToString(""))
        val result = mutableListOf<String>()
        val c = s[index]
        if (c.isLetter()) {
            val next = if (c.isUpperCase()) c.lowercaseChar() else c.uppercaseChar()
            result.addAll(letterCasePermutations(index + 1, s, permutation + next))
        }
        result.addAll(letterCasePermutations(index + 1, s, permutation + c))
        return result
    }
}

fun main() {
    val l = LetterCasePermutation()
    println(l.letterCasePermutation("a1b2"))// ["a1b2", "a1B2", "A1b2", "A1B2"]
    println(l.letterCasePermutation("3z4"))// ["3z4", "3Z4"]
    println(l.letterCasePermutation("12345"))// ["12345"]
    println(l.letterCasePermutation("0"))// ["0"]
    println(l.letterCasePermutation("C"))// ["c", "C"]
}