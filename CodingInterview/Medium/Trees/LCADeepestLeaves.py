"""
Find the root of the smallest subtree which contains all the deepest leaf nodes.

                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
      /  \        \     \
    11   10        8    9
"""

"""
https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
"""

from CodingInterview.Hard.Trees.Model.TreeNodeModel import TreeNode, create_mock_binary_tree, get_tree_nodes


def lca_deepest_leaves_helper(root: TreeNode, level: int) -> (int, TreeNode):
    if root is None:
        return -1, None
    if root.left is None and root.right is None:
        return level, root
    max_left_level, left_lca = lca_deepest_leaves_helper(root.left, level + 1)
    max_right_level, right_lca = lca_deepest_leaves_helper(root.right, level + 1)
    if max_left_level == max_right_level:
        return max_left_level, root
    max_level = max(max_right_level, max_left_level)
    max_lca = left_lca if max_level == max_left_level else right_lca
    return max_level, max_lca


def lca_deepest_leaves(root: TreeNode) -> TreeNode:
    max_level, lca = lca_deepest_leaves_helper(root, 1)
    return lca


if __name__ == '__main__':
    node = create_mock_binary_tree()
    node.right.right.right = TreeNode(9)
    node.right.left.right = TreeNode(8)
    node.left.left.right = TreeNode(10)
    node.left.left.left = TreeNode(11)
    print(lca_deepest_leaves(node).val)
    print(lca_deepest_leaves(node.left).val)
    print(lca_deepest_leaves(node.right).val)
