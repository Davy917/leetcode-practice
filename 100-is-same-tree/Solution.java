import Tree.*;

//官方解答
class Solution100 {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.getVal() != q.getVal()) {
            System.out.printf("p.val = %d, q.val = %d\n", p.getVal(), q.getVal());
            return false;
        } else {
            System.out.printf("p.val = %d, q.val = %d\n", p.getVal(), q.getVal());
            return isSameTree(p.getLeft(), q.getLeft()) && isSameTree(p.getRight(), q.getRight());
        }
    }

    static void main(String[] args) {
        /*
        TestCase1:
        Integer[] p = {1, 1, 2};
        Integer[] q = {1, 1, 2};
         */
        //TestCase2:
        Integer[] p = {1, 1, null, null, 2};
        Integer[] q = {1, 1, null, null, 2};

        var pNode = TreeDebugger.buildTree(p);
        var qNode = TreeDebugger.buildTree(q);
        System.out.println("Ans = " + isSameTree(pNode, qNode));
    }
}
/*
buildTree, 來自TreeNode
datastructure/Tree/BuildTree.java

https://leetcode.cn/problems/same-tree/solutions/363636/xiang-tong-de-shu-by-leetcode-solution/

TestCase1:
    1
   /
  1
 /
2

遞迴樹:
isSameTree(1, 1)  -> 值相同，繼續比左右
├─ isSameTree(1, 1)  -> 值相同，繼續比左右
│  ├─ isSameTree(2, 2)  -> 值相同，繼續比左右
│  │  ├─ isSameTree(null, null) -> true
│  │  └─ isSameTree(null, null) -> true (比對 right)
│  │  => true && true = true
│  └─ isSameTree(null, null) -> true (比對 right)
│  => true && true = true
└─ isSameTree(null, null) -> true (比對 right)
=> true && true = true

TestCase2:
    1
   / \
  1   2

遞迴樹:
  isSameTree(1, 1)           <- root，值相同，繼續
├─ left:  isSameTree(1, 1) <- 左子節點，值相同，繼續
│  ├─ left:  isSameTree(null, null) -> true
│  └─ right: isSameTree(null, null) -> true
│  => true
└─ right: isSameTree(2, 2) <- 右子節點，值相同，繼續
   ├─ left:  isSameTree(null, null) -> true
   └─ right: isSameTree(null, null) -> true
   => true
=> true && true = true
*/