import Tree.*;
class Solution129 {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
    void dfs(TreeNode root,int pathSum){
        pathSum += root.getVal();
        if (root.getLeft() == null && root.getRight() == null){
            sum += pathSum;
            return;
        }
        if (root.getLeft() != null)
            dfs(root.getLeft(), pathSum * 10);
        if (root.getRight() != null)
            dfs(root.getRight(), pathSum * 10);
    }

    static void main(String[] args) {
        Integer[] levelOrder = {4,9,0,5,1};
        TreeNode root = TreeDebugger_v2.buildLevelOrderTree_v2(levelOrder);
        Solution129 sol = new Solution129();
        System.out.println("Ans = " + sol.sumNumbers(root));
    }
}
//自己寫的