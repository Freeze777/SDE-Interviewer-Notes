
"""

Find the level of a node from the root in a binary tree.

            3       -- level 1
          /   \
        2       5   -- level 2    
      /  \
    1     4         -- level 3

"""
from CodingInterview.Hard.Trees.Model.TreeNodeModel import TreeNode


def level_helper(node: TreeNode, key, level):
    if node is None:
        return -1  # sentinel value

    if node.key == key:
        return level

    left_level = level_helper(node.left, key, level + 1)

    return left_level if left_level != -1 else level_helper(node.right, key, level + 1)


def node_level(root_node: TreeNode, key):
    return level_helper(root_node, key, 1)


if __name__ == '__main__':
    root = TreeNode(3)
    root.left = TreeNode(2)
    root.right = TreeNode(5)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(4)
    for x in range(1, 6):
        print(x, '-->', node_level(root, x))
