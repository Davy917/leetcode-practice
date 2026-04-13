from typing import Optional
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __str__(self) -> str:#__str__ 改成 __repr__ 也可以

        if self.left is None and self.right is None:
            return str(self.val)
        left_repr = "null" if self.left is None else repr(self.left)
        right_repr = "null" if self.right is None else repr(self.right)
        return f"{self.val}({left_repr},{right_repr})"

class Solution1122:
    def __init__(self):
        self.map: dict[str, TreeNode] = {}
        self.key_set: set[str] = set()
    def visit(self, node: TreeNode):
        if node is None:
            return ""
        
        #設計哈希鍵
        key = f"{node.val}(" + self.visit(node.left) + "," + self.visit(node.right) + ")"
        print("treeNodeKey = ", type(key))


        if key in self.key_set:
            self.map[key] = node
        self.key_set.add(key)

        return key
    
    def findDuplicateSubtrees(self, root: Optional[TreeNode]) -> list[Optional[TreeNode]]:
        self.visit(root)
        return list(self.map.values())#why??

if __name__ == "__main__":
    # Construct the same test tree used in the Java example
    n4a = TreeNode(4)
    n2a = TreeNode(2, n4a, None)
    n4b = TreeNode(4)
    n2b = TreeNode(2, n4b, None)
    n3 = TreeNode(3, n2b, TreeNode(4))
    root = TreeNode(1, n2a, n3)

    sol = Solution1122()
    result = sol.findDuplicateSubtrees(root)
    print("result =", result)
    print("Found", len(result), "duplicate subtree(s):")