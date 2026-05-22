from typing import List
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        total_sum = sum(num for num in nums)
        target = total_sum - x
        left, cur_sum = 0, 0
        for right in range(0, len(nums)):
            cur_sum += nums[right]
            if cur_sum == target:
                return len(nums) - right
            if cur_sum > target:
                cur_sum -= nums[left]
        return -1
if __name__ == "__main__":
    nums = [1, 1, 4, 2, 3]
    x = 5
    print("Ans = ", Solution().minOperations(nums, x))