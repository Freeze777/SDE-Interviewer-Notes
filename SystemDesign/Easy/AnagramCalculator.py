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

Design a system that takes in 2 huge text files from a user and computes if they are anagrams or not. 
Scale: 100k requests per day and 100 GB max size per file.
"""

"""
FR:
1. For simplicity, we will assume that huge files are stored in a cloud storage like S3. 
(I expect the interviewee to come up with this to keep the scope of the problem limited)
2. A simple UI for users to interact with the system. Put the 2 file paths and click on a button to check if they are anagrams or not.
3. The system should notify the user if the files are anagrams or not.
(Notification system is out of scope, use any 3rd party service like Twilio or Sendgrid)


NFR:
1. Scalability: 100k requests per day and 100 GB max size per file.
2. Performance: The system should be able to process the files quickly. 
(Realtime response is not required but it should be within a few seconds)
3. Availability: The system should be available 24x7.
4. Cost: The system should be cost effective.
5. Reliability:-The system should be reliable. Partial processing of files should not happen.
6. Monitoring: The system should be monitored.
"""
