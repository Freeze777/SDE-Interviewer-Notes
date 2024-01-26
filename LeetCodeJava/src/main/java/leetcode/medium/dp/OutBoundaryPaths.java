package leetcode.medium.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/out-of-boundary-paths/"> 576. Out of Boundary Paths</a>
 */
public class OutBoundaryPaths {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private final int[][][] cache = new int[50][50][51];

    private void initializeCache() {
        for (int[][] arr2D : cache) for (int j = 0; j < cache[0].length; j++) Arrays.fill(arr2D[j], -1);
    }

    OutBoundaryPaths() {
        initializeCache();
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove < 0) return 0;
        if (startRow < 0 || startColumn < 0 || startRow >= m || startColumn >= n) return 1;
        if (cache[startRow][startColumn][maxMove] != -1) return cache[startRow][startColumn][maxMove];
        int paths = 0;
        for (int[] dir : DIRECTIONS)
            paths = (paths + findPaths(m, n, maxMove - 1, startRow + dir[0], startColumn + dir[1]) % MOD) % MOD;
        return (cache[startRow][startColumn][maxMove] = paths);
    }

    public static void main(String[] args) {
        var obp = new OutBoundaryPaths();
        System.out.println(obp.findPaths(2, 2, 2, 0, 0));//6
        System.out.println(obp.findPaths(1, 3, 3, 0, 1));//13
        System.out.println(obp.findPaths(8, 7, 16, 1, 5));//114385532
        System.out.println(obp.findPaths(50, 50, 50, 25, 25));//496204127
    }
}


