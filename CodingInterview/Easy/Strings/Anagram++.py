"""
Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
Return the minimum number of steps to make t an anagram of s.
An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

Input: s = "bab", t = "aba"
Output: 1

Input: s = "leetcode", t = "practice"
Output: 5

Input: s = "friend", t = "family"
Output: 4

"""
"""
https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
"""

from collections import defaultdict, Counter


def min_steps(s: str, t: str) -> int:
    wc1 = defaultdict(lambda: 0, Counter(s))
    wc2 = defaultdict(lambda: 0, Counter(t))
    ans = 0
    for c in set(s + t):
        ans += abs(wc1[c] - wc2[c])
    return ans // 2


if __name__ == '__main__':
    # min steps
    print(min_steps("friend", "family"))
    print(min_steps("xxyyzz", "xxyyzz"))
    print(min_steps("leetcode", "practice"))

"""
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]

Input: s = "abab", p = "ab"
Output: [0,1,2]
"""
"""
https://leetcode.com/problems/find-all-anagrams-in-a-string/
"""


def find_anagrams(s: str, p: str):
    p_wc = defaultdict(lambda: 0, Counter(p))
    window_wc = defaultdict(lambda: 0, Counter(s[:len(p)]))
    indices = [0] if window_wc == p_wc else []
    for i in range(1, len(s) - len(p) + 1):
        window_wc[s[i - 1]] -= 1
        if window_wc[s[i - 1]] == 0:
            window_wc.pop(s[i - 1])
        window_wc[s[i + len(p) - 1]] += 1
        if window_wc == p_wc:
            indices.append(i)
    return indices


if __name__ == '__main__':
    # find anagrams
    print(find_anagrams("cbaebabacd", "abc"))
    print(find_anagrams("abab", "ab"))
