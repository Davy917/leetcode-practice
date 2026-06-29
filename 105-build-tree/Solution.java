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
        if (preLeft > preRight || inLeft > inRight)
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
官方視頻題解:
04:30開始看
https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solutions/255811/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/

preorder
[preLeft][preLeft + 1, pIndex - inLeft + preLeft][pIndex - inLeft + preLeft + 1, preRight]

inorder
[inLeft, pIndex - 1][pIndex][pIndex + 1, inRight]

[遞迴樹]
buildTree(pre=[3,9,20,15,7], preL=0, preR=4, inL=0, inR=4)
│
├─ root = 3 (preorder[0])
│  pIndex = 1 (inorder中3的位置)
│
├─ 左子树: buildTree(pre, 1, 1, inL=0, inR=0)
│  │
│  ├─ root = 9 (preorder[1])
│  │  pIndex = 0
│  │
│  ├─ 左子树: buildTree(pre, 2, 1, ...) → null (preL > preR)
│  │
│  └─ 右子树: buildTree(pre, 2, 1, ...) → null (preL > preR)
│  │
│  └─ return TreeNode(9)
│
└─ 右子树: buildTree(pre, 2, 4, inL=2, inR=4)
   │
   ├─ root = 20 (preorder[2])
   │  pIndex = 3
   │
   ├─ 左子树: buildTree(pre, 3, 3, inL=2, inR=2)
   │  │
   │  ├─ root = 15 (preorder[3])
   │  │  pIndex = 2
   │  │
   │  ├─ 左: null | 右: null
   │  │
   │  └─ return TreeNode(15)
   │
   └─ 右子树: buildTree(pre, 4, 4, inL=4, inR=4)
      │
      ├─ root = 7 (preorder[4])
      │  pIndex = 4
      │
      ├─ 左: null | 右: null
      │
      └─ return TreeNode(7)
   │
   └─ return TreeNode(20, left=15, right=7)

└─ return TreeNode(3, left=9, right=20)
*/