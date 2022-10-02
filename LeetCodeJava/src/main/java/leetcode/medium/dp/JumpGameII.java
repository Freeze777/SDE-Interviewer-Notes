package leetcode.medium.dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/jump-game-ii/">https://leetcode.com/problems/jump-game-ii/</a>
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if (nums.length == 1)
            return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return jumpHelper(nums, 0, nums.length - 1, dp);
    }

    public int jumpHelper(int[] nums, int current, int last, int[] dp) {
        if (current >= last)
            return 0;
        if (dp[current] != -1)
            return dp[current];

        int ans = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= nums[current]; i++) {
            ans = Math.min(ans, 1 + jumpHelper(nums, current + i, last, dp));
        }
        dp[current] = ans;
        return dp[current];
    }

    public static void main(String[] args) {
        JumpGameII jumpGameII = new JumpGameII();
        System.out.println(jumpGameII.jump(new int[]{0}));
        System.out.println(jumpGameII.jump(new int[]{1, 2}));
        System.out.println(jumpGameII.jump(new int[]{2, 3, 0, 1, 4}));
        System.out.println(jumpGameII.jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(jumpGameII.jump(new int[]{1, 1, 1, 1, 1}));
        System.out.println(jumpGameII.jump(new int[]{1, 0, 0, 1}));
    }
}
