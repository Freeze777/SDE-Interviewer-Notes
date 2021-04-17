"""
Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
"""

"""
https://leetcode.com/problems/minimum-path-sum/
"""


def minimum_path_sum_helper(grid, i, j, memo):
    if i == j == 0:
        memo[(i, j)] = grid[i][j]
    elif i == 0:
        memo[(i, j)] = grid[i][j] + minimum_path_sum_helper(grid, i, j - 1, memo)
    elif j == 0:
        memo[(i, j)] = grid[i][j] + minimum_path_sum_helper(grid, i - 1, j, memo)
    if (i, j) in memo:
        return memo[(i, j)]

    memo[(i, j)] = grid[i][j] + min(minimum_path_sum_helper(grid, i - 1, j, memo),
                                    minimum_path_sum_helper(grid, i, j - 1, memo))
    return memo[(i, j)]


def minimum_path_sum(grid):
    return minimum_path_sum_helper(grid, len(grid) - 1, len(grid[0]) - 1, {})


if __name__ == '__main__':
    matrix = [[1, 3, 1],
              [1, 5, 1],
              [4, 2, 1]]
    print(minimum_path_sum(matrix))

    matrix = [[1, 2, 3],
              [4, 5, 6]]
    print(minimum_path_sum(matrix))
