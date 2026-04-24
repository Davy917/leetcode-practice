from typing import List
class Solution:
    def findPeakElement(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        while left < right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if nums[middle] > nums[middle + 1]:
                right = middle
            else:
                left = middle + 1
        return left
    
if __name__ == "__main__":
    nums = [1, 2, 1, 3, 5, 6, 4]
    nums2 = [1, 2, 3, 1]
    ans = Solution().findPeakElement(nums2)
    print("ans = ", ans)