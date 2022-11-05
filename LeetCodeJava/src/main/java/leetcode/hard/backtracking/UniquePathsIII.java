package leetcode.hard.backtracking;

import java.awt.*;

/**
 * <a href="https://leetcode.com/problems/unique-paths-iii/">https://leetcode.com/problems/unique-paths-iii/</a>
 */
public class UniquePathsIII {

    private int uniquePaths(int[][] grid, int i, int j, int steps, int numEmpty, String path) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length) {
            if (grid[i][j] == 2 && steps == numEmpty) {
                System.err.println(path + "(" + i + "," + j + ")");
                return 1;
            }
            if (grid[i][j] == -1) return 0;
            steps = grid[i][j] == 0 ? steps + 1 : steps;
            int value = grid[i][j];
            grid[i][j] = -1;
            path = path + "(" + i + "," + j + ")->";
            int sum = uniquePaths(grid, i + 1, j, steps, numEmpty, path)
                    + uniquePaths(grid, i - 1, j, steps, numEmpty, path)
                    + uniquePaths(grid, i, j + 1, steps, numEmpty, path)
                    + uniquePaths(grid, i, j - 1, steps, numEmpty, path);
            grid[i][j] = value;
            return sum;
        }
        return 0;
    }

    public int uniquePathsIII(int[][] grid) {
        int numEmpty = 0;
        Point start = new Point(0, 0);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) start = new Point(i, j);
                if (grid[i][j] == 0) numEmpty++;
            }
        }
        return uniquePaths(grid, start.x, start.y, 0, numEmpty, "");
    }

    public static void main(String[] args) {
        UniquePathsIII upii = new UniquePathsIII();
        System.out.println(upii.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
        System.out.println(upii.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
        System.out.println(upii.uniquePathsIII(new int[][]{{0, 1}, {2, 0}}));
    }
}
