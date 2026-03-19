import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
        if (nums == null || nums.length == 0 || nums[0] == null) {
            root = null;
            return null;
        }

        root = new TreeNode(nums[0], null, null);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        int i = 1; // next index in nums
        while (!q.isEmpty() && i < nums.length) {
            TreeNode cur = q.poll();

            // left child
            if (i < nums.length && nums[i] != null) {
                cur.left = new TreeNode(nums[i], null, null);
                q.offer(cur.left);
            }
            i++;

            // right child
            if (i < nums.length && nums[i] != null) {
                cur.right = new TreeNode(nums[i], null, null);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }
}

class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null){
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        TreeNode cur = root;
        while (cur.right != null){

            ans.add(cur.val);
        }
        return ans;
    }

    static void main(String[] args) {
        Solution144 sol = new Solution144();
        Integer[] nums = {1,2,3,4,5,null,8,null,null,6,7,9};
        Integer[] nums2 = {1,null,2,3};
        Tree mainTree = new Tree();
        mainTree.builtBinaryTree(nums2);
        sol.preorderTraversal(mainTree.root);
    }
}