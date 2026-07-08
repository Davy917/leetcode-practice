#K神題解
import sys
from pathlib import Path
sys.path.insert(0, str(Path(__file__).parent.parent))
from typing import Optional
from datastructure  import TreeNode
from datastructure import TreeDebugger

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        left_depth = self.maxDepth(root.left) + 1
        right_depth = self.maxDepth(root.right) + 1
        return max(left_depth, right_depth)

    def maxDepth_v2(self, root: Optional[TreeNode]) -> int:
        def dfs(root, depth):
            if root is None:
                return len
            left_depth = dfs(root.left, depth + 1)
            right_depth = dfs(root.right, depth + 1)
            return max(left_depth, right_depth)
        return dfs(root, 0)

if __name__ == "__main__":
    nums = [3,9,20,None,None,15,7]
    r = TreeDebugger().build_levelorder_tree(nums)
    print("Ans = ", Solution().maxDepth(r))
"""
maxDepth        自底向上
maxDepth_v2     自頂向下
"""