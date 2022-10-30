package leetcode.medium.graph;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShortestPathFood {
    private static class State {
        int pathSize;
        Point position;

        State(int pathSize, Point position) {
            this.pathSize = pathSize;
            this.position = position;
        }

        @Override
        public String toString() {
            return "State{" + "pathSize=" + pathSize + ", position=" + position + '}';
        }
    }

    private final List<Point> directions = Arrays.asList(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1));

    private Point getStart(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '*') return new Point(i, j);
            }
        }
        return null;
    }

    private List<State> getNextVisits(State state, char[][] grid, Set<Point> visited) {
        return directions.stream()
                .map(d -> new Point(state.position.x + d.x, state.position.y + d.y))
                .filter(p -> (p.x >= 0 && p.y >= 0 && p.x < grid.length && p.y < grid[0].length
                        && !visited.contains(p) && grid[p.x][p.y] != 'X'))
                .map(p -> new State(state.pathSize + 1, p))
                .collect(Collectors.toList());
    }

    public int getFood(char[][] grid) {
        Queue<State> bfsQ = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        bfsQ.add(new State(0, getStart(grid)));
        int minPath = Integer.MAX_VALUE;
        while (!bfsQ.isEmpty()) {
            State current = bfsQ.remove();
            if (grid[current.position.x][current.position.y] == '#') minPath = Math.min(minPath, current.pathSize);
            if (visited.contains(current.position)) continue;
            bfsQ.addAll(getNextVisits(current, grid, visited));
            visited.add(current.position);
        }
        return minPath == Integer.MAX_VALUE ? -1 : minPath;
    }

    public static void main(String[] args) {
        ShortestPathFood spf = new ShortestPathFood();
        System.out.println(spf.getFood(new char[][]{{'X', 'X', 'X', 'X', 'X', 'X'}, {'X', '*', 'O', 'O', 'O', 'X'}, {'X', 'O', 'O', '#', 'O', 'X'}, {'X', 'X', 'X', 'X', 'X', 'X'}}));
        System.out.println(spf.getFood(new char[][]{{'X', 'X', 'X', 'X', 'X'}, {'X', '*', 'X', 'O', 'X'}, {'X', 'O', 'X', '#', 'X'}, {'X', 'X', 'X', 'X', 'X'}}));
        System.out.println(spf.getFood(new char[][]{{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, {'X', '*', 'O', 'X', 'O', '#', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'X'}, {'X', 'O', 'O', 'O', 'O', '#', 'O', 'X'}, {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}}));

    }
}
