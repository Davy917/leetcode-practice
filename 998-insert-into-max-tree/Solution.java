//扣友提供解答
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
class Solution998 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val > root.val)
            return new TreeNode(val, root, null);
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
/*
输入：root = [5,2,4,null,1], val = 3
输出：[5,2,4,null,1,null,3]
解释：a = [2,1,5,4], b = [2,1,5,4,3]

insertIntoMaxTree(root=5, val=3)
└─ 3 <= 5, 遞迴到右子樹
   insertIntoMaxTree(root=4, val=3)
   └─ 3 <= 4, 遞迴到右子樹
      insertIntoMaxTree(root=null, val=3)
      └─ root == null => return new TreeNode(3)
   └─ 回傳後: 4.right = 3, return 4
└─ 回傳後: 5.right = 4, return 5

教學影片
https://www.youtube.com/watch?v=mKxSzz9opWg

扣友提供解答:
https://leetcode.cn/problems/maximum-binary-tree-ii/solutions/1785544/by-ac_oier-v82s/
 */