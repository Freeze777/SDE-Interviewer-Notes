"""
You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional
path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.

All gardens have at most 3 paths coming into or leaving it.

Your task is to choose a flower type for each garden such that, for any two gardens connected by a path,
they have different types of flowers.

Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden.
The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.

Input: n = 3, paths = [[1,2],[2,3],[3,1]]
Output: [1,2,3]

Input: n = 4, paths = [[1,2],[3,4]]
Output: [1,2,1,2]

Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
Output: [1,2,3,4]
"""

"""
Graph traversal
https://leetcode.com/problems/flower-planting-with-no-adjacent/
"""

import random
from collections import defaultdict, deque
from typing import List


def bfs(num_vertex, graph):
    flowers = {}
    for node in range(1, num_vertex + 1):
        if node in flowers:
            continue

        bfs_queue = deque()
        bfs_queue.append(node)

        while len(bfs_queue):
            nxt = bfs_queue.popleft()
            if nxt not in flowers:
                if nxt in graph:
                    adj_list = graph[nxt]
                    adj_flowers = set(map(flowers.get, adj_list))
                    available_flowers = {1, 2, 3, 4} - adj_flowers
                    flowers[nxt] = available_flowers.pop()
                    for adj_node in graph[nxt]:
                        bfs_queue.append(adj_node)
                else:
                    flowers[nxt] = random.randint(1, 4)

    return [flowers[k] for k in sorted(flowers.keys())]


def garden_flowers(n: int, paths: List[List[int]]) -> List[int]:
    graph = defaultdict(list)
    for edge in paths:
        u, v = edge
        graph[u].append(v)
        graph[v].append(u)
    return bfs(n, graph)


if __name__ == '__main__':
    print(garden_flowers(3, [[1, 2], [2, 3], [3, 1]]))
    print(garden_flowers(4, [[1, 2], [3, 4]]))
    print(garden_flowers(5, [[4, 1], [4, 2], [4, 3], [2, 5], [1, 2], [1, 5]]))
    print(garden_flowers(1, []))
    print(garden_flowers(100, []))
    print(garden_flowers(100, [[1, 2]]))
