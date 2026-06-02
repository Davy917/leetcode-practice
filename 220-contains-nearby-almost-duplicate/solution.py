#扣友提供解法(桶排序)
from typing import List
class Solution:
    def containsNearbyAlmostDuplicate(self, nums: List[int], indexDiff: int, valueDiff: int) -> bool:
        buckets_width = valueDiff + 1
        buckets = {}
        for idx, num in enumerate(nums):
            buckets_idx = num // buckets_width #這種算法保證了, 如果有 Y 與當前 num 匹配的話, 那麼這個Y一定在num這個桶, 或是num相鄰的桶
            if buckets_idx in buckets:
                return True
            if buckets_idx-1 in buckets and abs(num-buckets[buckets_idx-1])<=valueDiff: # num - 前一桶的num
                return True
            if buckets_idx+1 in buckets and abs(num-buckets[buckets_idx+1])<=valueDiff: # num - 後一桶的num
                return True 
            buckets[buckets_idx]=num
            print(f"idx = {idx}, buckets = {buckets}")
            if idx >=indexDiff:
                old_buckets_idx=nums[idx-indexDiff]//buckets_width
                del buckets[old_buckets_idx]
        return False
if __name__ == "__main__":
    nums = [1,5,9,1,5,9] #分別會被裝進[0,1,2,0,1,2]
    print("Ans = ", Solution().containsNearbyAlmostDuplicate(nums, 2, 3))
    """
    扣友提供解法:
    https://leetcode.cn/problems/contains-duplicate-iii/solutions/3973045/hua-dong-chuang-kou-tong-pai-xu-by-you-m-x2o3/
    """