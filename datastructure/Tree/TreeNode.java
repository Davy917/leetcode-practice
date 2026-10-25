//練習專案可重用元件
package Tree;

public class TreeNode {
    private final Integer val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Integer val) { this.val = val; }
    public TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Integer getVal() { return val; }
    public TreeNode getLeft() { return left; }
    public TreeNode getRight() { return right; }

    public void setLeft(TreeNode left) { this.left = left; }
    public void setRight(TreeNode right) { this.right = right; }

    @Override//覆寫TreeNode 的 toString()，讓　treeNodeMap　的打印可讀
    public String toString() {
        if (left == null && right == null) return String.valueOf(val);//如果是子葉，直接return其值
        //非子葉return如下
        return val + "("
                + (left == null ? "null" : left.toString())
                + ","
                + (right == null ? "null" : right.toString())
                + ")";
    }
}

/*
FAQ:
一定要把TreeNode裡面每個屬性都加上public 才能被外部操作嗎, 有沒有更好的寫法?
現在 TreeNode 在 Tree package，而 100-is-same-tree/Solution.java 不在同 package，
所以如果要直接 p.val、p.left，那些成員就必須是 public 或 透過 public getter 間接取用。

TreeNode 被使用在:
100-is-same-tree/Solution.java
datastructure/Tree/TreeDebugger.java

前序遍歷:
144-preorder-traversal/Solution.java
中序遍歷:
094-inorder-traversal
後序遍歷:
236-lowest-common-ancestor/solution.py
 */