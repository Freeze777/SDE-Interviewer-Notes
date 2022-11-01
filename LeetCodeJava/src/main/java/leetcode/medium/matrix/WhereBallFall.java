package leetcode.medium.matrix;

import java.awt.Point;
import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/where-will-the-ball-fall/">https://leetcode.com/problems/where-will-the-ball-fall/</a>
 */
public class WhereBallFall {

    public boolean isBlocked(Point position, int[][] grid) {
        if (grid[position.x][position.y] == 1 && (position.y >= grid[0].length - 1 || grid[position.x][position.y + 1] == -1))
            return true;
        if (grid[position.x][position.y] == -1 && (position.y < 1 || grid[position.x][position.y - 1] == 1))
            return true;
        return false;
    }

    public Point nextMove(Point curr, int[][] grid) {
        if (isBlocked(curr, grid)) return null;
        if (grid[curr.x][curr.y] == 1) return new Point(curr.x + 1, curr.y + 1);
        return new Point(curr.x + 1, curr.y - 1);
    }

    public int[] findBall(int[][] grid) {
        int[] ans = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            Point position = new Point(0, i);
            while (position != null) {
                if (position.x >= grid.length || position.x < 0) break;
                position = nextMove(position, grid);
            }
            ans[i] = (position == null) ? -1 : position.y;
        }
        return ans;
    }

    public static void main(String[] args) {
        WhereBallFall wbf = new WhereBallFall();
        System.out.println(Arrays.toString(wbf.findBall(new int[][]{{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}}))); //[1, -1, -1, -1, -1]
        System.out.println(Arrays.toString(wbf.findBall(new int[][]{{-1}}))); //[-1]
        System.out.println(Arrays.toString(wbf.findBall(new int[][]{{1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}}))); //[0, 1, 2, 3, 4, -1]
    }
}
