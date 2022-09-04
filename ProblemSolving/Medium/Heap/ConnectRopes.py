"""
Given n ropes of different lengths, we need to connect these ropes into one rope.
We can connect only 2 ropes at a time.
The cost required to connect 2 ropes is equal to the sum of their lengths.
The length of this connected rope is also equal to the sum of their lengths.
This process is repeated until n ropes are connected into a single rope.
Find the min possible cost required to connect all the ropes.

Example 1:
ropes = [8, 4, 6, 12]
Output: 58

Example 2:
ropes = [4, 3, 2, 6]
Output: 29

Example 3:
ropes = [8, 4, 6, 7, 12]
Output: 84

Example 4:
ropes = [4, 2, 7, 6, 9]
Output: 62

Example 5:
ropes = [4, 3, 5, 7, 6, 9]
Output: 86
"""

"""
https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
"""


def connect_ropes(ropes) -> int:
    import heapq
    total_cost = 0
    heapq.heapify(ropes)
    while len(ropes) >= 2:
        first_rope, second_rope = heapq.heappop(ropes), heapq.heappop(ropes)
        total_cost += first_rope + second_rope
        heapq.heappush(ropes, first_rope + second_rope)
    return total_cost


if __name__ == '__main__':
    print(connect_ropes([8, 4, 6, 12]))
    print(connect_ropes([4, 3, 2, 6]))
    print(connect_ropes([8, 4, 6, 7, 12]))
    print(connect_ropes([4, 2, 7, 6, 9]))
    print(connect_ropes([4, 3, 5, 7, 6, 9]))

    print(connect_ropes([4, 3]))
    print(connect_ropes([4]))

