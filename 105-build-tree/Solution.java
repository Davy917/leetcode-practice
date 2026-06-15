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
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //儲存中序遍歷的val, index
        var map = new HashMap<Integer, Integer>(preorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        System.out.println(map);
        return buildTree(preorder, 0, preorder.length-1, map, 0, inorder.length-1);
    }
    private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                               HashMap<Integer, Integer> map, int inLeft, int inRight)
    {
        if (preLeft > preRight || inLeft > inRight) //遞歸終止條件
            return null;
        int rootVal = preorder[preLeft];
        var root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);
        System.out.printf("rootVal = %d, pIndex = %d\n", rootVal, pIndex);
        root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex-1);
        root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
        return root;
    }
    static void main(String[] args) {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        Solution105 sol = new Solution105();
        sol.buildTree(preOrder, inOrder);
    }
}
/*
输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]

前序遍历：遍历顺序为 父节点 -> 左子节点 -> 右子节点
后续遍历：遍历顺序为 左子节点 -> 父节点 -> 右子节点

官方視頻題解:
04:30開始看
https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solutions/255811/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/

遞迴樹:
build(pre[0..4], in[0..4])  root=3
├─ left:  build(pre[1..1], in[0..0])  root=9
│  ├─ left:  build(pre[2..1], in[0..-1]) -> null
│  └─ right: build(pre[2..1], in[1..0])  -> null
└─ right: build(pre[2..4], in[2..4])  root=20
   ├─ left:  build(pre[3..3], in[2..2])  root=15
   │  ├─ left:  build(pre[4..3], in[2..1]) -> null
   │  └─ right: build(pre[4..3], in[3..2]) -> null
   └─ right: build(pre[4..4], in[4..4])  root=7
      ├─ left:  build(pre[5..4], in[4..3]) -> null
      └─ right: build(pre[5..4], in[5..4]) -> null
*/