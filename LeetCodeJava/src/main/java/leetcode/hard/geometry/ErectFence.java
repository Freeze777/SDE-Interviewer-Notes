package leetcode.hard.geometry;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * <a href="https://leetcode.com/problems/erect-the-fence/description/">https://leetcode.com/problems/erect-the-fence/description/</a>
 */
public class ErectFence {

    private int distance(Point a, Point b) {
        return (a.y - b.y) * (a.y - b.y) + (a.x - b.x) * (a.x - b.x);
    }

    private int crossProduct(Point a, Point b, Point c) {
        int y1 = a.y - b.y;
        int y2 = a.y - c.y;
        int x1 = a.x - b.x;
        int x2 = a.x - c.x;
        return y2 * x1 - y1 * x2;
    }

    private Set<Point> jarvisMarchConvexHull(int[][] _points) {
        List<Point> points = Arrays.stream(_points)
                .map(tree -> new Point(tree[0], tree[1]))
                .sorted(Comparator.comparingInt(p -> p.x))
                .toList();
        Set<Point> convexHull = new HashSet<>();
        List<Point> collinear = new ArrayList<>();
        Point start, next, current;
        current = start = next = points.get(0);
        convexHull.add(start);
        while (true) {
            for (Point point : points) {
                if (current.equals(point)) continue;
                int crossProduct = crossProduct(current, next, point);
                if (crossProduct == 0) {
                    if (distance(current, next) < distance(current, point)) {
                        collinear.add(next);
                        next = point;
                    } else {
                        collinear.add(point);
                    }
                } else if (crossProduct > 0) {
                    next = point;
                    collinear.clear();
                }
            }
            convexHull.addAll(collinear);
            if (next == start || convexHull.size() == points.size()) break;
            current = next;
            convexHull.add(current);
        }
        return convexHull;
    }

    public int[][] outerTrees(int[][] trees) {
        if (trees.length <= 2) return trees;
        List<Point> convexHull = jarvisMarchConvexHull(trees).stream().toList();
        int[][] fence = new int[convexHull.size()][2];
        for (int i = 0; i < fence.length; i++) {
            fence[i][0] = convexHull.get(i).x;
            fence[i][1] = convexHull.get(i).y;
        }
        return fence;
    }

    public static void main(String[] args) {
        ErectFence erectFence = new ErectFence();
        System.out.println(Arrays.deepToString(erectFence.outerTrees(new int[][]{{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}})));
        System.out.println(Arrays.deepToString(erectFence.outerTrees(new int[][]{{1, 2}, {2, 2}, {4, 2}})));
        System.out.println(Arrays.deepToString(erectFence.outerTrees(new int[][]{{2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}})));
    }
}
