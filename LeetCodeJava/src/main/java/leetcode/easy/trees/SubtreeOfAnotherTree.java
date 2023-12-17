package leetcode.easy.trees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/subtree-of-another-tree/"> Subtree of Another Tree </a>
 */
public class SubtreeOfAnotherTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        try {
            var mainTreeHashes = new HashSet<String>();
            treeHash(root, mainTreeHashes);
            var subtreeHash = treeHash(subRoot, new HashSet<>());
            return mainTreeHashes.contains(subtreeHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String getHash(String input) throws NoSuchAlgorithmException {
        var md5Hasher = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();
        for (byte b : md5Hasher.digest(input.getBytes())) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    private String treeHash(TreeNode root, Set<String> hashes) throws NoSuchAlgorithmException {
        if (root == null) return "";
        var left = treeHash(root.left, hashes);
        var right = treeHash(root.right, hashes);
        var subtreeHash = getHash(left + root.val + right);
        hashes.add(subtreeHash);
        return subtreeHash;
    }

    public static void main(String[] args) {
        var sat = new SubtreeOfAnotherTree();
        var root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);

        var subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        System.out.println(sat.isSubtree(root, subRoot));//true
        System.out.println(sat.isSubtree(root, new TreeNode(1)));//true
        System.out.println(sat.isSubtree(root, new TreeNode(10)));//false
    }
}
