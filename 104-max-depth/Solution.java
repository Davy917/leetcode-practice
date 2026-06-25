import Tree.TreeNode;
import Tree.TreeDebugger;

class Solution104 {
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = maxDepth(root.getLeft()) + 1;
        int rightDepth = maxDepth(root.getRight()) + 1;
        return Math.max(leftDepth, rightDepth);
    }
    static void main(String[] args) {
        Integer[] nums = {3,9,20,null,null,15,7};
        TreeNode root = TreeDebugger.buildLevelOrderTree(nums);
        System.out.println("Ans = " + maxDepth(root));
    }
}

/*
自底向上:
https://leetcode.cn/leetbook/read/data-structure-binary-tree/xefb4e/
 */