//官方解答
import Tree.*;
class Solution606 {
    static StringBuilder sb = new StringBuilder();
    public static String tree2str(TreeNode root) {
        if (root == null)
            sb.append("()");
        else{
            sb.append(root.getVal());
            tree2str(root.getLeft());
            tree2str(root.getRight());
        }
        System.out.println(sb.toString());
        return null;
    }

    static void main(String[] args) {
        Integer[] nums = {1,2, 4, null, 3, null, null};
        var node = TreeDebugger.buildTree(nums);
        tree2str(node);
    }
}
/*
https://leetcode.cn/problems/construct-string-from-binary-tree/solutions/1343920/gen-ju-er-cha-shu-chuang-jian-zi-fu-chua-e1af/
 */