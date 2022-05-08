from typing import List

"""
https://leetcode.com/problems/longest-common-prefix/
"""
def longest_common_prefix(strs: List[str]) -> str:
    prefix = []
    for i in range(200):
        if i >= len(strs[0]):
            break
        char = strs[0][i]
        flag = True
        for s in strs:
            flag = flag and i < len(s) and (s[i] == char)
        if flag:
            prefix.append(char)
        else:
            break
    return "".join(prefix)


if __name__ == '__main__':
    print(longest_common_prefix(["flower", "flow", "flight"]))
    print(longest_common_prefix(["dog", "racecar", "car"]))
