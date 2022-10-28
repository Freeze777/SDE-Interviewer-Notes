package leetcode.medium.matrix;

import java.awt.Point;
import java.util.*;

/**
 * <a href="https://leetcode.com/problems/image-overlap/">https://leetcode.com/problems/image-overlap/</a>
 */
public class ImageOverlap {

    public List<Point> ones(int[][] img) {
        List<Point> oneSet = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                if (img[i][j] == 1)
                    oneSet.add(new Point(i, j));
            }
        }
        return oneSet;
    }

    public int largestOverlap(int[][] source, int[][] target) {
        List<Point> sourceOnes = ones(source);
        List<Point> targetOnes = ones(target);
        HashMap<Point, Integer> vectorCounts = new HashMap<>();
        for (Point a : sourceOnes) {
            for (Point b : targetOnes) {
                Point distanceVector = new Point(a.x - b.x, a.y - b.y); // gives the direction and magnitude
                if (!vectorCounts.containsKey(distanceVector)) {
                    vectorCounts.put(distanceVector, 0);
                }
                vectorCounts.compute(distanceVector, (k, v) -> v + 1);
            }
        }
        // the distance vector with the highest count is the answer. We should translate in its direction by its magnitude
        return vectorCounts.values().stream().reduce(0, Integer::max);
    }


    public static void main(String[] args) {
        ImageOverlap io = new ImageOverlap();
        int[][] A = new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] B = new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};
        System.out.println(io.largestOverlap(A, B));
    }
}
