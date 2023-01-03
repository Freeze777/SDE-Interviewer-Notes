package leetcode.easy.array

/**
 * 944. Delete Columns to Make Sorted
 * https://leetcode.com/problems/delete-columns-to-make-sorted/
 */
class DeleteColumnsMakeSorted {
    fun minDeletionSize(arr: Array<String>): Int {
        var count = 0
        for (col in arr[0].indices) {
            inner@ for (row in arr.indices) {
                if (row <= arr.size - 2 && arr[row][col] > arr[row + 1][col]) {
                    count++
                    break@inner
                }
            }
        }
        return count
    }
}

fun main() {
    val s = DeleteColumnsMakeSorted()
    println(s.minDeletionSize(arrayOf("cba", "daf", "ghi"))) // 1
    println(s.minDeletionSize(arrayOf("a", "b"))) // 0
    println(s.minDeletionSize(arrayOf("zyx", "wvu", "tsr"))) // 3
}