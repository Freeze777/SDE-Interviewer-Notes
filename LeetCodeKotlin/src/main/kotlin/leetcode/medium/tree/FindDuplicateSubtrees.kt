package leetcode.medium.tree

import leetcode.medium.tree.FindDuplicateSubtrees.*
import java.math.BigInteger
import java.security.MessageDigest

/**
 * 652. Find Duplicate Subtrees
 * https://leetcode.com/problems/find-duplicate-subtrees/
 */
class FindDuplicateSubtrees {
    data class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private fun md5Hash(s: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(s.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun merkleHash(root: TreeNode?, subtreeHashes: MutableMap<String, MutableList<TreeNode>>): String {
        if (root == null) return "$"
        val subtreeHash = md5Hash(merkleHash(root.left, subtreeHashes) +
                    merkleHash(root.right, subtreeHashes) +
                    md5Hash(root.`val`.toString()))
        subtreeHashes.getOrPut(subtreeHash) { mutableListOf() }.add(root)
        return subtreeHash
    }

    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        val subtreeHashes = mutableMapOf<String, MutableList<TreeNode>>()
        merkleHash(root, subtreeHashes)
        return subtreeHashes.filter { it.value.size > 1 }.values.map { it.first() }
    }
}

fun main() {
    val p = FindDuplicateSubtrees()
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left!!.left = TreeNode(4)
    root.right!!.left = TreeNode(2)
    root.right!!.left!!.left = TreeNode(4)
    root.right!!.right = TreeNode(4)
    println(p.findDuplicateSubtrees(root))// [2, 4], [4]
}
