package leetcode.hard.misc

/**
 * 2306. Naming a Company
 * https://leetcode.com/problems/naming-a-company/
 */
class NamingCompany {
    fun distinctNames(ideas: List<String>): Long {
        val map = HashMap<Char, MutableSet<String>>()
        for (c in 'a'..'z') map[c] = mutableSetOf()
        for (w in ideas) map[w[0]]?.add(w.substring(1))
        var sum = 0L
        for (a in 'a'..'z') {
            for (b in a..'z') {
                if (map[a].isNullOrEmpty() || map[b].isNullOrEmpty() || a == b) continue
                val common = map[a]?.intersect(map[b]!!) ?: mutableSetOf()
                val aMap = map[a]?.minus(common)
                val bMap = map[b]?.minus(common)
                sum += aMap!!.size * bMap!!.size * 2
            }
        }
        return sum
    }
}

fun main() {
    val nc = NamingCompany()
    println(nc.distinctNames(listOf("coffee", "donuts", "time", "toffee")))// 4
    println(nc.distinctNames(listOf("coffee", "donuts", "time", "toffee", "coffees")))// 10
    println(nc.distinctNames(listOf("coffee", "donuts", "time", "toffee", "coffees", "donut")))// 18
}
