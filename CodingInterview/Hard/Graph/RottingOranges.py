"""
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell
1 representing a fresh orange
2 representing a rotten orange
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
"""

"""
https://leetcode.com/problems/rotting-oranges/
A bfs problem
"""

from typing import List
from collections import deque

directions = [(1, 0), (0, 1), (0, -1), (-1, 0)]


def is_good_orange(grid, i, j):
    m = len(grid)
    n = len(grid[0])
    return 0 <= i < m and 0 <= j < n and grid[i][j] == 1


def rot_adjacent(grid, i, j):
    rotten = []
    for d in directions:
        if is_good_orange(grid, i + d[0], j + d[1]):
            grid[i + d[0]][j + d[1]] = 2
            rotten.append((i + d[0], j + d[1]))
    return rotten


def oranges_rotting(grid: List[List[int]]) -> int:
    m = len(grid)
    n = len(grid[0])
    queue = deque()
    good_orange = set()

    for i in range(m):
        for j in range(n):
            if grid[i][j] == 2:
                queue.append((i, j))
            elif grid[i][j] == 1:
                good_orange.add((i, j))

    queue.append(None)
    time = 0

    # BFS
    while len(queue):
        nxt = queue.popleft()
        if nxt is None:
            if len(queue) == 0:
                break
            else:
                time += 1
                queue.append(None)
        else:
            next_rots = rot_adjacent(grid, nxt[0], nxt[1])
            [queue.append(rot) for rot in next_rots]

    for orange in good_orange:
        if grid[orange[0]][orange[1]] == 1:
            return -1
    return time


if __name__ == '__main__':
    matrix = [[2, 1, 1],
              [1, 1, 0],
              [0, 1, 1]]
    print(oranges_rotting(matrix))

    matrix = [[2, 1, 1],
              [0, 1, 1],
              [1, 0, 1]]
    print(oranges_rotting(matrix))

    matrix = [[0, 2]]
    print(oranges_rotting(matrix))
