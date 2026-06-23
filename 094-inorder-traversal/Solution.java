import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}
class Solution94 {
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null)
            return list;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }
}
/*
输入：root = [1,null,2,3]
输出：[1,3,2]

遞迴樹:
inorderTraversal(root=1)
│
├─ inorderTraversal(root.left=null)
│  └─ (返回，无操作)
│
├─ list.add(1)  →  list = [1]
│
└─ inorderTraversal(root.right=2)
   │
   ├─ inorderTraversal(2.left=3)
   │  │
   │  ├─ inorderTraversal(3.left=null)
   │  │  └─ (返回，无操作)
   │  │
   │  ├─ list.add(3)  →  list = [1, 3]
   │  │
   │  └─ inorderTraversal(3.right=null)
   │     └─ (返回，无操作)
   │
   ├─ list.add(2)  →  list = [1, 3, 2]
   │
   └─ inorderTraversal(2.right=null)
      └─ (返回，无操作)
      
總結精隨:
可以理解成, 一直往左邊走, 走到不能再走的時候才開始做list.add()
*/