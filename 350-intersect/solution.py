from typing import List
class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        dict = {}
        for value in nums1:
            dict[value] = dict.setdefault(value, 0) + 1
        
        result = []
        for value in nums2:
            if value in dict and dict[value] != 0:
                result.append(value)
                dict[value] -= 1
        print(result)
        return result
if __name__ == "__main__":
    nums1 = [1, 2, 2, 1, 8, 9]
    nums2 = [2, 2]
    sol = Solution()
    sol.intersect(nums1, nums2)