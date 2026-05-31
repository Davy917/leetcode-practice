#一樣是239題, 放一個python版本
from collections import deque
from typing import List
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        dq = deque()
        for r in range(k):
            while dq and nums[r] >= nums[dq[-1]]: #注意這裡是while迴圈, 寫成if的話底下case會報錯
                dq.pop()
            dq.append(r)
            r += 1
        print(dq)
        n = len(nums)
        ans = [0] * (n-k+1)
        ans[0] = nums[dq[0]]
        for r in range(k, n):
            while dq and nums[r] >= nums[dq[-1]]:
                dq.pop()
            dq.append(r)

            while dq[0] <= r-k:
                dq.popleft()
            ans[r-k+1] = nums[dq[0]]
        return ans
    
if __name__ == "__main__":
    nums = [4,3,11]
    k = 3
    print("Ans = ", Solution().maxSlidingWindow(nums, k))

"""
deque 快速教學
https://www.youtube.com/watch?v=34SNQHapJYE

參考代碼:
239-max-sliding-window\Solution.java

java vs python:
檢視deque最後一個元素
deque.peekLast()
dq[-1]
"""