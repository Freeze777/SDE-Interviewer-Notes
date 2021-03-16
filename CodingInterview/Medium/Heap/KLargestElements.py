"""
Given an array of integers find the k largest elements in that array
"""
"""
https://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
"""

from heapq import heapreplace, heappop, heapify
from typing import List


# O(k+ nlogk)
def k_largest_minheap(arr: List[int], k: int):
    heap = arr[0:k]
    heapify(heap)
    for e in arr[k:]:
        heapreplace(heap, e)
    heap.sort(reverse=True)
    return heap


# O(n + klogn)
def k_largest_maxheap(arr: List[int], k: int):
    heap = [-e for e in arr]
    heapify(heap)
    return [-heappop(heap) for _ in range(k)]


# O(nlogn + k)
def k_largest_sort(arr: List[int], k: int):
    arr.sort(reverse=True)
    return arr[0:k]


if __name__ == '__main__':
    elements = [1, 10, 9, 3, 55, 22, -10, 0, 4]
    k = 4
    print(k_largest_minheap(elements, k))
    print(k_largest_maxheap(elements, k))
    print(k_largest_sort(elements, k))
