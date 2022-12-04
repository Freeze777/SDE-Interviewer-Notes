package leetcode.hard.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/reducing-dishes/">https://leetcode.com/problems/reducing-dishes/</a>
 */
public class ReducingDishes {
    private int maxSatisfaction(int[] satisfactions, int current, int time, int[][] cache) {
        if (current >= satisfactions.length || time > satisfactions.length) return 0;
        if (cache[current][time] != 0) return cache[current][time];
        int withCurrent = time * satisfactions[current] + maxSatisfaction(satisfactions, current + 1, time + 1, cache);
        int withoutCurrent = maxSatisfaction(satisfactions, current + 1, time, cache);
        return (cache[current][time] = Math.max(withCurrent, withoutCurrent));
    }

    public int maxSatisfaction(int[] satisfactions) {
        Arrays.sort(satisfactions);
        int[][] cache = new int[satisfactions.length][satisfactions.length + 1];
        return maxSatisfaction(satisfactions, 0, 1, cache);
    }

    public static void main(String[] args) {
        var rd = new ReducingDishes();
        System.out.println(rd.maxSatisfaction(new int[]{-1, -8, 0, 5, -9})); // 14
        System.out.println(rd.maxSatisfaction(new int[]{4, 3, 2})); // 20
        System.out.println(rd.maxSatisfaction(new int[]{-1, -4, -5})); // 0
        System.out.println(rd.maxSatisfaction(new int[]{-2, 5, -1, 0, 3, -3})); // 35
    }
}
