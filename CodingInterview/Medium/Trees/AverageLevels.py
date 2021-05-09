"""
Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
Answers within 10-5 of the actual answer will be accepted.

                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
                  \
                   8
Ans : [1, 2.5, 5.5, 8]
"""

"""
https://leetcode.com/problems/average-of-levels-in-binary-tree/
Similar : https://leetcode.com/problems/find-largest-value-in-each-tree-row/
"""
from CodingInterview.Hard.Trees.Model.TreeNodeModel import create_mock_binary_tree, TreeNode
from statistics import mean


def levels_helper(root: TreeNode, avg, level):
    if root is None:
        return
    if level not in avg:
        avg[level] = []
    avg[level].append(root.val)
    levels_helper(root.left, avg, level + 1)
    levels_helper(root.right, avg, level + 1)


def max_levels(root: TreeNode):
    avg = {}
    levels_helper(root, avg, 0)
    return [max(v) for k, v in sorted(avg.items())]


def average_levels(root: TreeNode):
    avg = {}
    levels_helper(root, avg, 0)
    return [mean(v) for k, v in sorted(avg.items())]


if __name__ == '__main__':
    node = create_mock_binary_tree()
    print(average_levels(node))
    print(max_levels(node))
