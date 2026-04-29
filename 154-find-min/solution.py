from typing import List
class Solution:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        while left < right:
            middle = left + (right - left) // 2
            print(f"left = {left}, right = {right}, middle = {middle}")
            if nums[middle] > nums[right]:
                left = middle + 1
            elif nums[middle] < nums[right]:
                right = middle
            elif nums[middle] == nums[right]:
                right -= 1

        print("pivot = ", left)
        return nums[left]

if __name__ == "__main__":
    nums = [4, 5, 6, 7, 1, 2, 3, 4]
    nums2 = [3, 3, 1, 3]
    nums3 = [1, 3, 3]
    sol = Solution()
    ans = sol.findMin(nums3)
    print("ans = ", ans)
    """
    力扣視頻題解:
    07:50開始
    https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
    """
    """
    找pivot
    1, 0
    0, 1
    """