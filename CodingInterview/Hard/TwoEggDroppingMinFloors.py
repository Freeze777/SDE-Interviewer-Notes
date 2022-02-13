import cmath
import math

"""
You are given two identical eggs and you have access to a building with n floors labeled from 1 to n.

You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, 
and any egg dropped at or below floor f will not break.

In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, 
you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

Return the minimum number of moves that you need to determine with certainty what the value of f is.
"""
"""
https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/
"""


def min_drops(num_floors: int) -> float:
    if num_floors == 1:
        return 1
    # solve for x: x(x-1)/2 = n => x^2-x-2n=0
    a, b, c = 1, -1, -2 * num_floors
    d = (b ** 2) - (4 * a * c)
    sqrt_d = cmath.sqrt(d)
    x1 = (-b + sqrt_d) / (2 * a)
    x2 = (-b - sqrt_d) / (2 * a)
    return min(abs(math.floor(x1.real)), abs(math.floor(x2.real)))


if __name__ == '__main__':
    print(min_drops(1))
    print(min_drops(2))
    print(min_drops(5))
    print(min_drops(6))
    print(min_drops(100))
