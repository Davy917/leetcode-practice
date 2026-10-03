#堆排序進階版
from typing import List
class Solution:
    def findKthsmallest(self, nums: List[int], k: int) -> int:

        def heapify(nums: List[int], n: int, parent: int):
            if parent >= n:
                return
            left_child = parent * 2 + 1
            right_child = parent * 2 + 2
            smallest = parent
            if left_child < n and nums[left_child] < nums[smallest]:
                smallest = left_child
            if right_child < n and nums[right_child] < nums[smallest]:
                smallest = right_child
            if smallest != parent:
                nums[parent], nums[smallest] = nums[smallest], nums[parent]
                heapify(nums, n, smallest)

        def build_min_heap(nums: List[int]):
            last_node = len(nums) - 1
            last_parent = (last_node - 1) // 2
            for i in range(last_parent, -1, -1):
                heapify(nums, k, i)

        compare = nums[k:]
        nums = nums[:k]
        build_min_heap(nums)
        for num in compare:
            if num > nums[0]:
                nums[0] = num
                heapify(nums, k, 0)
        return nums[0]

if __name__ == "__main__":
    nums = [3, 2, 1, 5, 6, 4]
    print("Ans = ", Solution().findKthsmallest(nums, 2))
"""
前 k 個元素 → 建最小堆
之後每個元素：
  若 > 堆頂 → 替換堆頂，sift down
最後堆頂就是答案

堆裡永遠只存「目前為止最大的 k 個」
→ 最小的那個（堆頂）就是第 k 大
→ 比堆頂小的元素，不可能進前 k 大，直接丟掉
"""