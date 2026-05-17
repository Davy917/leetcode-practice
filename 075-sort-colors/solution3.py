from typing import List
class Solution:
    def sortColors(self, nums: List[int]) -> None:
        n = len(nums)
        if n < 2:
            return
        """
        循環不變量的定義
        [0, p0) == 0
        [p0, i) == 1
        (p2, n - 1] == 2
        """
        i, p0, p2 = 0, 0, n - 1
        while (i <= p2):
            if nums[i] == 0:
                nums[i], nums[p0] = nums[p0], nums[i]
                p0 += 1
                i += 1
            elif nums[i] == 1:
                i += 1
            else:
                nums[i], nums[p2] = nums[p2], nums[i]
                p2 -= 1
            print(f"p0 = {p0}, i = {i}, p2 = {p2}\n")
            print(nums)

if __name__ == "__main__":
    nums = [2, 0, 2, 1, 1, 0]
    Solution().sortColors(nums)
    print("Ans = ", nums)
    
"""
官方解答:
先照抄再理解
https://leetcode.cn/leetbook/read/sliding-window-and-two-pointers/rl7myd/
"""