import sys
from collections import deque
from typing import List

from CodingInterview.Hard.Trees.Model.TreeNodeModel import TreeNode, create_mock_binary_tree

"""
                1
             /     \
            2       3
         /    \   /   \
        4     5  6     7
                  \
                   8

"""


def top_view(root: TreeNode) -> List[int]:
    bfs_queue, top_view_nodes = deque(), deque()
    max_vertical_level, min_vertical_level = (-sys.maxsize, sys.maxsize)

    bfs_queue.append(root)
    while len(bfs_queue) > 0:
        node = bfs_queue.pop()
        if node.vertical_level > max_vertical_level:
            top_view_nodes.append(node.val)
        elif node.vertical_level < min_vertical_level:
            top_view_nodes.appendleft(node.val)
        max_vertical_level = max(node.vertical_level, max_vertical_level)
        min_vertical_level = min(node.vertical_level, min_vertical_level)
        if node.left is not None:
            node.left.vertical_level = node.vertical_level - 1
            bfs_queue.append(node.left)
        if node.right is not None:
            node.right.vertical_level = node.vertical_level + 1
            bfs_queue.append(node.right)

    return list(top_view_nodes)


if __name__ == '__main__':
    root_node = create_mock_binary_tree()
    print(top_view(root_node))
    print(top_view(root_node.left))
    print(top_view(root_node.right))
    print(top_view(root_node.right.left))
