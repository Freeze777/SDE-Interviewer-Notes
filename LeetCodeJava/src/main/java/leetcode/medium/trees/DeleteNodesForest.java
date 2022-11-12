package leetcode.medium.trees;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/delete-nodes-and-return-forest/description/">https://leetcode.com/problems/delete-nodes-and-return-forest/description/</a>
 */
public class DeleteNodesForest {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
        }
    }

    private List<TreeNode> delNodes(TreeNode root, Set<Integer> toDelete) {
        if (root == null) return new ArrayList<>();
        List<TreeNode> forest = new ArrayList<>();
        forest.addAll(delNodes(root.left, toDelete));
        forest.addAll(delNodes(root.right, toDelete));

        if (root.left != null && toDelete.contains(root.left.val)) root.left = null;
        if (root.right != null && toDelete.contains(root.right.val)) root.right = null;

        if (toDelete.contains(root.val)) {
            if (root.left != null) forest.add(root.left);
            if (root.right != null) forest.add(root.right);
        }

        return forest;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        Set<Integer> set = new HashSet<>();
        for (Integer i : toDelete) set.add(i);
        List<TreeNode> forest = delNodes(root, set);
        if (!set.contains(root.val)) forest.add(root);
        return forest;
    }

    public static void main(String[] args) {
        DeleteNodesForest dnf = new DeleteNodesForest();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(dnf.delNodes(root, new int[]{3, 5}));
    }
}
