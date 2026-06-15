from typing import List
from typing import Optional
class TreeNode:
    def __init__(self, val) -> None:
        self.val = val
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        return self.construct(nums, 0, len(nums)-1)
    def construct(self, nums, left, right):
        if left > right:
            return None
        best = left
        print(f"left = {left}, right = {right}")
        for i in range(left+1, right+1):
            if nums[i] > nums[best]:
                best = i
        node = TreeNode(nums[best])
        node.left = self.construct(nums, left, best-1)
        node.right = self.construct(nums, best+1, right)
        return node
if __name__ == "__main__":
    nums = [3,2,1,6,0,5]
    Solution().constructMaximumBinaryTree(nums)
"""
二叉树的根是数组 nums 中的最大元素。
左子树是通过数组中最大值左边部分递归构造出的最大二叉树。
右子树是通过数组中最大值右边部分递归构造出的最大二叉树。

遞迴樹:
construct(0, 5) -> 6
├─ construct(0, 2) -> 3
│  ├─ construct(0, -1) -> None
│  └─ construct(1, 2) -> 2
│     ├─ construct(1, 0) -> None
│     └─ construct(2, 2) -> 1
│        ├─ construct(2, 1) -> None
│        └─ construct(3, 2) -> None
└─ construct(4, 5) -> 5
   ├─ construct(4, 4) -> 0
   │  ├─ construct(4, 3) -> None
   │  └─ construct(5, 4) -> None
   └─ construct(6, 5) -> None
"""