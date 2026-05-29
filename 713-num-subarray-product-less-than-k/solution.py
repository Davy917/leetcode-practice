#官方解答
from typing import List
class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        ans = 0
        prod = 1
        l = 0
        for r, num in enumerate(nums):
            prod *= num
            while l <= r and prod >= k:
                prod //= nums[l]
                l += 1
            ans += r-l+1 #請看下方說明
        return ans
if __name__ == "__main__":
    nums = [10,5,2,6]
    k = 100
    print("Ans = ", Solution().numSubarrayProductLessThanK(nums, k))

    """
    為什麼第12行 ans 不斷的 r-l+1 就能找到乘積小於k的子數組數量

    数组nums： 10, 5, 2, 6
    窗口1(r=2)：   l  r   
    窗口1(r=3):    l     r
    窗口1中符合的有[5],[2],[5,2]
    窗口2中符合的有[5],[2],[5,2],[6],[2,6],[5,2,6]


    對比 r=2, r=3, 你會發現新出現的子陣列是
    [6],[2,6],[5,2,6]

    他們的共通點是全部都包含最右邊的元素也就是 6

    在程式碼中，我們每一輪迴圈只做一次 ans += r - l + 1。  
    r-l+1 = 2-1+1 = 2
    我們只增加了以 nums[2] 為結尾的子陣列：[2] 和 [5, 2]。
    r-l+1 = 3-1+1 = 3
    我們只增加了以 nums[3] 為結尾的子陣列：[6], [2, 6], [5, 2, 6]。
    「多出來的」=「新增加的」=「以 r 為結尾的」。 數量剛好就是窗口的長度：r - l + 1。  

    官方解答:
    https://leetcode.cn/problems/subarray-product-less-than-k/solutions/1463527/cheng-ji-xiao-yu-k-de-zi-shu-zu-by-leetc-92wl/
    """