"""
第一次練習看python
完整思路看java
再優化看go
"""
from typing import List
class Solution:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        min = float('inf')

        while left <= right:

            mid = left + (right - left) // 2
            print("mid = ", mid)
            if nums[mid] < min:
                min = nums[mid]
                print("min value= ", min)

            if nums[mid] > nums[-1]:
                left = mid + 1
                print("left = ", left)
            else:
                right = mid - 1
                print("right = ", right)

        return min

print(Solution().findMin([3,1,2]))