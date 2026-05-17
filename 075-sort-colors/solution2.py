"""
官方解答第二種版本
"""
from typing import List
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        p0 , p1, n = 0, 0, len(nums)
        for i in range(n):
            if(nums[i] == 1):
                nums[p1], nums[i] = nums[i], nums[p1]
                p1 += 1
            elif(nums[i] == 0):
                nums[p0], nums[i] = nums[i], nums[p0]
                if p0 < p1:
                    nums[p1], nums[i] = nums[i], nums[p1]
                p0 += 1
                p1 += 1
            print(nums)
if __name__ == "__main__":
    nums = [2, 0, 2, 1, 1, 0]
    Solution().sortColors(nums)
    print("Ans = ", nums)

"""
官方解答:
先照抄再理解
https://leetcode.cn/problems/sort-colors/solutions/437968/yan-se-fen-lei-by-leetcode-solution/
"""