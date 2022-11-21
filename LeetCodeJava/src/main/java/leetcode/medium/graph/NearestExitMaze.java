package leetcode.medium.graph;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;

public class NearestExitMaze {

    private static class Node {
        Point point;
        int depth;

        public Node(Point point, int depth) {
            this.point = point;
            this.depth = depth;
        }
    }

    private final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static List<Node> nextNodes(Node node, char[][] maze, Set<Point> visited) {
        return Arrays.stream(dirs)
                .map(dir -> new Point(node.point.x + dir[0], node.point.y + dir[1]))
                .filter(point -> point.x >= 0 && point.x < maze.length && point.y >= 0
                        && point.y < maze[0].length && maze[point.x][point.y] == '.' && !visited.contains(point))
                .map(point -> new Node(point, node.depth + 1))
                .collect(Collectors.toList());
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<Node> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Point start = new Point(entrance[0], entrance[1]);
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited.add(node.point);
            if (!node.point.equals(start) && (node.point.x == 0 || node.point.x == maze.length - 1 || node.point.y == 0
                    || node.point.y == maze[0].length - 1)) {
                return node.depth;
            }

            queue.addAll(nextNodes(node, maze, visited));
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
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//1

        arr = new char[][]{{'+', '.', '+'}, {'.', '.', '.'}, {'+', '.', '+'}};
        entrance = new int[]{1, 2};
        System.out.println(new NearestExitMaze().nearestExit(arr, entrance));//-1
    }
}
