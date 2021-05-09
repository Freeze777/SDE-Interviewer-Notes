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
    print("# min steps #")
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
    print("# find anagrams #")
    print(find_anagrams("cbaebabacd", "abc"))
    print(find_anagrams("abab", "ab"))

"""
Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

Input: word1 = "abc", word2 = "bca"
Output: true

Input: word1 = "a", word2 = "aa"
Output: false

Input: word1 = "cabbba", word2 = "abbccc"
Output: true

Input: word1 = "cabbba", word2 = "aabbss"
Output: false
"""
"""
https://leetcode.com/problems/determine-if-two-strings-are-close/
"""


def close_strings(word1: str, word2: str):
    wc1, wc2 = Counter(word1), Counter(word2)
    return True if wc1 == wc2 else sorted(wc1.values()) == sorted(wc2.values()) and wc2.keys() == wc1.keys()


if __name__ == '__main__':
    print("# close strings #")
    print(close_strings("abc", "bca"))
    print(close_strings("a", "aa"))
    print(close_strings("cabbba", "abbccc"))
    print(close_strings("cabbba", "aabbss"))
    print(close_strings("uau", "ssx"))
    print(close_strings("uiuiiuuiuuuuuuwiwuuwiiiiuuuuwwiwuuu", "rppprrpurrrrrurrrurprprprprpuprruur"))
