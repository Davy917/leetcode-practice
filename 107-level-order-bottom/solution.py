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
    
"""
內層的迴圈執行次數是len(dq), 但在此迴圈裡面又會去改變dq的長度, 這樣會不會導致迴圈執行不完?

答案：不會
在 Python 中，range(len(dq)) 是在 for 迴圈開始時就被計算一次，之後迴圈會依照當時固定下來的 range 物件來迭代，不會再去重新查詢 len(dq)。

直觀理解
你可以把它想成「快照」的概念：
本層開始 → 快照：len(dq) = 3 → 只處理這 3 個節點
           ↓
迴圈中 → append 子節點 → 這些子節點屬於「下一層」
           ↓
本層結束 → 下一層 while 迴圈重新計算 len(dq) → 新的快照

若你這樣寫，就會有問題（死迴圈）：
while len(dq) > 0:       # 每次都會重新檢查
    root = dq.popleft()
    dq.append(...)       # 長度一直增加，永遠跑不完
"""