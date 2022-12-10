package leetcode.medium.trees;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/">https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/</a>
 */
public class MaxProductSplitBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private static final long MOD = 1000000007;

    private long maxProduct(TreeNode root, List<Long> subtreeSums) {
        if (root == null) return 0L;
        long sum = root.val + maxProduct(root.left, subtreeSums) + maxProduct(root.right, subtreeSums);
        subtreeSums.add(sum);
        return sum;
    }

    public int maxProduct(TreeNode root) {
        var subtreeSums = new LinkedList<Long>();
        long totalSum = maxProduct(root, subtreeSums);
        long maxProduct = Long.MIN_VALUE;
        for (Long subtreeSum : subtreeSums) {
            long otherTreeSum = totalSum - subtreeSum;
            long product = otherTreeSum * subtreeSum;
            maxProduct = Math.max(product, maxProduct);
        }
        return (int) (maxProduct % MOD);
    }

    public static void main(String[] args) {
        MaxProductSplitBinaryTree mp = new MaxProductSplitBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(mp.maxProduct(root));
    }
}
