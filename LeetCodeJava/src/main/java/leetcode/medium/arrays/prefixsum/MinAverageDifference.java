package leetcode.medium.arrays.prefixsum;

/**
 * <a href="https://leetcode.com/problems/minimum-average-difference-between-two-arrays/">https://leetcode.com/problems/minimum-average-difference-between-two-arrays/</a>
 */
public class MinAverageDifference {

    public int minimumAverageDifference(int[] nums) {
        long[] prefixSum = new long[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) prefixSum[i] = prefixSum[i - 1] + nums[i];
        long totalSum = prefixSum[nums.length - 1];
        long minAvgDiff = totalSum / nums.length;
        int minIndex = nums.length - 1;
        for (int i = 0; i < nums.length - 1; i++) {
            long avgDiff = Math.abs((prefixSum[i] / (i + 1)) - ((totalSum - prefixSum[i]) / (nums.length - i - 1)));
            if (avgDiff < minAvgDiff) {
                minAvgDiff = avgDiff;
                minIndex = i;
            } else if (avgDiff == minAvgDiff) minIndex = Math.min(minIndex, i);
        }
        return minIndex;
    }

    public static void main(String[] args) {
        var mvd = new MinAverageDifference();
        System.out.println(mvd.minimumAverageDifference(new int[]{0})); // 0
        System.out.println(mvd.minimumAverageDifference(new int[]{10})); //0
        System.out.println(mvd.minimumAverageDifference(new int[]{1, 2, 3, 4})); // 0
        System.out.println(mvd.minimumAverageDifference(new int[]{3, 7})); // 0
        System.out.println(mvd.minimumAverageDifference(new int[]{6, 2, 6, 5, 1, 2, 0, 7, 0, 1})); // 5
        System.out.println(mvd.minimumAverageDifference(new int[]{2, 5, 3, 9, 5, 3})); // 3
    }
}
