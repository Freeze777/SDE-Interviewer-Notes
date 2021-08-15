"""
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

Input: matrix = [["0","1"],["1","0"]]
Output: 1

Input: matrix = [["0"]]
Output: 0
"""
"""
https://leetcode.com/problems/maximal-square/
"""
from collections import defaultdict
from typing import List


def maximal_square(matrix: List[List[str]]):
    row, col, memo = len(matrix), len(matrix[0]), defaultdict(int)
    for i in range(row):
        for j in range(col):
            memo[(i, j)] = 0 if matrix[i][j] == "0" \
                else min(memo[(i - 1, j)], memo[(i - 1, j - 1)], memo[(i, j - 1)]) + 1
    return 0 if len(memo) == 0 else max(memo.values()) ** 2


if __name__ == '__main__':
    print(maximal_square([["1", "0", "1", "0", "0"],
                          ["1", "0", "1", "1", "1"],
                          ["1", "1", "1", "1", "1"],
                          ["1", "0", "0", "1", "0"]]))
    print(maximal_square([["0", "1"], ["1", "0"]]))
    print(maximal_square([["0"]]))
    print(maximal_square([[]]))
    print(maximal_square([["1", "0", "1", "0", "0"],
                          ["1", "0", "1", "1", "1"],
                          ["1", "1", "1", "1", "1"],
                          ["1", "0", "1", "1", "1"]]))
