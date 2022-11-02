package leetcode.medium.binarysearch;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/find-smallest-common-element-in-all-rows/">https://leetcode.com/problems/find-smallest-common-element-in-all-rows/</a>
 */
public class SmallestCommonElement {
    public int smallestCommonElement(int[][] mat) {
        int[] reference = mat[0];
        for (int candidate : reference) {
            boolean matchedAll = true;
            for (int i = 1; i < mat.length; i++) {
                int result = Arrays.binarySearch(mat[i], candidate);
                matchedAll &= (result >= 0);
            }
            if (matchedAll) return candidate;
        }
        return -1;
    }

    public static void main(String[] args) {
        SmallestCommonElement sce = new SmallestCommonElement();
        System.out.println(sce.smallestCommonElement(new int[][]{{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {3, 5, 7, 9, 11}, {1, 3, 5, 7, 9}}));//5
        System.out.println(sce.smallestCommonElement(new int[][]{{1, 2, 3}, {2, 3, 4}, {2, 3, 5}}));//2
    }
}
