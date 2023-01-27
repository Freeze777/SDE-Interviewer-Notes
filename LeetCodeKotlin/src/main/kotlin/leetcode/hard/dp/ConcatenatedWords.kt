package leetcode.hard.dp

/**
 * 472. Concatenated Words
 * https://leetcode.com/problems/concatenated-words/
 */
class ConcatenatedWords {
    fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
        val set = HashSet(words.toList())
        val answer = mutableListOf<String>()
        for (word in words) {
            val dp = BooleanArray(word.length + 1)
            dp[0] = true
            for (i in 1..word.length) {
                val jStart = if (i == word.length) 1 else 0
                for (j in jStart until i) {
                    if (dp[j] && set.contains(word.substring(j, i))) {
                        dp[i] = true
                    }
                }
            }
            if (dp[word.length]) answer.add(word)
        }
        return answer
    }
}

fun main() {
    val cw = ConcatenatedWords()
    println(
        cw.findAllConcatenatedWordsInADict(
            arrayOf(
                "cat",
                "cats",
                "catsdogcats",
                "dog",
                "dogcatsdog",
                "hippopotamuses",
                "rat",
                "ratcatdogcat"
            )
        )
    )//["catsdogcats", "dogcatsdog", "ratcatdogcat"]
    println(
        cw.findAllConcatenatedWordsInADict(
            arrayOf(
                "cat",
                "cats",
                "catsdogcats",
                "dog",
                "dogcatsdog",
                "hippopotamuses",
                "rat",
                "ratcatdogcat",
                "dogcatcat"
            )
        )
    )//["catsdogcats", "dogcatsdog", "ratcatdogcat", "dogcatcat"]
}