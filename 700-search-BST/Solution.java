import Tree.*;
class Solution700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null){
            return null;
        }
        if (root.getVal() > val) {
            return searchBST(root.getLeft(), val);
        }
        else if(root.getVal() < val){
            return searchBST(root.getRight(), val);
        }
        return root;
    }
}