from typing import Optional
class TreeNode:
    def __init__(self, val = 0, left = None, right = None):
        self.val = val
        self.left = left
        self.right = right
    def buildTree(self):
        node4 = TreeNode(3, None, None)
        node3 = TreeNode(1, None, None)
        node2 = TreeNode(5, None, None)
        node1 = TreeNode(2, node3, node4)
        root = TreeNode(4, node1, node2)
        return root
    def buildTree2(self):
        node3 = TreeNode(2, None, None)
        root = TreeNode(1, None, node3)
        return root
class Solution:
    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        result = root.val
        while root is not None:
            print(f"result = {result}")
            if abs(root.val - target) < abs(result - target):
                result = root.val
                print("find closer value ", result)
              
            elif abs(root.val - target) == abs(result - target):
                if result > root.val:
                    result = root.val
                    print("find closer value4 ", result)
            root = root.left if root.val > target else root.right
            
        return result
if __name__ == "__main__":
    root = TreeNode().buildTree()
    target = 3.5
    print("Ans = ", Solution().closestValue(root, target))
    root2 = TreeNode().buildTree2()
    target = 3.428571
    print("Ans = ", Solution().closestValue(root2, target))
"""
二叉搜索樹 (Binary Search Tree, BST) 來說，其核心定義：
    1. 左子樹中的所有節點的值都小於根節點的值。
    2. 右子樹中的所有節點的值都大於根節點的值。
    3. 左右子樹本身也必須是二叉搜索樹。
正例:
      50
     /  \
    30   70
   / \   / \
  20 40 60 80

反例:
      50
     /  \
    30   70
   / \   / \
  20 40 25 80  <- 問題在25違反了更上層父節點50, 的全局約束
二分查找的關鍵:
root = root.left if root.val > target else root.right

如果 root.left 已經確定 > target, 那麼再往右邊找也沒有意義了, 因為root.right 只會更大, 離答案更遠
"""
