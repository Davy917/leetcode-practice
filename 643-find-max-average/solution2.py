"""
官方解答, 看一次就明白了
"""
from typing import List
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        left = 0
        max_val, window_sum = sum(nums[:k]), sum(nums[:k])
        for index in range(k - 1, len(nums)):
            if index > k-1: #判斷是不是在迴圈的第一圈
                window_sum += nums[index]
            max_val = max(max_val, window_sum)
            window_sum -= nums[left]
            left += 1
        return max_val / k
if __name__ == "__main__":
    nums = [-5]
    k = 1
    print("Ans = ", Solution().findMaxAverage(nums, k))

"""
官方解答:
https://leetcode.cn/problems/maximum-average-subarray-i/solutions/590322/zi-shu-zu-zui-da-ping-jun-shu-i-by-leetc-us1k

跟solution.py的差別在於, 這一版在計算window_sum時不用遍歷, 所以不會超時
"""