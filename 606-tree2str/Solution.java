//官方解答
import Tree.*;
class Solution606 {
    public static String tree2str(TreeNode root) {
        if (root == null)
            return "";
        if (root.getLeft() == null && root.getRight() == null)
            return Integer.toString(root.getVal());
        if (root.getRight() == null)
            return root.getVal() + "(" + tree2str(root.getLeft()) + ")";
        //涵蓋了右有, 以及左右皆有
        return root.getVal() + "(" + tree2str(root.getLeft()) + ")(" + tree2str(root.getRight()) + ")";
    }
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 4, null, 3, null, null};
        var node = TreeDebugger.buildTree(nums);
        System.out.println("Ans = " + tree2str(node));
    }
}
/*
https://leetcode.cn/problems/construct-string-from-binary-tree/solutions/1343920/gen-ju-er-cha-shu-chuang-jian-zi-fu-chua-e1af/
 */