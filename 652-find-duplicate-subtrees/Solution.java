import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
    //看到這種寫法就要知道，我們可以從外面傳值進來修改TreeNode的數值
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
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

class Solution652 {
    Map<String, TreeNode> treeNodeMap = new HashMap<>();
    Set<String> keySet = new HashSet<>();

    private String visitTreeNode(TreeNode node){
        if (node == null){return "";}
        //設計哈希鍵
        String treeNodeKey = node.val                 // 當前節點值
                + "("                                // 左括號：開始子樹描述
                + visitTreeNode(node.left)     // 左子樹的序列化結果
                + ","                               // 逗號：分隔左右子樹
                + visitTreeNode(node.right)   // 右子樹的序列化結果
                + ")";                             // 右括號：結束子樹描述

        System.out.println("treeNodeKey = " + treeNodeKey);

        if (!keySet.add(treeNodeKey)){//如果 (不能成功加入 keySet)，說明這個 key 之前已經存在，這是重複的子樹！
            treeNodeMap.put(treeNodeKey, node);//把它記錄到 treeNodeMap
        }
        System.out.println("map.get = " + treeNodeMap.get(treeNodeKey));
        System.out.println("map.values = " + treeNodeMap.values());
        System.out.println("treeNodeMap = " + treeNodeMap);
        return treeNodeKey;
    }

    public List<TreeNode> P0652_findDuplicateSubtrees(TreeNode root) {
        visitTreeNode(root);
        return new ArrayList<>(treeNodeMap.values());//注意這裡用的是values
    }

    public static void main(String[] args) {
        // 手動建立測試樹（和範例相同的結構）
        TreeNode n4a = new TreeNode(4);
        TreeNode n2a = new TreeNode(2, n4a, null);
        TreeNode n4b = new TreeNode(4);
        TreeNode n2b = new TreeNode(2, n4b, null);
        TreeNode n3 = new TreeNode(3, n2b, new TreeNode(4));
        TreeNode root = new TreeNode(1, n2a, n3);

        Solution652 sol = new Solution652();
        List<TreeNode> result = sol.P0652_findDuplicateSubtrees(root);//寫一個 result 的 List 來存放結果
        System.out.println("result = " + result);
        System.out.println("Found " + result.size() + " duplicate subtree(s):");

        for (TreeNode t : result) {
            System.out.println("root val = " + t.val);
        }
    }
}

/**
圖型化測試樹:
      root
        ↓
        1
      /   \
   n2a     n3
    ↓       ↓
    2       3
   /       / \
 n4a    n2b   4
  ↓      ↓
  4      2
        /
      n4b
       ↓
       4
TreeNode n4a = new TreeNode(4);
TreeNode n2a = new TreeNode(2, n4a, null);
TreeNode n4b = new TreeNode(4);
TreeNode n2b = new TreeNode(2, n4b, null);
TreeNode n3 = new TreeNode(3, n2b, new TreeNode(4));
TreeNode root = new TreeNode(1, n2a, n3);

 class TreeNode {
 int val;
 TreeNode left;
 TreeNode right;
 TreeNode(int val) { this.val = val; }
 TreeNode(int val, TreeNode left, TreeNode right) {
 this.val = val;
 this.left = left;
 this.right = right;
     }
 }
 */

/**
 * TreeNode 对象在内存中的样子：
 *
 * ┌────────────────────────┐
 * │     TreeNode 实例       │
 * ├────────────────────────┤
 * │  val:    4             │  ← int 类型，直接存值
 * ├────────────────────────┤
 * │  left:   null          │  ← 引用类型，指向另一个 TreeNode
 * │          (0x0000)      │     或者 null
 * ├────────────────────────┤
 * │  right:  null          │  ← 引用类型，指向另一个 TreeNode
 * │          (0x0000)      │     或者 null
 * └────────────────────────┘
 *
 *
 *
 * 内存地址: 0x1000              内存地址: 0x2000
 * ┌────────────────┐           ┌────────────────┐
 * │  TreeNode      │           │  TreeNode      │
 * ├────────────────┤           ├────────────────┤
 * │  val:   2      │           │  val:   4      │
 * ├────────────────┤           ├────────────────┤
 * │  left:  0x2000 │──────────>│  left:  null   │
 * ├────────────────┤           ├────────────────┤
 * │  right: null   │           │  right: null   │
 * └────────────────┘           └────────────────┘
 *    parent 变量                  child 变量
 */