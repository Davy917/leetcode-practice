#二分查找
from typing import List
class Solution1122:
    def findMin(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        min = float('inf')

        while left <= right:

            mid = left + (right - left) // 2
            print("mid = ", mid)
            if nums[mid] < min:
                min = nums[mid]
                print("min value= ", min)

            if nums[mid] > nums[-1]:
                left = mid + 1
                print("left = ", left)
            else:
                right = mid - 1
                print("right = ", right)

        return min

print(Solution1122().findMin([3,1,2]))

"""
mid = 2
min value = 3
right = 2

mid = 1
min value = 2
right = 1

mid = 0
min value = 1
right = 0
------------------
mid = 2
min value = 3
right = 1

mid = 0
min value = 1
right = -1


输入：nums = [4,5,6,8,9,10,11,0,1,2]
输出：0
解释：原数组为 [8,7,0,1,2,4,5,6] ，旋转 4 次得到输入数组。

输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。

最小的樹會有一個特殊現象, 它的左邊會比它大
        left = 0
        right = len(nums) - 1
        mid = (right + left) // 2
        print("mid = ", nums[mid])
        if(nums[0] < nums[-1]):
            return nums[0]

        while nums[mid-1] < nums[mid]:
            left = mid
            mid = (left + right) // 2
            print("mid = ", nums[mid])


        return nums[mid]
"""
