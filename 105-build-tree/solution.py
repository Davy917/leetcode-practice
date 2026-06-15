from typing import List
from typing import Optional
class TreeNode:
    def __init__(self, val, left, right) -> None:
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        map = {}
        for i, v in enumerate(inorder):
            map[v] = i
        print(map)
        return self.myBuildTree(preorder, 0, len(preorder)-1, map, 0, len(inorder)-1)
    def myBuildTree(self, preorder, preLeft, preRight, map, inLeft, inRight):
        if preLeft > preRight or inLeft > inRight:
            return None
        rootVal = preorder[preLeft]
        root = TreeNode(rootVal, None, None)
        pIndex = map[rootVal]
        root.left = self.myBuildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1)
        root.right = self.myBuildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight)
        return root

if __name__ == "__main__":
    preorder = [3, 9, 20, 15, 7]
    inorder = [9, 3, 15, 20, 7]
    Solution().buildTree(preorder, inorder)
"""
FAQ:
左子樹的右邊界 pIndex - inLeft + preLeft 怎麼來的 ?

假設 x 為 preorder 左子樹的右邊界
x - (preLeft + 1) = pIndex - 1 - inLeft
x = pIndex - inLeft + preLeft

preorder:
[preLeft][preLeft + 1, pIndex - inLeft + preLeft] [pIndex - inLeft + preLeft + 1, preRight]

inorder:
[inLeft, pIndex - 1][pIndex][pIndex + 1, inRight]
"""