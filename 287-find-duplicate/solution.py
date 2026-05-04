"""
暴力解
時間複雜度：O(n^2) 提交會超時
"""
from typing import List
class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        for index, value in enumerate(nums):
            visitor = index + 1
            while visitor < len(nums):
                if nums[visitor] == value:
                    return value
                visitor += 1
        return -1
if __name__ == "__main__":
    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 9]
    nums2 = [3, 2, 3, 4, 2]
    print(Solution().findDuplicate(nums2))