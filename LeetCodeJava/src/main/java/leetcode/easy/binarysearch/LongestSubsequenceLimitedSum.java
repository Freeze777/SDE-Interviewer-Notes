package leetcode.easy.binarysearch;

import java.util.*;

/**
 * 2389. Longest Subsequence Limited Sum
 * <a href="https://leetcode.com/problems/longest-subsequence-limited-sum/">https://leetcode.com/problems/longest-subsequence-limited-sum/</a>
 */
public class LongestSubsequenceLimitedSum {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) nums[i] += nums[i - 1];
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) answer[q] = Math.abs(Arrays.binarySearch(nums, queries[q]) + 1);
        return answer;
    }

    public static void main(String[] args) {
        var lsls = new LongestSubsequenceLimitedSum();
        //[2, 1, 3, 4]
        System.out.println(Arrays.toString(lsls.answerQueries(new int[]{1, 2, 3, 4, 5}, new int[]{3, 1, 7, 11})));
        //[1, 0, 1, 2]
        System.out.println(Arrays.toString(lsls.answerQueries(new int[]{3, 5, 10, 20, 21}, new int[]{3, 1, 7, 11})));
    }
}
