#自己寫的暴力解
from typing import List
class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        n = len(nums)
        nums.sort()
        min_diff = float('inf')
        diff = 0
        ans = 0
        for l in range(0, n - 2):
            k = l+1
            r = n-1
            t = target - nums[l]
            while k < r:
                Sum = nums[k] + nums[r]
                if Sum < t:
                    diff = abs(Sum - t)
                    k += 1
                elif Sum > t:
                    diff = abs(Sum - t)
                    r -= 1
                else:
                    return nums[l] + Sum
                if min_diff > diff:
                    min_diff = diff
                    ans = nums[l] + Sum
        return ans
if __name__ == "__main__":
    nums = [4,0,5,-5,3,3,0,-4,-5]
    print("Ans = ", Solution().threeSumClosest(nums, -2))

    """
    建議先看過第15題
    015-three-sum/Solution.java

    指針示意圖
    t = -2 -(-5) = 3
     l   k                     r
    -5, -5, -4, 0, 0, 3, 3, 4, 5

     l       k                 r
    -5, -5, -4, 0, 0, 3, 3, 4, 5

    for 迴圈把 l 固定住, 利用 k, r 找 t
    while 迴圈中要盡力找到 nums[k] + nums[r] = t 
    如果沒有找到的話, 也要記錄下最接近的組合並保存到 diff 中
    """