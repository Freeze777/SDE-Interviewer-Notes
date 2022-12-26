package leetcode.medium.dp;

/**
 * 55. Jump Game
 * <a href="https://leetcode.com/problems/jump-game/">https://leetcode.com/problems/jump-game/</a>
 */
public class JumpGame {

    public boolean jumpHelper(int[] nums, int current, int last, Boolean[] dp) {
        if (current >= last) return true;
        if (dp[current] != null) return dp[current];
        var ans = false;
        for (int i = 1; i <= nums[current]; i++) {
            ans = jumpHelper(nums, current + i, last, dp);
            if (ans) break;
        }
        return dp[current] = ans;
    }

    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        Boolean[] dp = new Boolean[nums.length];
        return jumpHelper(nums, 0, nums.length - 1, dp);
    }

    public static void main(String[] args) {
        var jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(new int[]{2, 3, 1, 1, 4}));//true
        System.out.println(jumpGame.canJump(new int[]{3, 2, 1, 0, 4}));//false
        System.out.println(jumpGame.canJump(new int[]{2, 0, 0}));//true
        System.out.println(jumpGame.canJump(new int[]{0}));//true
        System.out.println(jumpGame.canJump(new int[]{1, 1, 1, 0}));//true
        System.out.println(jumpGame.canJump(new int[]{1, 2, 3}));//true
    }
}
