//官方解答
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
        if (postLeft > postRight || inLeft > inRight)
            return null;
        int rootVal = postorder[postRight];
        var root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);
        System.out.printf("rootVal = %d, pIndex = %d\n", rootVal, pIndex);
        root.left = buildTree(postorder, postLeft, postLeft + pIndex - inLeft - 1, map, inLeft, pIndex-1);
        root.right = buildTree(postorder, postLeft + pIndex - inLeft, postRight - 1, map, pIndex+1, inRight);
        return root;
    }
    static void main(String[] args) {
        int[] postOrder = {9, 15, 7, 20, 3};
        int[] inOrder = {9, 3, 15, 20, 7};
        Solution106 sol = new Solution106();
        sol.buildTree(inOrder, postOrder);
    }
}
/*
preorder:
左子樹節點數 = pIndex - inLeft
左子樹範圍:
[postLeft, postLeft + 左子樹節點數 - 1]
右子樹範圍:
[postLeft + 左子樹節點數, postRight - 1]
*/