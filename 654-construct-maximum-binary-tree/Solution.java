//官方解答
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}
class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length-1);
    }
    public TreeNode construct(int[] nums, int left, int right){
        if (left > right){
            return null;
        }
        int best = left;
        for (int i = left+1; i <= right; i++)
            if (nums[i] > nums[best])
                best = i;
        TreeNode node = new TreeNode(nums[best]);
        node.left = construct(nums, left, best-1);
        node.right = construct(nums, best+1, right);
        return node;
    }
    static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        Solution654 sol = new Solution654();
        sol.constructMaximumBinaryTree(nums);
    }
}
/*
官方解答:
https://leetcode.cn/problems/maximum-binary-tree/solutions/1759348/zui-da-er-cha-shu-by-leetcode-solution-lbeo/
 */