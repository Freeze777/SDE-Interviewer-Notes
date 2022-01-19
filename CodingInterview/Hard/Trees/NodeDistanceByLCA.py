"""
Find distance between two nodes in a binary tree.
distance = number of edges in the path between two nodes.
- Each node stores a key, left pointer, right pointer.
- You are given 2 node keys who exists in the tree. No two keys in the tree have same value.

1) Binary Search Tree - algo only
2) Binary Tree - algo + code

class TreeNode:
    def __init__(self, x):
        self.key = x
        self.left = None
        self.right = None

                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
                  \
                   8
"""
"""
https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/

Challenging the interviewee:
- The whole algorithm is centered around LCA : Lowest common ancestor. Best O(N) time, O(N) space.
- Distance between A and B = Height(A) + Height(B) - 2 * Height(LCA)
- Start with BST and then take it to normal BT
- Unit test to break the code
- What should we do if we have large number queries for node distances.
"""

from CodingInterview.Hard.Trees.Model.TreeNodeModel import TreeNode, create_mock_binary_tree
from CodingInterview.Easy.Trees.NodeLevel import node_level


def lowest_common_ancestor(root_node: TreeNode, key1, key2):
    if root_node is None or root_node.val == key1 or root_node.val == key2:
        return root_node

    left_lca = lowest_common_ancestor(root_node.left, key1, key2)
    right_lca = lowest_common_ancestor(root_node.right, key1, key2)

    if left_lca and right_lca:
        return root_node

    return left_lca or right_lca


def node_distance(root_node: TreeNode, key1, key2):
    lca = lowest_common_ancestor(root_node, key1, key2)
    key1_level, key2_level = node_level(lca, key1) - 1, node_level(lca, key2) - 1
    return key1_level + key2_level


if __name__ == '__main__':
    root = create_mock_binary_tree()
    print(node_distance(root, 1, 7))
    print(node_distance(root, 8, 7))
    print(node_distance(root, 1, 8))
    print(node_distance(root, 4, 8))
    print(node_distance(root, 8, 8))
