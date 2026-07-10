from typing import List
from typing import Optional
from Tree import TreeNode
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        num = []
        def dfs(node: Optional[TreeNode]):
            if node is None:
                return
            dfs(node.left)
            num.append(node.val)
            dfs(node.right)
        dfs(root)
        return num