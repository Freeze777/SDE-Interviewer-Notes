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

    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        // Use a Set for efficient O(1) average time complexity checks.
        var toDeleteSet = new HashSet<Integer>();
        Arrays.stream(toDelete).forEach(toDeleteSet::add);

        var forestRoots = delNodes(root, toDeleteSet);

        if (!toDeleteSet.contains(root.val)) forestRoots.add(root);
        return forestRoots;
    }

    public List<TreeNode> delNodes(TreeNode root, Set<Integer> toDeleteSet) {
        if (root == null) return new ArrayList<>();

        var newRootsInThisSubtree = new ArrayList<TreeNode>();
        newRootsInThisSubtree.addAll(delNodes(root.left, toDeleteSet));
        newRootsInThisSubtree.addAll(delNodes(root.right, toDeleteSet));

        if (root.left != null && toDeleteSet.contains(root.left.val)) root.left = null;
        if (root.right != null && toDeleteSet.contains(root.right.val)) root.right = null;

        if (toDeleteSet.contains(root.val)) {
            if (root.left != null) newRootsInThisSubtree.add(root.left);
            if (root.right != null) newRootsInThisSubtree.add(root.right);
        }
        return newRootsInThisSubtree;
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
