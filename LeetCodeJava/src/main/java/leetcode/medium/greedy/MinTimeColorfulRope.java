package leetcode.medium.greedy;

import java.awt.*;

/**
 * <a href="tps://leetcode.com/problems/minimum-time-to-make-rope-colorful/
 * ">https://leetcode.com/problems/minimum-time-to-make-rope-colorful/</a>
 */
public class MinTimeColorfulRope {

    public Point findSameColorInterval(int burstStart, String colors) {
        int burstEnd = burstStart;
        while (burstEnd < colors.length() - 1 && colors.charAt(burstEnd) == colors.charAt(burstEnd + 1)) {
            burstEnd++;
        }
        return new Point(burstStart, burstEnd);
    }

    public int minTimeToRemoveSameColor(Point interval, int[] neededTime) {
        int burstTimeMax = 0, burstTotalTime = 0;
        for (int i = interval.x; i <= interval.y; i++) {
            burstTotalTime += neededTime[i];
            burstTimeMax = Math.max(burstTimeMax, neededTime[i]);
        }
        return burstTotalTime - burstTimeMax; // remove all balloon except the max
    }

    public int minCost(String colors, int[] neededTime) {
        int totalTime = 0;
        for (int start = 0; start < colors.length(); ) {
            Point interval = findSameColorInterval(start, colors);
            totalTime += minTimeToRemoveSameColor(interval, neededTime);
            start = interval.y + 1;
        }
        return totalTime;
    }

    public static void main(String[] args) {
        MinTimeColorfulRope minRope = new MinTimeColorfulRope();
        System.out.println(minRope.minCost("abaac", new int[]{1, 2, 3, 4, 5})); //3
        System.out.println(minRope.minCost("abc", new int[]{1, 2, 3})); //0
        System.out.println(minRope.minCost("aabaa", new int[]{1, 2, 3, 4, 1})); //2
        System.out.println(minRope.minCost("aaabbbccc", new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3})); //9
    }

}
