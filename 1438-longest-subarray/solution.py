from typing import List
from collections import deque
class Solution:
    #暴力解超時, 試著優化這個解法
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        n = len(nums)
        max_len, cur_len = 0, 0
        max_num, min_num = nums[0], nums[0]
        l = 0
        for r in range(n):
            cur_len += 1
            max_num = max(max_num, nums[r])
            min_num = min(min_num, nums[r])
            while max_num - min_num > limit:
                l += 1
                cur_len -= 1
                max_num = max(nums[l:r+1])
                min_num = min(nums[l:r+1])
            max_len = max(max_len, cur_len)
        return max_len
    #優化後
    def longestSubarray_v2(self, nums: List[int], limit: int) -> int:
        n = len(nums)
        max_len, cur_len = 0, 0
        dq_min, dq_max = deque(), deque()
        l = 0
        for r in range(n):
            while dq_max and nums[r] > nums[dq_max[-1]]:
                dq_max.pop()
            dq_max.append(r)
            while dq_min and nums[r] < nums[dq_min[-1]]:
                dq_min.pop()
            dq_min.append(r)
            print(f"dq_max = {dq_max}, dq_min = {dq_min}")
            while nums[dq_max[0]] - nums[dq_min[0]] > limit:
                print(">limit")
                l += 1
                if l < n and dq_min[0] < l:
                    dq_min.popleft()
                if l < n and dq_max[0] < l:
                    dq_max.popleft()
                print(f"l = {l}, r = {r}")
            cur_len = r-l+1
            max_len = max(max_len, cur_len)
        return max_len
if __name__ == "__main__":
    nums = [1,5,6,7,8,10,6,5,6]
    limit = 4
    print("Ans = ", Solution().longestSubarray_v2(nums, limit))

    """
    思考軌跡:
    1438-longest-subarray\leetcode1438-footprint.md
    
    優化後的代碼更像是, leetcode官解 滑动窗口 + 单调队列
    https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/612688/jue-dui-chai-bu-chao-guo-xian-zhi-de-zui-5bki/
    """