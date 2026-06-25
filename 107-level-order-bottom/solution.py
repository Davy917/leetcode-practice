from typing import Optional
from typing import List
from collections import deque
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def levelOrderBottom(self, root: Optional[TreeNode]) -> List[List[int]]:
        result: List[List[int]] = []
        if root is None:
            return result
        dq: deque[TreeNode] = deque()
        dq.append(root)

        while len(dq) != 0:
            level: List[int] = []
            for i in range(len(dq)):
                root = dq.popleft()
                level.append(root.val)
                if root.left is not None:
                    dq.append(root.left)
                if root.right is not None:
                    dq.append(root.right)
            result.insert(0, level)
        return result