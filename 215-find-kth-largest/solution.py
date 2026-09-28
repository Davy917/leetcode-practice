import heapq
from typing import List
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        min_heap = []
        heapq.heapify(min_heap)
        for num in nums:
            heapq.heappush(min_heap, -num)
        for i in range(k):
            result = -heapq.heappop(min_heap)
            if i == k-1:
                return result
        return -1

if __name__ == "__main__":
    # nums = [3, 2, 1, 5, 6, 4]
    nums = [3,2,3,1,2,4,5,5,6]
    print("Ans = ", Solution().findKthLargest(nums, 4))

"""
這題要補練習--->手寫堆排序
"""