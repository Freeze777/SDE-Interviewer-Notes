from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


"""
                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
                  \
                   8

"""


def create_mock_binary_tree():
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.left.left = TreeNode(4)
    root.left.right = TreeNode(5)
    root.right = TreeNode(3)
    root.right.left = TreeNode(6)
    root.right.right = TreeNode(7)
    root.right.left.right = TreeNode(8)
    return root


def get_tree_nodes(root: TreeNode) -> List[TreeNode]:
    if root is None:
        return []
    return [root.val] + get_tree_nodes(root.left) + get_tree_nodes(root.right)
