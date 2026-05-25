"""
自己完成
"""
from typing import List
from collections import defaultdict
class Solution:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        l, r = 0, 0
        score, max_score = 0, 0
        dd = defaultdict(int)
        while r < len(nums):
            dd[nums[r]] += 1
            score += nums[r]
            while dd[nums[r]] > 1:
                dd[nums[l]] -= 1
                score -= nums[l]
                l += 1
            max_score = max(max_score, score)
            r += 1
        return max_score
if __name__ == "__main__":
    nums = [5]
    print("Ans = ", Solution().maximumUniqueSubarray(nums))