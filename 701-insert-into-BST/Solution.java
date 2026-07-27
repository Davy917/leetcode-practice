import Tree.*;
class Solution701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);
        return dfs(root, val);
    }
    public TreeNode dfs(TreeNode root, int val) {
        if (root.getVal() > val) {
            if (root.getLeft() == null)
                root.setLeft(new TreeNode(val));
            else
                dfs(root.getLeft(), val);  
        }
        else {
            if (root.getRight() == null)
                root.setRight(new TreeNode(val));
            else
                dfs(root.getRight(), val);
        }
        return root;
    }
}

/*
是猜對的, 整體概念並不是很熟, 要再寫一遍

目前寫法的特性
屬於 「預判子節點」風格的 DFS，核心特點是：
    在遞迴前檢查子節點是否為 null：如果為 null 就直接插入；不為 null 才繼續遞迴。
    不會對 null 節點呼叫遞迴，所以遞迴終止條件是「子節點為空」，而不是「當前節點為空」。
優點：直觀，容易理解「走到哪裡就插到哪裡」的思路。

其它寫法:
可以看題目中與Leet的對話紀錄
https://leetcode.cn/problems/insert-into-a-binary-search-tree/
*/