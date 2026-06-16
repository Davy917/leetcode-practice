//官方解答
import Tree.*;
import LinkedList.*;

class Solution1367 {
    public static boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null)
            return false;
        return isSamePath(head, root) || isSubPath(head, root.getLeft()) || isSubPath(head, root.getRight());
    }
    public static boolean isSamePath(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        if (head.val != root.getVal())
            return false;
        return isSamePath(head.next, root.getLeft()) || isSamePath(head.next, root.getRight());
    }
    static void main(String[] args) {
        /*
        TestCase1:
        Integer[] root = {1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3};
        Integer[] head = {4,2,8};
         */
        //TestCase2:
        Integer[] root = {1};
        Integer[] head = {1};
        var rootNode = TreeDebugger.buildTree(root);
        var headNode = LinkedList.buildLinkedList(head);
        System.out.println("Ans = " + isSubPath(headNode, rootNode));
    }
}
/*
buildTree, buildLinkedList來源
datastructure/LinkedList/LinkedList.java
datastructure/Tree/TreeDebugger.java

相關題目
100-is-same-tree/Solution.java
572-is-subtree/Solution.java

建議先看, 有機會自己寫出來
datastructure/Tree/Tree遞迴心法.md

官解:
https://leetcode.cn/problems/linked-list-in-binary-tree/solutions/122916/er-cha-shu-zhong-de-lie-biao-by-leetcode-solution/
*/