from datastructure import TreeNode
class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        if not root or root == p or root == q: return root
        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)
        if not left and not right: return
        if not left: return right
        if not right: return left
        return root

"""
k神解答:
https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solutions/240096/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/

[FAQ]
從底至頂回溯的代碼模板是不是把判斷式或等式寫在遞歸式(7,8行)的下方?

是的，這也是後序遍歷（post-order） 的遞迴模式

因為從底至頂的意思是：

先一路遞迴到葉子節點（最底部）
觸發終止條件，開始一層層往上返回
每一層拿到 left 和 right 的結果後，才做判斷

總結:
「從底至頂回溯」= 後序遍歷 = 先遞迴到底，再在回傳的路上做判斷，所以判斷式自然寫在遞迴式的下方。
"""