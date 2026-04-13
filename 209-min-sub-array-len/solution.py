from typing import List
import math
class Solution1122:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        left = 0
        Sum = 0
        min_len = float('inf')

        for right in range(len(nums)):
            Sum += nums[right]
            while Sum >= target:
                min_len = math.min(min_len, right - left + 1)
                print("Find shorter len ", min_len)
                Sum -= nums[left]
                left += 1
        
        return 0 if min_len == float('inf') else min_len #三元運算子

print(Solution1122().minSubArrayLen(7, [2, 3, 1, 2, 4, 3]))