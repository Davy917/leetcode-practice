import java.util.List;

class TreeNode {
    int val;
    TreeNode right;
    TreeNode left;
    TreeNode(int val,TreeNode left, TreeNode right){
        this.val = val;
        this.right = right;
        this.left = left;
    }
}
class Tree{
    TreeNode root = null;
    TreeNode builtBinaryTree(Integer[] nums){
        if (nums[0] == null){return null;}
        root = new TreeNode(nums[0], null, null);
        //TODO
        return root;
    }
}

class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {

    }

    static void main(String[] args) {
        Solution144 sol = new Solution144();
        Integer[] nums = {1,2,3,4,5,null,8,null,null,6,7,9};

        Tree mainTree = new Tree();
        mainTree.builtBinaryTree(nums);
        //sol.preorderTraversal(mainTree.root);
    }
}