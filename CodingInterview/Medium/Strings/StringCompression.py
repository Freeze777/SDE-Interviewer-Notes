"""
Given a character array we need to compress it in-place as follows:
aaabcccdd -> a3b1c3d2 (no resizing needed)
abcd -> a1b1c1d1 (need resizing)
"""
from typing import List


def string_compression(arr: List[str]):
    burst_start = total_empty_slots = lone_char_count = 0

    while burst_start < len(arr):
        empty_slots, burst_end = find_character_bursts(arr, burst_start)
        burst_size = burst_end - burst_start + 1
        total_empty_slots += empty_slots
        if burst_size > 1:
            arr[burst_end - 1], arr[burst_end] = arr[burst_end], burst_size
            total_empty_slots -= 1
        else:
            lone_char_count += 1
        burst_start = burst_end + 1

    arr = resize(arr, lone_char_count, total_empty_slots)
    arr = right_shift_empty_slots(arr)
    arr = final_compression(arr)
    return arr


def find_character_bursts(arr, burst_start):
    empty_slots = 0
    while burst_start + 1 < len(arr) and arr[burst_start] == arr[burst_start + 1]:
        arr[burst_start] = ''
        empty_slots += 1
        burst_start += 1
    return empty_slots, burst_start


def resize(arr, lone_char_count, total_empty_slots):
    if total_empty_slots < lone_char_count:
        arr += [''] * (lone_char_count - total_empty_slots)
    return arr


def right_shift_empty_slots(arr):
    count = 0
    for i in range(len(arr)):
        if arr[i] != '':
            arr[count] = arr[i]
            count += 1
    while count < len(arr):
        arr[count] = ''
        count += 1
    return arr


def final_compression(arr):
    last_char_end = arr.index('') - 1
    final_arr_start = len(arr) - 1
    while last_char_end >= 0:
        if not isinstance(arr[last_char_end], int):
            arr[final_arr_start] = 1
            arr[final_arr_start - 1] = arr[last_char_end]
            last_char_end -= 1
            final_arr_start -= 2
        else:
            arr[final_arr_start] = arr[last_char_end]
            arr[final_arr_start - 1] = arr[last_char_end - 1]
            last_char_end -= 2
            final_arr_start -= 2
    return arr[final_arr_start + 1:]


if __name__ == '__main__':
    print(string_compression(list("abbcdef")))
    print(string_compression(list("aaaaaaaaabbcdef")))
    print(string_compression(list("aaaaaaaaabbcdeeeeeeefzz")))
    print(string_compression(list("abcdef")))
    print(string_compression(list("abbcdeef")))
    print(string_compression(list("aaaaaaaaaa")))
    print(string_compression(list("baaacdef")))
