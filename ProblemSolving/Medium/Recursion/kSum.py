"""
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

https://leetcode.com/problems/3sum

https://leetcode.com/problems/4sum/
"""


def k_sum(arr, k, target):
    arr.sort()
    if k == 2:
        return two_sum(arr, target)
    else:
        result, visited = [], set()
        for i in range(len(arr) - k + 1):
            if arr[i] not in visited:
                sub_sums = k_sum(arr[i + 1:], k - 1, target - arr[i])
                result += [[arr[i]] + sub for sub in sub_sums]
            visited.add(arr[i])
        return result


def two_sum(arr, target):
    ans = set()
    i, j = 0, len(arr) - 1
    while i < j and i < len(arr) and j >= 0:
        if arr[i] + arr[j] == target:
            ans.add((arr[i], arr[j]))
            i, j = i + 1, j - 1
        elif arr[i] + arr[j] > target:
            j -= 1
        else:
            i += 1
    return [list(t) for t in ans]


if __name__ == '__main__':
    print(k_sum([2, 3, 4, 5, 1, 9, 12, 6, 1, 9, 0], 2, 10))
    print(k_sum([2, 3, 4, 5, 1, 9, 12, 6, 1, 9, 0], 3, 10))
    print(k_sum([2, 3, 4, 5, 1, 9, 12, 6, 1, 9, 0], 3, 10))
    print(k_sum([2, 3, 4, 5, 1, 9, 12, 6, 1, 9, 0], 4, 10))

    print(k_sum([0, 0, 0], 2, 0))
    print(k_sum([0, 0, 0], 3, 0))
    print(k_sum([0, 0, 0, 0, 0], 4, 0))
