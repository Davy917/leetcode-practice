#第二次寫, 自己寫出來了
def isBadVersion(version: int) -> bool:
    if version in (1,2,3):
        return False
    else:
        return True
class Solution:
    def firstBadVersion(self, n: int) -> int:
        left, right = 1, n
        while left < right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if not isBadVersion(middle):
                left = middle + 1
            else:
                right = middle
        return right
if __name__ == "__main__":
    n, bad = 5, 4
    print("Ans = ", Solution().firstBadVersion(n))

"""
我們直接假設 testcase 如下:
[1, 2, 3, 4, 5]
[g, g, g, b, b]
"""