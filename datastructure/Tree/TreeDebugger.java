package Tree;
import java.util.*;

public class TreeDebugger {
    private static int index = 0;

    public static TreeNode buildPreorderTree(Integer[] preorder) {
        index = 0;
        System.out.println("開始構建樹，輸入序列: " + Arrays.toString(preorder));
        return recBuildPreorderTree(preorder, 0);
    }
    private static TreeNode recBuildPreorderTree(Integer[] preorder, int depth) {
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
        root.setLeft(recBuildPreorderTree(preorder, depth + 1));

        System.out.println(indent + " -> 進入 " + currentVal + " 的右子樹");
        root.setRight(recBuildPreorderTree(preorder, depth + 1));

        return root;
    }

    public static TreeNode buildLevelOrderTree(Integer[] levelOrder){
        if (levelOrder == null)
            return null;
        // root預處理
        TreeNode root = new TreeNode(levelOrder[0]);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        for (int i = 1; i < levelOrder.length; i++)
        {
            //如果 levelOrder[i] 為 null, 仍然需要用null佔位, 詳細說明在下方FAQ
            TreeNode newNode = null;
            if (levelOrder[i] != null)
                newNode = new TreeNode(levelOrder[i]);

            int parentIndex = (i - 1) / 2;
            TreeNode parent = nodes.get(parentIndex);
            if (i % 2 == 1)
                parent.setLeft(newNode);
            else
                parent.setRight(newNode);
            nodes.add(newNode);
        }
        return root;
    }
    public static void main(String[] args) {
//        Integer[] testCase = {1, 2, 4, null, null, null, 3, null, 5, null, null};
//        buildPreorderTree(testCase);
//        System.out.println("\n構建完成！");
        Integer[] levelOrder = {3, 9, 20, null, null, 15, 7};
        buildLevelOrderTree(levelOrder);
    }
}
/*
使用方式:
100-is-same-tree/Solution.java

範例:
1, 2, 4, null, null, null, 3, null, 5, null, null
      1
     / \
    2   3
   /     \
  4       5
  
前序遍歷:
144-preorder-traversal/Solution.java
中序遍歷:
094-inorder-traversal

buildLevelOrderTree FAQ:
步驟：
1. 先創建 root 並存入 nodes[0]
2. 遍歷 levelOrder[1...]
3. 找到 parentIndex = (i-1)/2
4. 從 nodes[parentIndex] 獲取父節點
5. 連接左右孩子
6. 把新節點存入 nodes[i]

1. TreeNode newNode = null 此時得到的 newNode 是什麼 ?
在 Java 中，這是一個空引用：
    newNode 是一個 TreeNode 類型的變量
    它的值是 null，表示不指向任何對象
    就像一個空的指針，沒有實際的 TreeNode 實例

2. 為什麼需要用 null 占位？
關鍵原因：保持索引對應關係
levelOrder = {3, 9, 20, null, null, 15, 7}
  索引:        0  1   2    3    4   5   6

3. 為什麼 nodes 要用 ArrayList 這種數據結構 ?
ArrayList 是唯一同時滿足隨機訪問 + 存儲 null + 動態擴展的選擇
如果不用 null 占位，就無法用 parentIndex = (i-1)/2 這個公式找父節點
所以選擇 ArrayList 是綜合考慮的結果，不僅僅是為了塞 null！
 */