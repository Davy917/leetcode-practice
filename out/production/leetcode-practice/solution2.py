"""
暴力解 + 二分查找
"""
from typing import List
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        if len(nums1) >= len(nums2):
            longer = nums1
            shorter = nums2
        else:
            longer = nums2
            shorter = nums1

        result = []
        index = 0
        while index < len(shorter):
            target = shorter[index]
            if index > 0 and shorter[index] == shorter[index - 1]:
                index += 1
                continue
            left = 0
            right = len(longer) - 1
            while left <= right:
                middle = left + (right - left) // 2
                if longer[middle] > target:
                    right = middle - 1
                elif longer[middle] < target:
                    left = middle + 1
                else:
                    result.append(target)
                    break
            index += 1
        return result
if __name__ == "__main__":
    nums1 = [1, 2, 2, 1]
    nums2 = [2, 2]
    print(Solution().intersection(nums1, nums2))