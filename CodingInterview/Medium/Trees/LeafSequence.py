from CodingInterview.Hard.Trees.Model.TreeNodeModel import TreeNode, create_mock_binary_tree

"""
Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
For example, in the given tree above, the leaf value sequence is (4, 5, 8, 7).

                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
                  \
                   8
Two binary trees are considered leaf-similar if their leaf value sequence is the same.
Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
"""

"""
https://leetcode.com/problems/leaf-similar-trees/
"""


def get_leaf_sequence(root: TreeNode):
    if root is None:
        return []
    if root.left is None and root.right is None:
        return [root.key]
    return get_leaf_sequence(root.left) + get_leaf_sequence(root.right)


def leaf_similar(root1: TreeNode, root2: TreeNode) -> bool:
    return get_leaf_sequence(root1) == get_leaf_sequence(root2)


if __name__ == '__main__':
    node = create_mock_binary_tree()
    print(leaf_similar(node, node.right))
    print(leaf_similar(node, node))
    print(leaf_similar(node, node.left))
