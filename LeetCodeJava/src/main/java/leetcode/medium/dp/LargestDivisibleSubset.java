package leetcode.medium.dp;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/largest-divisible-subset/">Largest Divisible Subset</a>
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        var dp = new int[nums.length];
        var prev = new int[nums.length];
        var maxIndex = 0;
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        for (var i = 1; i < nums.length; i++) {
            for (var j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && 1 + dp[j] > dp[i]) {
                    dp[i] = 1 + dp[j];
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[maxIndex]) maxIndex = i;
        }

        var result = new ArrayList<Integer>();
        do {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        } while (maxIndex != -1);

        return result;
    }

    public static void main(String[] args) {
        var lds = new LargestDivisibleSubset();
        System.out.println(lds.largestDivisibleSubset(new int[]{1, 2, 3}));//[1, 2]
        System.out.println(lds.largestDivisibleSubset(new int[]{1, 2, 4, 8}));//[1, 2, 4, 8]
        System.out.println(lds.largestDivisibleSubset(new int[]{4, 8, 10, 240}));//[4, 8, 240]
    }
}
