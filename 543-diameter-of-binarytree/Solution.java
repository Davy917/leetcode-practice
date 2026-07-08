import Tree.*;
class Solution543 {
    static int ans;
    public static int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public static int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.getLeft()); // 左兒子為根的子樹深度
        int R = depth(node.getRight()); // 右兒子為根的子樹深度
        ans = Math.max(ans, L+R+1); // L+R+1 當前節點的所有子節點數(包含自己)
        return Math.max(L, R) + 1; // 當前節點的最大深度
    }

    static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5};
        TreeNode root = TreeDebugger.buildLevelOrderTree(nums);
        System.out.println("Ans = " + diameterOfBinaryTree(root));
    }
}
/*
建議先看
104-max-depth/solution.py

官解:
https://leetcode.cn/problems/diameter-of-binary-tree/solutions/139683/er-cha-shu-de-zhi-jing-by-leetcode-solution/

自底至頂, 看過每一個節點能連到的直徑是多少, 再把最大的那個返回
*/