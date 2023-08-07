"""
Code samples:
Anagrams.py
Anagram++.py
MultiThreadedAnagram.py

Design doc:
https://whimsical.com/anagramservice-82Xa6ZibRcMWXV1hxcGiCo
"""


"""
An anagram of a string is another string that contains the same characters, only the order of characters can be different.
NOTE : Assume english language alphabets in lowercase for now.
For example,
1. "abcd" & "dabc"  - YES
2. "abccc" & "abc"  - NO
3. "xyz" & "abc"    - NO
4. "silent" & "listen" - YES

Design a system that takes in 2 files from a user and computes if they are anagrams or not. 
Scale : 100k requests per day and 50 GB max size per file.
"""
