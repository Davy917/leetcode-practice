from typing import List
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        nums_sum = sum(num for num in nums)
        if nums_sum < x:
            return -1
        target = nums_sum - x
        left = 0
        ans = -1

        window_val = 0
        for right in range(0, len(nums)):
            window_val += nums[right]
            while window_val > target:
                window_val -= nums[left]
                left += 1
            if window_val == target:
                ans = max(ans, right-left+1)
            print(f"left = {left}, right = {right}, ans = {ans}\n")

        return -1 if ans == -1 else len(nums) - ans

if __name__ == "__main__":
    nums = [3,2,20,1,1,3]
    nums2 = [5,2,3,1,1]
    nums3 = [1, 1]
    print("Ans = ", Solution().minOperations(nums3, 3))
"""
寫的時候卡在18行寫不出來, 當時是嘗試找到target直接返回
抄了精選評論區, 俊桑的答案
https://leetcode.cn/leetbook/read/sliding-window-and-two-pointers/rl4f2t/

注意:
第4行~第7行借鑒了 1423-max-score, 裡面的思路, 建議先看懂1423再看這題
第17行
找到target後並不能直接返回, 因為我們要找的是最大ans
第21行
這邊的ans就是第17行找到的最大ans
拿 len(nums) - ans 才會得到我們想要的minOperations
"""