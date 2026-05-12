"""
參考代碼:
algo/RadixSort/radixSort_reverse.py
"""
from typing import List
class Solution:
    def maximumGap(self, nums: List[int]) -> int:
        max_num = nums[0]
        min_num = nums[0]
        for num in nums:
            if num > max_num:
                max_num = num
            elif num < min_num:
                min_num = num

        max_digit_len = 0
        while max_num > 0:
            max_num //= 10
            max_digit_len += 1
        dev = 1
        for digit in range(max_digit_len):
            counting = [0] * 10
            for num in nums:
                radix = num // dev % 10
                counting[radix] += 1
            print("Before prefix = ", counting)

            counting[0] -= 1
            for index in range(1, 10):
                counting[index] += counting[index - 1]
            print("After prefix = ", counting)

            result = [0] * len(nums)
            for index in range(len(nums) - 1, -1, -1):
                radix = nums[index] // dev % 10
                result[counting[radix]] = nums[index]
                counting[radix] -= 1
            dev *= 10
            nums[:len(nums)] = result[:len(result)]

        max_gap = 0
        for index in range(1, len(nums)):
            max_gap = max(max_gap, nums[index] - nums[index - 1])

        return max_gap
if __name__ == "__main__":
    arr = [3, 6, 9, 1]
    sol = Solution()
    print(sol.maximumGap(arr))