"""
自己寫的, 最直觀的寫法
"""
from collections import defaultdict
from typing import List
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        dd = defaultdict(int)
        for num in nums:
            if num == 0:
                dd[num] += 1
            elif num == 1:
                dd[num] += 1
            else:
                dd[num] += 1

        nums[:dd[0]] = [0] * dd[0]
        nums[dd[0] : dd[0] + dd[1]] = [1] * dd[1]
        nums[dd[0] + dd[1]:] = [2] * dd[2]

if __name__ == "__main__":
    nums = [0, 0, 0]
    Solution().sortColors(nums)
    print("Ans = ", nums)