"""
第一次練習java
第二次練習含思路python
"""
class Solution:
    def searchRange(self, nums: list[int], target: int) -> list[int]:
        res = [-1, -1]
        if not nums:
            return res
        l, r = 0, len(nums)
        while l+1<r:
            m = (l+r)//2
            if nums[m] > target:
                r = m
            else:
                l = m
        if nums[l] == target:
            res[-1] = l
        l, r = -1, len(nums)-1
        while l+1<r:
            m = (l+r)//2
            if nums[m] < target:
                l = m
            else:
                r = m
        if nums[r] == target:
            res[0] = r
        return res

if __name__ == "__main__":
    nums = [5,6,6,7,7,7,8,8,8,9]
    print("Ans = ", Solution().searchRange(nums, 6))

"""
自己寫的
思路, 二分查找:
要找頭尾,並返回 res[頭, 尾] 大概不能同時進行, 分開找先找 res[尾]

假設:
nums = [5,6,6,7,7,7,8,8,8,9], target = 6
l, r = 0, len(nums)

l, 當前下標可能是答案 -> 答案在l ~ r之間
r, 當前下標, 包括r ~ 最後一位皆不是答案

三種情境:
nums[m] > target
    r移動到m
nums[m] < target
    l移動到m+1
nums[m] == target
    l移動到m

再簡化:
nums[m] > target
    r = m
else
    l = m
簡化的好處是不用m+1, m+1可能會溢位

補充:
找res[0]的邏輯是一樣的, 只是l, r的腳色互換而已
"""