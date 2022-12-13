package leetcode.medium.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/minimum-falling-path-sum/">https://leetcode.com/problems/minimum-falling-path-sum/</a>
 */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int[] dp : cache) Arrays.fill(dp, Integer.MAX_VALUE);
        // DP Tabulation
        System.arraycopy(matrix[matrix.length - 1], 0, cache[matrix.length - 1], 0, matrix.length);
        for (int row = matrix.length - 2; row >= 0; row--) {
            for (int col = 0; col < matrix.length; col++) {
                int min = cache[row + 1][col] + matrix[row][col];//down
                if (col >= 1) min = Math.min(cache[row + 1][col - 1] + matrix[row][col], min);//left
                if (col < matrix[0].length - 1) min = Math.min(cache[row + 1][col + 1] + matrix[row][col], min);//right
                cache[row][col] = min;
            }
        }
        return Arrays.stream(cache[0]).min().getAsInt();
    }

    public static void main(String[] args) {
        var mfps = new MinFallingPathSum();
        System.out.println(mfps.minFallingPathSum(new int[][]{{2, 1, 3}, {6, 5, 4}, {7, 8, 9}}));//13
        System.out.println(mfps.minFallingPathSum(new int[][]{{-19, 57}, {-40, -5}}));//-59
        System.out.println(mfps.minFallingPathSum(new int[][]{{-48}}));//-48
    }
}
