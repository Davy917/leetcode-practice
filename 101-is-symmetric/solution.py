#K神題解
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).parent.parent))
from typing import Optional
from datastructure  import TreeNode
from datastructure import TreeDebugger

class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if root is None: #基本不會走到這裡
            return False
        return self.recursion(root.left, root.right)
    def recursion(self, L: Optional[TreeNode], R: Optional[TreeNode]) -> bool:
        if not L and not R:
            return True
        elif (not L or not R) or (L.val != R.val):
            return False
        return self.recursion(L.left, R.right) and self.recursion(L.right, R.left)
if __name__ == "__main__":
    nums = [1,2,2,3,4,4,3]
    root = TreeDebugger().build_levelorder_tree(nums)
    print("Ans = ", Solution().isSymmetric(root))

    """
    官解
    https://leetcode.cn/problems/symmetric-tree/solutions/268109/dui-cheng-er-cha-shu-by-leetcode-solution/
    K神題解
    https://leetcode.cn/problems/symmetric-tree/solutions/2361627/101-dui-cheng-er-cha-shu-fen-zhi-qing-xi-8oba/

    兩個版本都看完, 再用自己的理解寫一遍
    
    
    recursion(2, 2)  ← 初始调用
        ├─ recursion(3, 3)  ← L.left vs R.right
        │   ├─ recursion(None, None) → True
        │   └─ recursion(None, None) → True
        │   └─ 返回 True
        │
        └─ recursion(4, 4)  ← L.right vs R.left
            ├─ recursion(None, None) → True
            └─ recursion(None, None) → True
            └─ 返回 True
        └─ 返回 True (True AND True)

    相關題目
    100-is-same-tree
    """