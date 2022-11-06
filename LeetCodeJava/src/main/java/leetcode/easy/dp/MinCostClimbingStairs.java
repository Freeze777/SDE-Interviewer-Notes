package leetcode.easy.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/min-cost-climbing-stairs/description/">https://leetcode.com/problems/min-cost-climbing-stairs/description/</a>
 */
public class MinCostClimbingStairs {
    public final int SENTINEL = Integer.MAX_VALUE / 2;

    public int minCost(int[] cost, int i, int[] cache) {
        if (i >= cost.length) return 0;
        if (cache[i] != SENTINEL) return cache[i];
        int minCost = cost[i] + Math.min(minCost(cost, i + 1, cache), minCost(cost, i + 2, cache));
        return (cache[i] = Math.min(minCost, cache[i]));
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] cache = new int[cost.length];
        Arrays.fill(cache, SENTINEL);
        minCost(cost, 0, cache);
        return Math.min(cache[0], cache[1]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs mccs = new MinCostClimbingStairs();
        System.out.println(mccs.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(mccs.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(mccs.minCostClimbingStairs(new int[]{10, 15, 20, 9, 1, 22, 8}));
        System.out.println(mccs.minCostClimbingStairs(new int[]{15, 10}));
        System.out.println(mccs.minCostClimbingStairs(new int[]{10, 15}));
    }
}
