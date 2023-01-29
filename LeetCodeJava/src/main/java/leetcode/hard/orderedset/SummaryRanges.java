package leetcode.hard.orderedset;

import java.awt.*;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 352. Data Stream as Disjoint Intervals
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 */
public class SummaryRanges {
    private static class Interval extends Point implements Comparable<Interval> {
        Interval(int x, int y) {
            super(x, y);
        }

        @Override
        public boolean equals(Object other) {
            return this.x <= ((Interval) other).x && this.y >= ((Interval) other).y;//full overlap
        }

        @Override
        public int compareTo(Interval other) {
            if (other.equals(this)) return 0;
            if (this.x == other.x) return this.y - other.y;
            return this.x - other.x;
        }

        @Override
        public String toString() {
            return "Interval{" + "x=" + x + ", y=" + y + '}';
        }
    }

    private final TreeSet<Interval> _intervals = new TreeSet<>();

    public SummaryRanges() {
    }

    public void addNum(int num) {
        insert(new Interval(num, num));
    }

    public int[][] getIntervals() {
        int[][] intervals = new int[_intervals.size()][2];
        int i = 0;
        for (Interval interval : _intervals) {
            intervals[i][0] = interval.x;
            intervals[i][1] = interval.y;
            i++;
        }
        return intervals;
    }

    private void insert(Interval interval) {
        var left = _intervals.floor(interval);
        var right = _intervals.ceiling(interval);

        if (left == null && right == null) {
            _intervals.add(interval);
        } else if (left == null) {
            if (interval.y + 1 == right.x) {
                mergeToRight(interval, right);
            } else {
                _intervals.add(interval);
            }
        } else if (right == null) {
            if (left.y == interval.x - 1) {
                mergeToLeft(interval, left);
            } else {
                _intervals.add(interval);
            }
        } else {
            if (left.y == interval.x - 1 && right.x == interval.y + 1) {
                mergeLeftToRight(left, right);
            } else if (left.y == interval.x - 1) {
                mergeToLeft(interval, left);
            } else if (interval.y + 1 == right.x) {
                mergeToRight(interval, right);
            } else {
                _intervals.add(interval);
            }
        }
    }

    private void mergeLeftToRight(Interval left, Interval right) {
        _intervals.remove(left);
        _intervals.remove(right);
        _intervals.add(new Interval(left.x, right.y));
    }

    private void mergeToLeft(Interval interval, Interval left) {
        _intervals.remove(left);
        _intervals.add(new Interval(left.x, interval.y));
    }

    private void mergeToRight(Interval interval, Interval right) {
        _intervals.remove(right);
        _intervals.add(new Interval(interval.x, right.y));
    }

    public static void main(String[] args) {
        var sr = new SummaryRanges();
        for (int i = 1; i <= 5; i++) sr.addNum(i);
        for (int i = 7; i <= 9; i++) sr.addNum(i);
        System.out.println(Arrays.deepToString(sr.getIntervals()));//[[1,5],[7,9]]
        sr.addNum(6);
        System.out.println(Arrays.deepToString(sr.getIntervals()));//[[1,9]]
        sr.addNum(5);
        System.out.println(Arrays.deepToString(sr.getIntervals()));//[[1,9]]
        sr.addNum(20);
        sr.addNum(21);
        System.out.println(Arrays.deepToString(sr.getIntervals()));//[[1,9],[20,21]]
        sr.addNum(19);
        System.out.println(Arrays.deepToString(sr.getIntervals()));//[[1,9],[19,21]]
    }
}
