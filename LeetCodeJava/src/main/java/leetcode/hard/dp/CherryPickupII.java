package leetcode.hard.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/cherry-pickup-ii/">Cherry Pickup II</a>
 */
public class CherryPickupII {

    record CacheKey(int row, int col1, int col2) {
    }

    private final int[] colDirs = new int[]{-1, 0, 1};

    private boolean isInvalid(int[][] grid, int col) {
        return (col >= grid[0].length || col < 0);
    }

    public int cherryPickup(int[][] grid) {
        return cherryPickup(grid, 0, 0, grid[0].length - 1, new HashMap<>());
    }

    private int cherryPickup(int[][] grid, int row, int robo1Col, int robo2Col, Map<CacheKey, Integer> cache) {
        if (row >= grid.length) return 0;
        var key = new CacheKey(row, robo1Col, robo2Col);
        if (cache.containsKey(key)) return cache.get(key);
        var bestMove = 0;
        var isSameCell = (robo1Col == robo2Col);
        for (int robo1ColDir : colDirs) {
            var nextRow = row + 1;
            var nextRobo1Col = robo1Col + robo1ColDir;
            if (isInvalid(grid, nextRobo1Col)) continue;
            for (int robo2ColDir : colDirs) {
                int nextRobo2Col = robo2Col + robo2ColDir;
                if (isInvalid(grid, nextRobo2Col)) continue;
                int currentMove = grid[row][robo1Col] + grid[row][robo2Col]
                        + cherryPickup(grid, nextRow, nextRobo1Col, nextRobo2Col, cache)
                        - (isSameCell ? grid[row][robo1Col] : 0);
                bestMove = Math.max(bestMove, currentMove);
            }
        }
        cache.put(key, bestMove);
        return bestMove;
    }

    public static void main(String[] args) {
        var cp = new CherryPickupII();
        System.out.println(cp.cherryPickup(new int[][]{{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}}));
        System.out.println(cp.cherryPickup(new int[][]{{4, 0, 0, 0, 0, 0, 0, 0, 0, 86}, {0, 52, 0, 0, 0, 0, 0, 0, 48, 0}, {0, 0, 94, 0, 0, 0, 0, 74, 0, 0}, {0, 0, 0, 98, 0, 0, 25, 0, 0, 0}, {0, 0, 0, 0, 70, 66, 0, 0, 0, 0}}));
        System.out.println(cp.cherryPickup(new int[][]{{0, 40, 0, 0, 0, 0, 0, 0, 93, 0}, {0, 0, 41, 0, 0, 0, 0, 59, 0, 0}, {89, 0, 0, 0, 0, 0, 0, 0, 0, 28}, {0, 32, 0, 0, 0, 0, 0, 0, 80, 0}, {0, 0, 40, 0, 0, 0, 0, 0, 0, 0}, {50, 0, 0, 0, 0, 0, 0, 0, 0, 66}, {0, 90, 0, 0, 0, 0, 0, 0, 11, 0}, {0, 0, 62, 0, 0, 0, 0, 12, 0, 0}, {95, 0, 0, 0, 0, 0, 0, 0, 0, 88}, {0, 31, 0, 0, 0, 0, 0, 0, 26, 0}}));
        System.out.println(cp.cherryPickup(new int[][]{{1, 0, 0, 0, 0, 0, 0, 0, 0, 2}, {0, 6, 0, 0, 0, 0, 0, 0, 6, 0}, {0, 0, 9, 0, 0, 0, 0, 3, 0, 0}, {0, 0, 0, 8, 0, 0, 5, 0, 0, 0}, {100, 0, 0, 0, 2, 3, 0, 0, 0, 100}}));
        System.out.println(cp.cherryPickup(new int[][]{{9, 79}, {49, 85}, {54, 48}, {37, 72}, {68, 14}, {53, 30}, {65, 80}, {94, 18}, {89, 46}, {7, 93}}));
        System.out.println(cp.cherryPickup(new int[][]{{13, 14, 37, 49, 64, 98, 4, 11, 47, 81}, {71, 46, 50, 50, 10, 14, 35, 35, 52, 69}}));
    }
}
