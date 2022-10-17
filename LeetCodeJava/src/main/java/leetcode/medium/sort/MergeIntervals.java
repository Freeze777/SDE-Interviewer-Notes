package leetcode.medium.sort;

import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/merge-intervals/">https://leetcode.com/problems/merge-intervals/</a>
 */
public class MergeIntervals {
    static class Interval extends Point implements Comparable<Interval> {
        Interval(int x, int y) {
            super(x, y);
        }

        @Override
        public int compareTo(Interval other) {
            if (this.x == other.x) return this.y - other.y;
            return this.x - other.x;
        }

        @Override
        public String toString() {
            return "Interval{" + "x=" + x + ", y=" + y + '}';
        }
    }


    public int[][] merge(int[][] _intervals) {
        if (_intervals.length < 2) return _intervals;

        List<Interval> intervals = Arrays.stream(_intervals)
                .map(interval -> new Interval(interval[0], interval[1]))
                .sorted()
                .collect(Collectors.toList());

        Interval previous = intervals.get(0);
        intervals.add(new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE)); // sentinel value
        List<Interval> mergedIntervals = new ArrayList<>();
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (previous.y < current.x) {
                mergedIntervals.add(previous);
                previous = current;
            } else {
                previous.x = Math.min(previous.x, current.x);
                previous.y = Math.max(previous.y, current.y);
            }
        }
        int[][] _mergedIntervals = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            _mergedIntervals[i][0] = mergedIntervals.get(i).x;
            _mergedIntervals[i][1] = mergedIntervals.get(i).y;
        }
        return _mergedIntervals;
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        mi.merge(new int[][]{{15, 18}, {1, 3}, {8, 10}, {2, 6}});
        mi.merge(new int[][]{{9, 18}, {1, 3}, {8, 10}, {2, 6}});
        mi.merge(new int[][]{{9, 18}, {1, 3}, {8, 10}, {2, 8}});
        mi.merge(new int[][]{{9, 18}});
        mi.merge(new int[][]{{9, 18}, {20, 100}});
        mi.merge(new int[][]{{9, 18}, {18, 100}});
    }
}
