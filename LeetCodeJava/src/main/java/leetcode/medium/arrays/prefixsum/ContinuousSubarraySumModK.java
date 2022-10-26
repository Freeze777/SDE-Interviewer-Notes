package leetcode.medium.arrays.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/continuous-subarray-sum/">https://leetcode.com/problems/continuous-subarray-sum/</a>
 */
public class ContinuousSubarraySumModK {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length <= 1) return false;
        int movingSum = 0;
        Map<Integer, Integer> prevSums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int newSum = (movingSum + nums[i]) % k;
            if (i >= 1 && newSum == 0) return true; // A[0] to A[i]
            if (prevSums.containsKey(newSum)) { // A[j] to A[i]
                int prevIndex = prevSums.get(newSum);
                if (i - prevIndex >= 2) return true;
            } else {
                prevSums.put(newSum, i);
            }
            movingSum = newSum;
        }
        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySumModK cssm = new ContinuousSubarraySumModK();
        System.out.println(cssm.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); //true
        System.out.println(cssm.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)); //true
        System.out.println(cssm.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13)); //false
        System.out.println(cssm.checkSubarraySum(new int[]{23, 2, 4, 6, 6}, 7)); //true
        System.out.println(cssm.checkSubarraySum(new int[]{0}, 1)); //false
        System.out.println(cssm.checkSubarraySum(new int[]{0, 7}, 5)); //false
        System.out.println(cssm.checkSubarraySum(new int[]{0, 7, 0}, 5)); //false
    }
}
