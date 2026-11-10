"""
第一次練習java
第二次練習python
第三次練習js
第四次練習go
"""
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
    bad = 4
    print("Ans = ", Solution().firstBadVersion(5))