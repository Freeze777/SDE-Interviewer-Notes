"""
Question:
Write a function to check whether two given strings are anagram of each other or not.
An anagram of a string is another string that contains the same characters, only the order of characters can be different.

NOTE : Assume english language alphabets in lowercase for now.

For example,
1. "abcd" & "dabc"  - YES
2. "abccc" & "abc"  - NO
3. "xyz" & "abc"    - NO
4. "silent" & "listen" - YES
"""
from typing import List

"""
https://leetcode.com/problems/valid-anagram

Challenge with more questions based on how interviewee performs: 
- Time complexity and space complexity. Best is O(N) time and O(1) space.
- Increase difficulty : https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
- Large stream of strings, print anagram pairs (scalable solution) ??
- Write unit tests

"""


# O(NlogN) + O(MlogM) time, O(1) space - sorting
def solve_by_sort(word1, word2):
    return sorted(word1) == sorted(word2)


# O(N+M) time, O(N+M) space - letter counting
def solve_by_letter_count(word1, word2):
    from collections import Counter
    return Counter(word1) == Counter(word2)


# O(N) time, O(1) space - prime factorization
def solve_by_prime_number_hash(word1, word2):
    return get_prime_number_hash(word1) == get_prime_number_hash(word2)


def get_prime_number_hash(word):
    import operator
    from functools import reduce
    letter_code = {'a': 2, 'b': 3, 'c': 5, 'd': 7, 'e': 11, 'f': 13, 'g': 17, 'h': 19, 'i': 23, 'j': 29, 'k': 31,
                   'l': 37, 'm': 41, 'n': 43,
                   'o': 47, 'p': 53, 'q': 59, 'r': 61, 's': 67, 't': 71, 'u': 73, 'v': 79, 'w': 83, 'x': 89, 'y': 97,
                   'z': 101}
    return 0 if not word else reduce(operator.mul, [letter_code[letter] for letter in word])


def group_anagrams(words: List[str]) -> List[List[str]]:
    d = {}
    for w in words:
        prime_hash = get_prime_number_hash(w)
        if prime_hash not in d:
            d[prime_hash] = []
        d[prime_hash].append(w)
    return [wrd for wrd in d.values()]


if __name__ == '__main__':
    # anagram test
    w1 = 'listen'
    w2 = 'silent'
    print(solve_by_sort(w1, w2))
    print(solve_by_letter_count(w1, w2))
    print(solve_by_prime_number_hash(w1, w2))

    # group anagram test
    print(group_anagrams(["eat", "tea", "tan", "ate", "nat", "bat"]))
