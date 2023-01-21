package leetcode.medium.backtracking

/**
 * 93. Restore IP Addresses
 * https://leetcode.com/problems/restore-ip-addresses/
 */
class RestoreIpAddresses {
    fun restoreIpAddresses(s: String) = restoreIpAddresses(s, 1, listOf((s[0] - '0')))
        .map { it.joinToString(".") }

    private fun restoreIpAddresses(s: String, index: Int, ip: List<Int>): List<List<Int>> {
        if (s.length < 4 || ip.size > 4) return listOf()
        if (index >= s.length) return if (ip.size == 4) listOf(ip) else listOf()
        val result = mutableListOf<List<Int>>()
        val newDigit = s[index] - '0'
        if (ip.last() != 0) {
            val newOctet = ip.last() * 10 + newDigit
            if (newOctet <= 255) {
                val newIp = ip.toMutableList()
                newIp[newIp.lastIndex] = newOctet
                result.addAll(restoreIpAddresses(s, index + 1, newIp))
            }
        }
        result.addAll(restoreIpAddresses(s, index + 1, ip.plus(newDigit)))
        return result
    }
}

fun main() {
    val ria = RestoreIpAddresses()
    println(ria.restoreIpAddresses("25525511135"))
    println(ria.restoreIpAddresses("0000"))
    println(ria.restoreIpAddresses("101023"))
    println(ria.restoreIpAddresses("10128111"))
    println(ria.restoreIpAddresses("11111111"))
    println(ria.restoreIpAddresses("1"))
    println(ria.restoreIpAddresses("11"))
}