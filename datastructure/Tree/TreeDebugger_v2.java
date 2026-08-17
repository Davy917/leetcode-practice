package Tree;
import java.util.*;

public class TreeDebugger_v2 {
    public static TreeNode buildLevelOrderTree_v2(Integer[] levelOrder) {
        if (levelOrder.length == 0)
            return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        int n = levelOrder.length;
        Queue<TreeNode> queue = new ArrayDeque<>(root);
        int index = 1;
        for (int i = 0; i < n; i++) {
            root.setLeft();
        }
        return root;
    }
    public static void main(String[] args) {
        Integer[] levelOrder = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1};
        buildLevelOrderTree_v2(levelOrder);
    }
}
/*
          5
        /   \
       4     8
      /     / \
     11    13  4
    /  \        \
   7    2        1
*/