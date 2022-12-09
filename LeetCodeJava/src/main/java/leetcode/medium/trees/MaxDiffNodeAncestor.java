package leetcode.medium.trees;

/**
 * <a href="https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/">https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/</a>
 */
public class MaxDiffNodeAncestor {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int maxAncestorDiff(TreeNode root, int low, int high) {
        if (root == null) return Integer.MIN_VALUE;
        var newLow = Math.min(low, root.val);
        var newHigh = Math.max(high, root.val);
        var subtreeMax = Math.max(maxAncestorDiff(root.left, newLow, newHigh), maxAncestorDiff(root.right, newLow, newHigh));
        var rootMaxDiff = Math.max(Math.abs(root.val - low), Math.abs(root.val - high));
        return Math.max(rootMaxDiff, subtreeMax);
    }

    public int maxAncestorDiff(TreeNode root) {
        return Math.max(maxAncestorDiff(root.left, root.val, root.val), maxAncestorDiff(root.right, root.val, root.val));
    }

    public static void main(String[] args) {
        var mdna = new MaxDiffNodeAncestor();
        var tree = new TreeNode(8);
        tree.left = new TreeNode(3);
        tree.right = new TreeNode(10);
        tree.left.left = new TreeNode(1);
        tree.left.right = new TreeNode(6);
        tree.left.right.left = new TreeNode(4);
        tree.left.right.right = new TreeNode(7);
        tree.right.right = new TreeNode(14);
        tree.right.right.left = new TreeNode(13);
        System.out.println(mdna.maxAncestorDiff(tree));//7

        tree = new TreeNode(1);
        tree.right = new TreeNode(2);
        tree.right.right = new TreeNode(0);
        tree.right.right.left = new TreeNode(3);
        System.out.println(mdna.maxAncestorDiff(tree));//3
    }
}
