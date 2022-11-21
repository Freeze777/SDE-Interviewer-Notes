package leetcode.medium.graph;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/">https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/</a>
 */
public class NearestExitMaze {

    private static class MazeCell {
        Point cell;
        int depth;

        public MazeCell(Point cell, int depth) {
            this.cell = cell;
            this.depth = depth;
        }
    }

    private final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static List<MazeCell> nextNodes(MazeCell mazeCell, char[][] maze) {
        return Arrays.stream(dirs)
                .map(dir -> new Point(mazeCell.cell.x + dir[0], mazeCell.cell.y + dir[1]))
                .filter(point -> point.x >= 0 && point.x < maze.length && point.y >= 0
                        && point.y < maze[0].length && maze[point.x][point.y] == '.')
                .map(point -> new MazeCell(point, mazeCell.depth + 1))
                .collect(Collectors.toList());
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<MazeCell> queue = new LinkedList<>();
        Point start = new Point(entrance[0], entrance[1]);
        queue.add(new MazeCell(start, 0));
        maze[start.x][start.y] = '+';
        while (!queue.isEmpty()) {
            MazeCell mazeCell = queue.poll();
            if (!mazeCell.cell.equals(start) && (mazeCell.cell.x == 0 || mazeCell.cell.x == maze.length - 1 || mazeCell.cell.y == 0
                    || mazeCell.cell.y == maze[0].length - 1)) {
                return mazeCell.depth;
            }
            List<MazeCell> nextMazeCells = nextNodes(mazeCell, maze);
            queue.addAll(nextMazeCells);
            nextMazeCells.forEach(nextMazeCell -> maze[nextMazeCell.cell.x][nextMazeCell.cell.y] = '+'); // avoids TLE
        }
        return -1;
    }

    public static void main(String[] args) {
        char[][] arr = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        int[] entrance = {1, 2};
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//1

        arr = new char[][]{{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
        entrance = new int[]{1, 0};
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//2

        arr = new char[][]{{'+', '.', '+'}, {'.', '.', '.'}, {'+', '.', '+'}};
        entrance = new int[]{1, 0};
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//2

        arr = new char[][]{{'+', '.', '+'}, {'.', '.', '.'}, {'+', '.', '+'}};
        entrance = new int[]{1, 2};
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//2

        arr = new char[][]{{'+', '.', '+'}, {'.', '.', '.'}, {'+', '.', '+'}};
        entrance = new int[]{0, 0};
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//1
    }
}
