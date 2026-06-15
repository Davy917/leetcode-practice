from typing import List
from typing import Optional
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        map = {}
        for i, v in enumerate(inorder):
            map[v] = i
        print(map)
        return self.myBuildTree(postorder, 0, len(postorder) - 1, map, 0, len(inorder) - 1)
    def myBuildTree(self, postorder, postLeft, postRight, map, inLeft, inRight):
        if(postLeft > postRight or inLeft > inRight):
            return None
        rootVal = postorder[postRight]
        root = TreeNode(rootVal, None, None)
        pivotIndex = map[rootVal]
        root.left = self.myBuildTree(postorder, postLeft, pivotIndex - inLeft + postLeft - 1, map, inLeft, pivotIndex-1)
        root.right = self.myBuildTree(postorder, pivotIndex - inLeft + postLeft, postRight - 1, map, pivotIndex + 1, inRight)
        return root

if __name__ == "__main__":
    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]
    Solution().buildTree(inorder, postorder)

    """
    inorder:
    [inLeft, pivotIndex - 1][pivotIndex][pivotIndex + 1, inRight]
    
    postorder:
    [postLeft, x][x + 1, postRight - 1][postRight]
    [postLeft, pivotIndex - inLeft + postLeft - 1][pivotIndex - inLeft + postLeft, postRight - 1][postRight]

    postorder左子樹右邊界 x
    x - postLeft = pivotIndex - 1 - inLeft
    x = pivotIndex - inLeft + postLeft - 1
    """