"""

You are given N courses with course Ids [0 to N-1] and some of these courses have pre-requisites.
prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai
Print out any one order in which all courses can be completed or return empty order if all courses cannot be completed.

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


def dfs_helper(course, graph, completed_courses, discovered_courses, order):
    discovered_courses.add(course)
    if course in graph:
        for next_course in graph[course]:
            if next_course not in completed_courses:
                if next_course in discovered_courses:
                    completed_courses.add(-1)
                else:
                    dfs_helper(next_course, graph, completed_courses, discovered_courses, order)
    completed_courses.add(course)
    order.append(course)


def course_order(num_courses, pre_requisites):
    graph = {}
    for e in pre_requisites:
        a, b = e
        if b not in graph:
            graph[b] = []
        graph[b].append(a)
    completed_courses = set()
    discovered_courses = set()
    order = []
    for course in range(num_courses):
        if course not in completed_courses:
            dfs_helper(course, graph, completed_courses, discovered_courses, order)
    return [] if -1 in completed_courses else order[::-1]


if __name__ == '__main__':
    numcourses = 4
    prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
    """
        0 --> 1  
         \     \
          v     v
          2 --> 3 
    """
    print(course_order(numcourses, prerequisites))
