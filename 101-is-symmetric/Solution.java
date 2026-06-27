//鏡像遍歷, 最直觀可以想到的, 遞歸官方解看python, 迭代官方解看golang
import java.util.*;
import Tree.TreeNode;
import Tree.TreeDebugger;
class Solution101 {
    public static boolean isSymmetric(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        preorderLeft(root.getLeft(), l);
        preorderRight(root.getRight(), r);
        System.out.println(l);
        System.out.println(r);
        return l.equals(r);
    }
    // 左子樹：根 -> 左 -> 右 (包含 null)
    public static void preorderLeft(TreeNode root, List<Integer> l) {
        if (root == null) {
            l.add(null); // 必須記錄 null 以保存結構資訊
            return;
        }
        l.add(root.getVal());
        preorderLeft(root.getLeft(), l);
        preorderLeft(root.getRight(), l);
    }
    // 右子樹：根 -> 右 -> 左 (鏡像遍歷，包含 null)
    public static void preorderRight(TreeNode root, List<Integer> r) {
        if (root == null) {
            r.add(null);
            return;
        }
        r.add(root.getVal());
        preorderLeft(root.getRight(), r); // 先走右邊
        preorderLeft(root.getLeft(), r);  // 再走左邊
    }
    public static void main(String[] args) {
        Integer[] nums = {1,2,2,3,4,4,3,5,6,null,null,null,null,6,5};
        TreeNode root = TreeDebugger.buildLevelOrderTree(nums);
        System.out.println("Ans = " + isSymmetric(root));
    }
}
/*
              1
           /     \
          2       2  (右)
         / \     / \
        3   4   4   3  (右)
       / \         / \
      5   6       6   5

    相關題目
    100-is-same-tree
*/