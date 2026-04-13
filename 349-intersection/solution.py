from typing import List
class Solution1122:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1_set = set()        
        intersect = []
        for i in nums1:
            nums1_set.add(i)
            #print(nums1_set)

        for i in nums2:
            if i in nums1_set:
                intersect.append(i)
                nums1_set.discard(i)
        return intersect

nums1 = [4,9,5]
nums2 = [9,4,9,8,4]
print(Solution1122().intersection(nums1, nums2))

"""
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[9,4]
解释：[4,9] 也是可通过的
"""