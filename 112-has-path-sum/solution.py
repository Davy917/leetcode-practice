from typing import Optional
from datastructure import TreeNode
from datastructure import TreeDebugger

class Solution:
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if root is None:
            return False
        return self.recursion(root, targetSum, root.val)
    def recursion(self, root: Optional[TreeNode], targetSum: int, curSum: int):
        if root.left is None and root.right is None:
            if curSum == targetSum:
                return True
            else:
                return False
        if root.left is None:
            return self.recursion(root.right, targetSum, curSum + root.right.val)
        if root.right is None:
            return self.recursion(root.left, targetSum, curSum + root.left.val)
        return self.recursion(root.left, targetSum, curSum + root.left.val) or self.recursion(root.right, targetSum, curSum + root.right.val)

if __name__ == "__main__":
    nums = [1, 2, None]
    r = TreeDebugger().build_levelorder_tree(nums)
    print("Ans = ", Solution().hasPathSum(r, 0))

"""
四種情境:
左右皆無
左有
右有
左右皆有

迭代 BFS版本看golang
112-has-path-sum/solution.go

這題是先把go的版本寫出來之後, 再改成 python 迭代的
"""