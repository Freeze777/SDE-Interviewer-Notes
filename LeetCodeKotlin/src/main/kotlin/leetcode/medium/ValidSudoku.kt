package leetcode.medium

/**
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 */
class ValidSudoku {

    private val `3by3MatrixPoints` = listOf(
        Pair(0, 0),
        Pair(0, 3),
        Pair(0, 6),
        Pair(3, 0),
        Pair(3, 3),
        Pair(3, 6),
        Pair(6, 0),
        Pair(6, 3),
        Pair(6, 6)
    )

    private fun rowCheck(arr: Array<CharArray>, row: Int): Boolean {
        val set = HashSet<Char>()
        for (i in arr[row].indices) {
            if (arr[row][i] == '.') continue
            if (set.contains(arr[row][i])) return false
            set.add(arr[row][i])
        }
        return true
    }

    private fun colCheck(arr: Array<CharArray>, col: Int): Boolean {
        val set = HashSet<Char>()
        for (i in arr.indices) {
            if (arr[i][col] == '.') continue
            if (set.contains(arr[i][col])) return false
            set.add(arr[i][col])
        }
        return true
    }

    private fun `3by3MatrixCheck`(arr: Array<CharArray>, row: Int, col: Int): Boolean {
        val set = HashSet<Char>()
        for (i in 0..2) {
            for (j in 0..2) {
                if (arr[row + i][col + j] == '.') continue
                if (set.contains(arr[row + i][col + j])) return false
                set.add(arr[row + i][col + j])
            }
        }
        return true
    }


    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (row in board.indices) if (!rowCheck(board, row)) return false
        for (col in board[0].indices) if (!colCheck(board, col)) return false
        for (point in `3by3MatrixPoints`) if (!`3by3MatrixCheck`(board, point.first, point.second)) return false
        return true
    }
}

fun main() {
    val s = ValidSudoku()
    val board = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )
    println(s.isValidSudoku(board))// true

    val board2 = arrayOf(
        charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )
    println(s.isValidSudoku(board2))// false
}