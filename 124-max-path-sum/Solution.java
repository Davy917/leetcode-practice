//非官方解法, 建議先看 543 題
import Tree.*;
class Solution124 {
    static int maxSum;
    public static int maxPathSum(TreeNode root) {
        maxSum = root.getVal();
        dfs(root);
        return maxSum;
    }
    public static int dfs(TreeNode root){
        if (root == null)
            return 0;
        int leftSum = dfs(root.getLeft());
        int rightSum = dfs(root.getRight());
        if (leftSum < 0 && rightSum < 0) {
            maxSum = Math.max(maxSum, root.getVal());
            return root.getVal();
        }
        else if (leftSum < 0){
            maxSum = Math.max(maxSum, rightSum + root.getVal());
            return rightSum + root.getVal();
        }
        else if (rightSum < 0) {
            maxSum = Math.max(maxSum, leftSum + root.getVal());
            return leftSum + root.getVal();
        }
        else{
            maxSum = Math.max(maxSum, leftSum + rightSum + root.getVal());
            return Math.max(leftSum, rightSum) + root.getVal();
        }
    }
    static void main(String[] args) {
        Integer[] nums = {-10,9,20,null,null,15,7};
        TreeNode root = TreeDebugger.buildLevelOrderTree(nums);
        System.out.println("Ans = " + maxPathSum(root));
    }
}
/*
建議先看過:
543-diameter-of-binarytree/Solution.java

思路:
自底至頂看過每個節點的路徑和, 再返回最大的那個

細節:
考慮到每種情境
leftSum < 0 && rightSum < 0 捨棄兩邊, 留root
leftSum < 0                 捨棄左邊
rightSum < 0                捨棄右邊
leftSum > 0 && rightSum > 0 選大的那一邊

參考 543題思路後自己慢慢調整出來的
*/
