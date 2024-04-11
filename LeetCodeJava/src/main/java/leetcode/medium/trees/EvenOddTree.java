package leetcode.medium.trees;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/even-odd-tree/">1609. Even Odd Tree</a>
 */
public class EvenOddTree {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }


    public boolean isEvenOddTree(TreeNode root) {
        var bfsQ = new LinkedList<TreeNode>();
        var level = 0;
        var previous = Integer.MIN_VALUE;
        bfsQ.add(root);
        bfsQ.add(null);
        while (!bfsQ.isEmpty()) {
            var node = bfsQ.poll();
            if (node == null) {
                if (bfsQ.isEmpty()) break;
                bfsQ.add(null);
                level++;
                previous = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                continue;
            }
            if (level % 2 == 0) {//even level
                if (node.val % 2 == 0) return false;//even value
                if (node.val <= previous) return false;//not strictly increasing
            } else {//odd level
                if (node.val % 2 == 1) return false;//odd value
                if (node.val >= previous) return false;//not strictly decreasing
            }

            previous = node.val;
            if (node.left != null) bfsQ.add(node.left);
            if (node.right != null) bfsQ.add(node.right);
        }
        return true;
    }

    public static void main(String[] args) {
        var eot = new EvenOddTree();
        var root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(eot.isEvenOddTree(root));

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        System.out.println(eot.isEvenOddTree(root));
    }

}
