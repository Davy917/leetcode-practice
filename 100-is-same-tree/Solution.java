import Tree.*;

//官方解答
class Solution100 {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.getVal() != q.getVal()) {
            return false;
        } else {
            return isSameTree(p.getLeft(), q.getLeft()) && isSameTree(p.getRight(), q.getRight());
        }
    }

    static void main(String[] args) {
        Integer[] p = {1, 2, 1};
        Integer[] q = {1, 1, 2};
        var pNode = TreeDebugger.buildTree(p);
        var qNode = TreeDebugger.buildTree(q);
        isSameTree(pNode, qNode);
    }
}
/*
buildTree, 來自TreeNode
datastructure/Tree/BuildTree.java

https://leetcode.cn/problems/same-tree/solutions/363636/xiang-tong-de-shu-by-leetcode-solution/
*/