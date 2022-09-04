"""
Path in a binary tree is a sequence of nodes where pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node values in the path.
Given the root of a binary tree, return the maximum path sum of any path.

Example 1:
                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
                  \
                   8
Ans : 25

Example 2:
                10
             /     \
            2       10
          /   \       \
        20     1      -25
                     /   \
                    3     4
Ans : 42

"""

import sys

from ProblemSolving.Hard.Trees.Model.TreeNodeModel import create_mock_binary_tree, TreeNode, \
    create_mock_binary_tree_with_negative


def max_path_sum(root: TreeNode) -> int:
    return max(max_path_sum_helper(root))


def max_path_sum_helper(root: TreeNode) -> (int, int):
    if root is None:
        return 0, -sys.maxsize
    if root.is_leaf():
        return root.val, root.val

    best_left_root_path, overall_left_max_path = max_path_sum_helper(root.left)
    best_right_root_path, overall_right_max_path = max_path_sum_helper(root.right)
    max_path_with_current_root = max(root.val + best_right_root_path + best_left_root_path, root.val,
                                     best_left_root_path + root.val, best_right_root_path + root.val)
    overall_max_path = max(overall_right_max_path, overall_left_max_path, max_path_with_current_root)
    best_current_root_path = max(best_left_root_path + root.val, best_right_root_path + root.val, root.val)

    return best_current_root_path, overall_max_path


if __name__ == '__main__':
    root_node = create_mock_binary_tree()
    print(max_path_sum(root_node))

    root_node = create_mock_binary_tree_with_negative()
    print(max_path_sum(root_node))

    print(max_path_sum(TreeNode(-3)))

    root_node = TreeNode(-2)
    root_node.left = TreeNode(-1)
    print(max_path_sum(root_node))

    root_node = TreeNode(1)
    root_node.left = TreeNode(2)
    print(max_path_sum(root_node))

    root_node = TreeNode(1)
    root_node.left = TreeNode(-2)
    root_node.right = TreeNode(3)
    print(max_path_sum(root_node))

    root_node = TreeNode(-1)
    root_node.right = TreeNode(9)
    root_node.right.left = TreeNode(-6)
    root_node.right.right = TreeNode(3)
    root_node.right.right.right = TreeNode(-2)
    print(max_path_sum(root_node))
