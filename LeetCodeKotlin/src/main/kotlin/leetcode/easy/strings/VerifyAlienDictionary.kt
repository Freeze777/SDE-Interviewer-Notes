package leetcode.easy.strings

/**
 * 953. Verifying an Alien Dictionary
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
class VerifyAlienDictionary {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val orders = order.mapIndexed { index, c -> c to index }.toMap()
        val comparator = Comparator<String> { a, b ->
            a.zip(b)
                .forEach { (aChar, bChar) -> if (aChar != bChar) return@Comparator orders[aChar]!! - orders[bChar]!! }
            a.length - b.length
        }
        val wordsClone = words.clone()
        wordsClone.sortWith(comparator)
        return wordsClone.contentEquals(words)
    }
}

fun main() {
    val p = VerifyAlienDictionary()
    println(p.isAlienSorted(arrayOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz"))//true
    println(p.isAlienSorted(arrayOf("word", "world", "row"), "worldabcefghijkmnpqstuvxyz"))//false
    println(p.isAlienSorted(arrayOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz"))//false
    println(p.isAlienSorted(arrayOf("kuvp", "q"), "ngxlkthsjuoqcpavbfdermiywz"))//true
}
