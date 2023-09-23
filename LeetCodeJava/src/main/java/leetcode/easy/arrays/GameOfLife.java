package leetcode.easy.arrays;

import java.util.Arrays;

import static java.lang.System.arraycopy;

/**
 * <a href="https://leetcode.com/problems/game-of-life/">Game of Life</a>
 */
public class GameOfLife {

    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public void gameOfLife(int[][] board) {
        int[][] backup = clone(board);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int alive = aliveNeighbours(backup, row, col);
                if (board[row][col] == 1 && alive > 3) board[row][col] = 0;
                else if (board[row][col] == 1 && alive < 2) board[row][col] = 0;
                else if (board[row][col] == 0 && alive == 3) board[row][col] = 1;
            }
        }
    }

    private boolean isValid(int[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }

    private int aliveNeighbours(int[][] board, int row, int col) {
        int alive = 0;
        for (int[] dir : dirs)
            if (isValid(board, row + dir[0], col + dir[1]) && board[row + dir[0]][col + dir[1]] == 1) alive++;
        return alive;
    }

    private int[][] clone(int[][] board) {
        int[][] backup = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) arraycopy(board[row], 0, backup[row], 0, board[row].length);
        return backup;
    }

    public static void main(String[] args) {
        var gol = new GameOfLife();
        int[][] board = {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        gol.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));//[[0,0,0],[0,0,1],[1,0,1],[0,1,1]]

        board = new int[][]{{1, 1}, {1, 0}};
        gol.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));//[[1,1],[1,1]]

        board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gol.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));//[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
    }
}
