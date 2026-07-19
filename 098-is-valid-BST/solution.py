from typing import Optional
from datastructure  import TreeNode

class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def valid(root, lower, upper):
            if not root:
                return True
            is_left_bst = valid(root.left, lower, root.val)
            is_right_bst = valid(root.right, root.val, upper)
            if root.val <= lower or root.val >= upper:
                return False
            return is_left_bst and is_right_bst
        return valid(root, float('-inf'), float('inf'))