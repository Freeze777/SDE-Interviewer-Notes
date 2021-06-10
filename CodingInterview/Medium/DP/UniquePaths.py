"""

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Input: m = 3, n = 7
Output: 28

Input: m = 3, n = 2
Output: 3

Input: m = 7, n = 3
Output: 28

Input: m = 3, n = 3
Output: 6
"""

"""
https://leetcode.com/problems/unique-paths/
"""


def unique_paths(m, n, memo=None):
    if memo is None:
        memo = {}
    if m == 0 or n == 0:
        return 0
    if m == 1 or n == 1:
        return 1
    if (m, n) in memo or (n, m) in memo:
        return memo[(m, n)] or memo[(n, m)]
    memo[(m, n)] = memo[(n, m)] = unique_paths(m - 1, n, memo) + unique_paths(m, n - 1, memo)
    return memo[(m, n)]


if __name__ == '__main__':
    print(unique_paths(3, 7))
    print(unique_paths(3, 2))
    print(unique_paths(7, 3))
    print(unique_paths(3, 3))
