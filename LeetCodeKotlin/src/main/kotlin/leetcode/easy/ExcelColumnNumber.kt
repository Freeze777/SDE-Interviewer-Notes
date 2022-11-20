package leetcode.easy

/**
 * 171. Excel Sheet Column Number
 * https://leetcode.com/problems/excel-sheet-column-number/
 */
class ExcelColumnNumber {
    fun titleToNumber(columnTitle: String): Int {
        var mul = 1
        var ans = 0
        columnTitle.reversed().forEach { x ->
            ans += (x - 'A' + 1) * mul
            mul *= 26
        }
        return ans
    }
}

fun main() {
    val s = ExcelColumnNumber()
    println(s.titleToNumber("A"))
    println(s.titleToNumber("AB"))
    println(s.titleToNumber("ZY"))
    println(s.titleToNumber("FXSHRXW"))
}