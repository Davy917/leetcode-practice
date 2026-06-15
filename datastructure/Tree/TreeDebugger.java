package Tree;

import java.util.*;

public class TreeDebugger {
    private static int index = 0;

    public static TreeNode buildTree(Integer[] preorder) {
        index = 0;
        System.out.println("開始構建樹，輸入序列: " + Arrays.toString(preorder));
        return myBuildTree(preorder, 0);
    }
    private static TreeNode myBuildTree(Integer[] preorder, int depth) {
        // 兼容 Java 8 的縮排生成方式
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        String indent = sb.toString();

        if (index >= preorder.length) return null;

        Integer currentVal = preorder[index];
        index++;
        if (currentVal == null) {
            System.out.println(indent + "遇到 null，返回上一層");
            return null;
        }
        System.out.println(indent + "創建節點: " + currentVal + " (index: " + (index-1) + ")");
        TreeNode root = new TreeNode(currentVal);

        System.out.println(indent + " -> 進入 " + currentVal + " 的左子樹");
        root.setLeft(myBuildTree(preorder, depth + 1));

        System.out.println(indent + " -> 進入 " + currentVal + " 的右子樹");
        root.setRight(myBuildTree(preorder, depth + 1));

        return root;
    }
    
    static void main(String[] args) {
        Integer[] testCase = {1, 2, 4, null, null, null, 3, null, 5, null, null};
        buildTree(testCase);
        System.out.println("\n構建完成！");
    }
}
/*
1, 2, 4, null, null, null, 3, null, 5, null, null
      1
     / \
    2   3
   /     \
  4       5
 */