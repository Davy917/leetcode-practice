from typing import List
class Solution:
    def fourSumCount(self, nums1: List[int], nums2: List[int], nums3: List[int], nums4: List[int]) -> int:
        result = 0
        map: dict[int, int] = {}
        for i in nums1:
            for j in nums2:
                #設計哈希鍵
                headSum = i + j
                count = map.get(headSum, 0)
                map[headSum] = count + 1

        for i in nums3:
            for j in nums4:
                tailSum = i + j
                result += map.get(-tailSum, 0)
        return result
if __name__ == "__main__":
    nums1 = [1, 2]
    nums2 = [-2, -1]
    nums3 = [-1, 2]
    nums4 = [0, 2]
    print(Solution().fourSumCount(nums1, nums2, nums3, nums4))


"""
输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
"""