"""
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and
then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and
concatenate them to build the expression "+2-1".

Return the number of different expressions that you can build, which evaluates to target.

Input: nums = [1,1,1,1,1], target = 3
Output: 5

Input: nums = [1], target = 1
Output: 1
"""

"""
https://leetcode.com/problems/target-sum/
"""


def target_sum_ways_helper(numbers, target, start, memo):
    if target == 0 and len(numbers) == start:
        return 1
    if len(numbers) <= start:
        return 0
    if (target, start) in memo:
        return memo[(target, start)]
    positive_inclusion = target_sum_ways_helper(numbers, target + numbers[start], start + 1, memo)
    negative_inclusion = target_sum_ways_helper(numbers, target - numbers[start], start + 1, memo)
    memo[(target, start)] = positive_inclusion + negative_inclusion
    return memo[(target, start)]


def target_sum_ways(numbers, target):
    return target_sum_ways_helper(numbers, target, 0, {})


if __name__ == '__main__':
    print(target_sum_ways([1, 1, 1, 1, 1], 3))
    print(target_sum_ways([1], 1))
    print(target_sum_ways([16, 40, 9, 17, 49, 32, 30, 10, 38, 36, 31, 22, 3, 36, 32, 2, 26, 17, 30, 47], 49))
    print(target_sum_ways([1, 0], 1))
