"""

You are given N courses with course Ids [0 to N-1] and some of these courses have pre-requisites.
Print out any one order in which all courses can be completed or return empty order if all courses cannot be completed.

prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]
"""

"""
https://leetcode.com/problems/course-schedule-ii/
https://www.geeksforgeeks.org/find-the-ordering-of-tasks-from-given-dependencies/
"""
