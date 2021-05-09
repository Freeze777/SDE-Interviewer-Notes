"""
You are given a network of n nodes, labeled from 1 to n.
You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node,
 vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
If it is impossible for all the n nodes to receive the signal, return -1.

Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
"""

"""
Typical Djikstra implementation
https://leetcode.com/problems/network-delay-time/
"""
import sys
from collections import defaultdict
from heapq import heappop, heapify, heappush
from typing import List


def bfs(start, num_vertex, graph):
    bfs_pq = [(0, start)]
    heapify(bfs_pq)
    shortest_path = defaultdict(lambda: sys.maxsize, {})

    while len(bfs_pq):
        time, nxt = heappop(bfs_pq)
        shortest_path[nxt] = min(time, shortest_path[nxt])
        if nxt in graph:
            for adj_node in graph[nxt]:
                v, w = adj_node
                if v not in shortest_path:
                    heappush(bfs_pq, (time + w, v))

    return max(shortest_path.values()) if len(shortest_path) == num_vertex else -1


def network_delay_time(times: List[List[int]], n: int, k: int) -> int:
    graph = defaultdict(list)
    for edge in times:
        u, v, w = edge
        graph[u].append((v, w))
    [adj_list.sort(key=lambda x: x[1]) for adj_list in graph.values()]  # optional : to boost performance
    return bfs(k, n, graph)


if __name__ == '__main__':
    print(network_delay_time([[2, 1, 1], [2, 3, 1], [3, 4, 1]], 4, 2))
    print(network_delay_time([[1, 2, 1]], 2, 1))
    print(network_delay_time([[1, 2, 1]], 2, 2))
    print(network_delay_time([[1, 2, 1], [2, 1, 3]], 2, 2))
    print(network_delay_time([[1, 2, 1], [2, 3, 2], [1, 3, 4]], 3, 1))
    print(network_delay_time([[1, 2, 1], [2, 3, 7], [1, 3, 4], [2, 1, 2]], 3, 2))
    print(network_delay_time(
        [[4, 2, 76], [1, 3, 79], [3, 1, 81], [4, 3, 30], [2, 1, 47], [1, 5, 61], [1, 4, 99], [3, 4, 68], [3, 5, 46],
         [4, 1, 6], [5, 4, 7], [5, 3, 44], [4, 5, 19], [2, 3, 13], [3, 2, 18], [1, 2, 0], [5, 1, 25], [2, 5, 58],
         [2, 4, 77], [5, 2, 74]], 5, 3))
