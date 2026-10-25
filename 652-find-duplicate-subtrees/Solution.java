import java.util.*;
import Tree.TreeNode;
import Tree.TreeDebugger_v2;

class Solution652 {
    static Map<String, TreeNode> treeNodeMap = new HashMap<>();
    static Set<String> exist = new HashSet<>();
    private static String dfs(TreeNode node){
        if (node == null){return "";}
        String treeNodeKey = node.getVal() + "(" + dfs(node.getLeft()) + "," + dfs(node.getRight()) + ")";
        if (!exist.add(treeNodeKey)){           //如果不能成功加入 exist，說明這個 key 之前已經存在，這是重複的子樹！
            treeNodeMap.put(treeNodeKey, node);//把它記錄到 treeNodeMap
        }
        return treeNodeKey;
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<>(treeNodeMap.values());//注意這裡用的是values
    }

    static void main(String[] args) {
        Integer[] levelOrder = {1, 2, 3, 4, null, 2, 4, null, null, 4};
        TreeNode Root = TreeDebugger_v2.buildLevelOrderTree_v2(levelOrder);
        System.out.println("Ans = " + findDuplicateSubtrees(Root));
    }
}

/*
 想到什麼?
 DFS前序遍歷 + 哈希集合
 每次經過一個節點就把節點值放到集合中

 先把樹建起來方便等下測試

 先把前序遍歷寫出來--->中左右
 預期: 1, 2, 4, 3, 2, 4, 4

 前序遍歷寫完了, 開始探討如何儲存子樹
 題目要返回的是 List<TreeNode>
 可能不是前序遍歷, 想錯了趕快修正

 把後序遍歷寫出來--->左右中
 預期: 4, 2, 4, 2, 4, 3, 1

 [4], [4, 2]
 設計哈希鍵是關鍵
 */