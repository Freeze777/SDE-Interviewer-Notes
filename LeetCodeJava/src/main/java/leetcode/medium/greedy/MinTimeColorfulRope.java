package leetcode.medium.greedy;

import java.awt.*;

/**
 * ht<a href="tps://leetcode.com/problems/minimum-time-to-make-rope-colorful/
 * ">https://leetcode.com/problems/minimum-time-to-make-rope-colorful/</a>
 */
public class MinTimeColorfulRope {

    public Point burstInterval(int burstStart, String colors) {
        int burstEnd = burstStart;
        while (burstEnd < colors.length() - 1 && colors.charAt(burstEnd) == colors.charAt(burstEnd + 1)) {
            burstEnd++;
        }
        return new Point(burstStart, burstEnd);
    }

    public int burstIntervalCost(Point interval, int[] neededTime) {
        int burstCostMax = 0, burstCostSum = 0;
        for (int i = interval.x; i <= interval.y; i++) {
            burstCostSum += neededTime[i];
            burstCostMax = Math.max(burstCostMax, neededTime[i]);
        }
        return burstCostSum - burstCostMax;
    }

    public int minCost(String colors, int[] neededTime) {
        int totalCost = 0;
        for (int start = 0; start < colors.length(); ) {
            Point interval = burstInterval(start, colors);
            totalCost += burstIntervalCost(interval, neededTime);
            start = interval.y + 1;
        }
        return totalCost;
    }

    public static void main(String[] args) {
        MinTimeColorfulRope minRope = new MinTimeColorfulRope();
        System.out.println(minRope.minCost("abaac", new int[]{1, 2, 3, 4, 5})); //3
        System.out.println(minRope.minCost("abc", new int[]{1, 2, 3})); //0
        System.out.println(minRope.minCost("aabaa", new int[]{1, 2, 3, 4, 1})); //2
        System.out.println(minRope.minCost("aaabbbccc", new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3})); //9
    }

}
