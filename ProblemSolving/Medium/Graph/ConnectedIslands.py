"""

Given an MxN matrix with 1's and 0's, find the following:
1) Number of connected islands
2) Size of the largest island
NOTE : An island is continuously connected 1's in the matrix. You can move in all 8 directions.

def islands(matrix) -> (int, int):

example:
matrix = [
        [1, 1, 0, 0, 0],
        [0, 1, 0, 0, 1],
        [1, 0, 0, 1, 1],
        [0, 0, 0, 0, 0],
        [1, 0, 1, 0, 1]
        ]
Ans: numberOfIslands: 5, maxSize = 4

matrix = [
        [1, 0, 0, 0, 1],
        [0, 1, 0, 1, 0],
        [0, 0, 1, 0, 0],
        [0, 1, 0, 1, 0],
        [1, 0, 0, 0, 1]
    ]
Ans: numberOfIslands: 1, maxSize = 9

"""

"""
https://leetcode.com/problems/max-area-of-island/
https://www.geeksforgeeks.org/find-length-largest-region-boolean-matrix/
https://www.geeksforgeeks.org/find-number-of-islands/

Things to challenge:
Solve both number of islands and largest island
Do we need to use a separate visited array?
Efficient query problem : Given i,j find the size of its containing island. we have 10^6 such queries 

"""


def is_valid(matrix, i, j, visited):
    m, n = len(matrix), len(matrix[0])
    return 0 <= i < m and 0 <= j < n and not visited[i][j] and matrix[i][j] == 1


def search(matrix, i, j, visited):
    directions = [(1, 1), (1, 0), (1, -1), (0, 1), (0, -1), (-1, 1), (-1, 0), (-1, -1)]
    if not is_valid(matrix, i, j, visited):
        return 0
    visited[i][j], size = True, 1
    for direction in directions:
        size += search(matrix, i + direction[0], j + direction[1], visited)
    return size


def islands(matrix) -> (int, int):
    row, col = len(matrix), len(matrix[0])
    visited = [[False for _ in range(col)] for _ in range(row)]
    num_islands = max_size = 0
    for i in range(row):
        for j in range(col):
            if matrix[i][j] == 1 and not visited[i][j]:
                max_size = max(max_size, search(matrix, i, j, visited))
                num_islands += 1
    return num_islands, max_size


if __name__ == '__main__':
    matrix1 = [
        [1, 1, 0, 0, 0, 0],
        [0, 1, 0, 0, 1, 0],
        [1, 0, 0, 1, 1, 0],
        [0, 0, 0, 0, 0, 0],
        [1, 0, 1, 0, 1, 0]
    ]
    print(islands(matrix1))

    matrix2 = [
        [1, 0, 0, 0, 1],
        [0, 1, 0, 1, 0],
        [0, 0, 1, 0, 0],
        [0, 1, 0, 1, 0],
        [1, 0, 0, 0, 1]
    ]
    print(islands(matrix2))
