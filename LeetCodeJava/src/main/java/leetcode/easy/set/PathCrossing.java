package leetcode.easy.set;

import java.util.HashSet;

/**
 * <a href="https://leetcode.com/problems/path-crossing">Path Crossing</a>
 */
public class PathCrossing {
    public boolean isPathCrossing(String path) {
        record Point(int x, int y) {
        }
        var visited = new HashSet<Point>();
        var start = new Point(0, 0);
        visited.add(start);
        for (var dir : path.toCharArray()) {
            switch (dir) {
                case 'N' -> start = new Point(start.x, start.y + 1);
                case 'S' -> start = new Point(start.x, start.y - 1);
                case 'E' -> start = new Point(start.x + 1, start.y);
                case 'W' -> start = new Point(start.x - 1, start.y);
            }
            if (visited.contains(start)) return true;
            visited.add(start);
        }
        return false;
    }
}
