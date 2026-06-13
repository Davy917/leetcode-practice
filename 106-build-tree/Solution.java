import java.util.HashMap;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        System.out.println(map);
        return buildTree(postorder, 0, postorder.length - 1, map, 0, inorder.length - 1);
    }
    private TreeNode buildTree(int[] postorder, int postLeft, int postRight,
                               HashMap<Integer, Integer> map, int inLeft, int inRight)
    {
        if (postLeft < postRight || inLeft < inRight)
            return null;
        int rootVal = postorder[postRight];
        var root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);
        System.out.printf("rootVal = %d, pIndex = %d\n", rootVal, pIndex);

    }
    static void main(String[] args) {
        int[] postOrder = {9, 15, 7, 20, 3};
        int[] inOrder = {9, 3, 15, 20, 7};
        Solution106 sol = new Solution106();
        sol.buildTree(inOrder, postOrder);
    }
}
/*
inOrder:
9 3 15 20 7
l    p    r
postOrder:
9 15 7 20 3
          p

postRight = ??
postRight =
*/