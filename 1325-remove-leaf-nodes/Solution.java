import Tree.*;
class Solution1325 {
    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null)
            return null;
        root.setLeft(removeLeafNodes(root.getLeft(), target));
        root.setRight(removeLeafNodes(root.getRight(), target));
        if (root.getVal() == target && root.getLeft() == null && root.getRight() == null)
            return null;
        return root;
    }
    static void main(String[] args) {
        Integer[] levelOrder = {1,2,3,2,null,2,4};
        TreeNode root = TreeDebugger.buildLevelOrderTree(levelOrder);
        System.out.println("Ans = " + removeLeafNodes(root, 2));
    }
}