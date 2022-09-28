"""

"""
"""
https://leetcode.com/problems/count-and-say/
"""


def burst(num_arr, start):
    while start + 1 < len(num_arr) and num_arr[start] == num_arr[start + 1]:
        start += 1
    return start


def get_count(num_arr):
    res_arr = []
    start = 0
    while start < len(num_arr):
        end = burst(num_arr, start)
        burst_size = end - start + 1
        res_arr.append(str(burst_size))
        res_arr.append(num_arr[start])
        start = end + 1
    return res_arr


def count_and_say(n: int) -> str:
    root_arr = ["1"]
    for i in range(n - 1):
        root_arr = get_count(root_arr)
    return "".join(root_arr)


if __name__ == '__main__':
    print(count_and_say(1))
    print(count_and_say(3))
    print(count_and_say(30))
