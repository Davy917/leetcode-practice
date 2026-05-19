"""
自己想的暴力解法, 提交會超時
官方解法, 看solution2.py
"""
from typing import List
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        left = 0
        max_val = sum(nums[:k])
        for index in range(k - 1, len(nums)):
            right = index + 1
            max_val = max(max_val, sum(nums[left:right]))
            left += 1
        return max_val / k

if __name__ == "__main__":
    nums = [1,12,-5,-6,50,3]
    k = 4
    print("Ans = ", Solution().findMaxAverage(nums, k))