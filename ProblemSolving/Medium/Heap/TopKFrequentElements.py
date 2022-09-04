"""

https://leetcode.com/problems/top-k-frequent-elements/
https://leetcode.com/problems/top-k-frequent-words/
"""
from collections import Counter
from typing import List

from ProblemSolving.Medium.Heap.KLargestElements import k_largest_minheap


def top_k_frequent(elements: List[int], k: int):
    counts = [(v, k) for k, v in Counter(elements).items()]  # (count, key) tuples
    ans = [t[::-1][0] for t in k_largest_minheap(counts, k)]
    return ans


if __name__ == '__main__':
    print(top_k_frequent([1, 1, 1, 1, 2, 2, 3, 7, 9, 9, 9, 9, 9, -1, -1, -1, -1, -1], 3))
    print(top_k_frequent([1], 1))
    print(top_k_frequent([1, 1, 1, 2, 2, 3], 2))
    print(top_k_frequent(["hi", "hi", "hello", "hi", "me", "me"], 2))
