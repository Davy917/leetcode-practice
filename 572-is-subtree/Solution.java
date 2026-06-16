import Tree.*;

class Solution572 {
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null)
            return true;
        else if (root == null || subRoot == null)
            return false;
        else if (root.getVal() != subRoot.getVal())
            return isSubtree(root.getLeft(), subRoot) || isSubtree(root.getRight(), subRoot);
        else
            return isSameTree(root, subRoot) || isSubtree(root.getLeft(), subRoot) || isSubtree(root.getRight(), subRoot);
    }
    public static boolean isSameTree(TreeNode root, TreeNode subRoot){
        if (root == null && subRoot == null)
            return true;
        else if (root == null || subRoot == null)
            return false;
        else if (root.getVal() != subRoot.getVal())
            return false;
        else
            return isSameTree(root.getLeft(), subRoot.getLeft()) && isSameTree(root.getRight(), subRoot.getRight());
    }
    static void main(String[] args) {
        /*
        TestCase1:
        Integer[] root = {3, 4, 1, null, null, 2, null, null, 5, null, null};
        Integer[] subRoot = {4, 1, null, null, 2, null, null};
         */
        //TestCase2:
        Integer[] root = {1, 1, null};
        Integer[] subRoot = {1, null, null};
        var rootNode = TreeDebugger.buildTree(root);
        var subRootNode = TreeDebugger.buildTree(subRoot);
        System.out.println("Ans = " + isSubtree(rootNode, subRootNode));
    }
}
/*
先看完這一題
100-is-same-tree/Solution.java
遞迴心法
572-is-subtree\Tree遞迴心法.md

root:        subRoot:
    3            4
   / \          / \
  4   5        1   2
 / \
1   2

遞迴樹:
isSubtree(3, 4)
├─ isSubtree(4, 4)
│  ├─ isSameTree(4, 4)
│  │  ├─ isSameTree(1, 1)
│  │  │  ├─ isSameTree(null, null) -> true
│  │  │  └─ isSameTree(null, null) -> true
│  │  │  => true
│  │  └─ isSameTree(2, 2)
│  │     ├─ isSameTree(null, null) -> true
│  │     └─ isSameTree(null, null) -> true
│  │     => true
│  │  => true
│  ├─ isSubtree(1, 4)   // 因為前面 isSameTree 已經 true，實際上短路不會執行
│  └─ isSubtree(2, 4)   // 同上
│  => true
└─ isSubtree(5, 4)      // 因為左邊已經 true，實際上短路不會執行
=> true

FAQ:
了解第10行的語意
既然目前這個 root 節點不可能是答案，那我就去它的左子樹找一次，或者去它的右子樹找一次。只要其中一邊找到，就算找到。

再來為什麼是 || 而不是 &&
因為 subRoot 只需要是 root 的某一處子樹，不需要同時出現在左邊和右邊。

所以邏輯是：
左邊找到 → true
右邊找到 → true
左右都沒找到 → false
這就是 ||（或）的語意。
如果寫成 &&，就會變成：
左子樹也要找到，右子樹也要找到，才算 true
這明顯太嚴格，不符合題意。

第12行, 當我們找到root中的 4, 與subRoot中的 4 相匹配的時候,
我們進一步呼叫 isSameTree 函式比對 4 之後的所有節點是否都相同

isSameTree(呼叫之後)
├─ True ---> 回傳True
└─ False---> 繼續在 root 的左右子樹中查找
*/