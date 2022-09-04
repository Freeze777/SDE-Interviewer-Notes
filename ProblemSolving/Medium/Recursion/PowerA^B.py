"""
Write a function that returns a power function that computes pow(a,b) = a^b.
"""
"""
https://leetcode.com/problems/powx-n/
Challenge:
Best solution is O(logb)
Find which is efficient bit operations vs mathematical operations.
recursive vs while loop
UTs
"""


def pow(a: float, b: int):
    if b < 0:
        return pow(1 / a, abs(b))
    if b == 0:
        return 1
    if b & 1 == 0:
        return pow(a * a, b >> 1)
    return a * pow(a * a, b >> 1)


if __name__ == '__main__':
    print(pow(4, 2))
    print(pow(5, 5))
    print(pow(4, -2))
    print(pow(-4, 3))
    print(pow(-5, -3))
    print(pow(4, 0))
    print(pow(0, 0))  # no proper value, long debate in maths
