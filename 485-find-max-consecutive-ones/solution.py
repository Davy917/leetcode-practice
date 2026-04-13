from typing import List
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        if len(nums) == 0:
            return None
        temp_len = 0
        max_len = 0
        for i in range(len(nums)):
            if nums[i] == 1:
                temp_len += 1
            else:
                temp_len = 0

            if temp_len > max_len:
                max_len = temp_len
                print("find max_len", max_len)

        return max_len

print(Solution().findMaxConsecutiveOnes([1, 1, 0, 1, 1, 1]))