package leetcode.medium.sort;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/insert-interval/">57. Insert Interval</a>
 */
public class InsertInterval extends MergeIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Interval> _intervals = getIntervals(intervals);
        _intervals.add(new Interval(newInterval[0], newInterval[1]));
        return getFinalResult(mergeIntervals(_intervals));
    }

    public static void main(String[] args) {
        var ii = new InsertInterval();
        // [[1,5],[6,9]]
        System.out.println(Arrays.deepToString(ii.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
        // [[1,2],[3,10],[12,16]]
        System.out.println(Arrays.deepToString(ii.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
        // [[1,5]]
        System.out.println(Arrays.deepToString(ii.insert(new int[][]{{1, 5}}, new int[]{2, 3})));
    }
}
