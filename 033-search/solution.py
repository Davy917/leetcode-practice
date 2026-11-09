"""
思路看go
優化看python
"""
class Solution:
    def search(self, nums: list[int], target: int) -> int:
        l, r = 0, len(nums)-1
        while l <= r:
            m = (l+r)//2
            if nums[m] == target:
                return m
            if nums[m] >= nums[l]:
                if nums[l] <= target < nums[m]:
                    r = m - 1
                else:
                    l = m + 1
            else:
                if nums[m] < target <= nums[r]:
                    l = m + 1
                else:
                    r = m - 1
        return -1
if __name__ == "__main__":
    nums = [6,7,8,0,1,2,3,4,5]
    print("Ans = ", Solution().search(nums, 0))

"""
自己寫的
目標:
把go版本再精簡

再深入思考:
nums[m] >= nums[l], 代表m跟l是一組的, 代表l ~ m已經排好
nums[m] < nums[l], 代表m跟r是一組的, 代表m ~ r已經排好

代碼結構:
target == nums[m] return m
nums[m] >= nums[l], 代表l ~ m已經排好
    nums[l] <= target < nums[m] 往左找
    其它情況全部往右找
nums[m] < nums[l], 代表m ~ r已經排好
    nums[m] < target <= nums[r] 往右找
    其它情況全部往左找

這版的邏輯:
數組一定長這樣: l ~ m ~ r
先知道l ~ m排好, 還是 m ~ r排好:
    l ~ m排好:
        nums[l] <= target < nums[m]
        如果target不在上述區間, 只可能在 m ~ r區間, 往右走
    m ~ r排好:
        nums[m] > target >= nums[r]
        如果target不在上述區間, 只可能在 l ~ m區間, 往左走
"""