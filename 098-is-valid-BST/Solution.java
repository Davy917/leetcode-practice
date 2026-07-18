/*
前序遍歷看js
中序遍歷看java
後序便利看golang
 */
import Tree.*;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution98 {
    public static boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer inorder = Integer.MIN_VALUE;
        while (!stack.isEmpty() || root != null) //如果stack還有, 或是root還有
        {
            while (root != null) //左子樹走到底, 節點全部裝進stack
            {
                stack.push(root);
                root = root.getLeft();
                System.out.println("Stack: " + stack.stream().map(n -> n.getVal()).toList());
            }

            root = stack.pop(); //因為中序遍歷, stack的順序一定是由小到大
            if (root.getVal() <= inorder) //違反此規則, 一定不是BST
                return false;
            inorder = root.getVal();
            System.out.println("inorder = " + inorder);
            root = root.getRight();
        }
        return true;
    }
    static void main(String[] args) {
        Integer[] levelOrder = {5,1,4,null,null,3,6};
        TreeNode root = TreeDebugger.buildLevelOrderTree(levelOrder);
        System.out.println("Ans = " + isValidBST(root));
    }
}
/*
BST的中序遍歷用迭代的方式寫, 自己想不到, 照抄官方解法後再自行理解
https://leetcode.cn/problems/validate-binary-search-tree/solutions/230256/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
*/