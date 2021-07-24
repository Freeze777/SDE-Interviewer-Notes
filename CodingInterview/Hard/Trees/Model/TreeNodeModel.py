from typing import List


class TreeNode:
    def __init__(self, x):
        self.val: int = x
        self.left: TreeNode = None
        self.right: TreeNode = None
        self.vertical_level: int = 0
        self.depth: int = 0

    def __str__(self) -> str:
        return f'[val:{self.val}, vertical_level:{self.vertical_level}, depth:{self.depth}]'

    def is_leaf(self) -> bool:
        return self.left is None and self.right is None


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


"""
                10
             /     \
            2       10
          /   \       \
        20     1      -25
                     /   \
                    3     4

"""


def create_mock_binary_tree_with_negative():
    root = TreeNode(10)
    root.left = TreeNode(2)
    root.left.left = TreeNode(20)
    root.left.right = TreeNode(1)
    root.right = TreeNode(10)
    root.right.right = TreeNode(-25)
    root.right.right.left = TreeNode(3)
    root.right.right.right = TreeNode(4)
    return root


def get_tree_nodes(root: TreeNode) -> List[str]:
    if root is None:
        return []
    return [root.__str__()] + get_tree_nodes(root.left) + get_tree_nodes(root.right)


def pretty_print_tree(node: TreeNode, level=0):
    if node is not None:
        pretty_print_tree(node.right, level + 1)
        print(' ' * 4 * level + '->', node.val)
        pretty_print_tree(node.left, level + 1)
