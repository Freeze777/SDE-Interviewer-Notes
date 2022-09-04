from collections import Counter, defaultdict

"""
An anagram of a string is a string that contains the same characters with a different (or the same) ordering.
You are given two strings s and t. In one step, you can append any character to either s or t.
Return the minimum number of steps to make s and t anagrams of each other.

Input: s = "leetcode", t = "coats"
Output: 7

Input: s = "night", t = "thing"
Output: 0
"""
"""
https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/
"""
def min_steps(s: str, t: str) -> int:
    wc1, wc2, ans = defaultdict(lambda: 0, Counter(s)), defaultdict(lambda: 0, Counter(t)), 0
    for c in set(s + t):
        ans += abs(wc1[c] - wc2[c])
    return ans


if __name__ == '__main__':
    print(min_steps(s="leetcode", t="coats"))
    print(min_steps(s="night", t="thing"))
