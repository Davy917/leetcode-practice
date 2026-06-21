//官方解答
import Tree.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        return rserialize(root, sb);
    }
    public String rserialize(TreeNode root, StringBuilder sb){
        if (root == null)
            sb.append("null,");
        else {
            sb.append(root.getVal()).append(",");
            rserialize(root.getLeft(), sb);
            rserialize(root.getRight(), sb);
        }
        System.out.println("sb = " + sb.toString()); //sb = 1,2,3,null,null,4,5,null,null,null,null,
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new ArrayList<>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
        /*
        debug
        System.out.println("dataArray = " + Arrays.toString(dataArray));
        System.out.println("dataList = " + dataList);

        dataArray = [1, 2, 3, null, null, 4, 5, null, null, null, null]
        dataList = [1, 2, 3, null, null, 4, 5, null, null, null, null]
         */
    }

    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.getFirst().equals("null")){
            dataList.removeFirst();
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataList.getFirst()));
        dataList.removeFirst();
        root.setLeft(rdeserialize(dataList));
        root.setRight(rdeserialize(dataList));
        return root;
    }
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();

        Integer[] nums = {1, 2, 3, null, null, 4, 5};
        TreeNode root = TreeDebugger.buildTree(nums);
        deser.deserialize(ser.serialize(root));
    }
}
/*
官方解答
https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/solutions/290065/er-cha-shu-de-xu-lie-hua-yu-fan-xu-lie-hua-by-le-2/


split方法:
基本概念
String.split(...) 會把字串用指定分隔規則切開，回傳 String[]。

舉例:
String s = "a,b,c";
String[] arr = s.split(",");
結果是 ["a", "b", "c"]。

可以用第二個參數控制切割次數與是否保留尾端空字串：
str.split(regex, limit)
    limit > 0：最多切 limit - 1 次
    limit == 0：預設行為，移除尾端空字串
    limit < 0：保留尾端空字串

預設：split(",") == split(",", 0)（尾端空字串會被移除）

Arrays.asList(dataArray) 的意義是：
把 String[] dataArray 轉成 List<String> 介面，方便你用 get(0)、remove(0) 這種 List 操作。
但它回傳的 List 是固定大小（背後綁著原陣列），不能 add/remove。

所以外面再包一層：
new ArrayList<>(...) 會建立一個真正可增刪的新 List。
這對 rdeserialize(List<String> dataList) 很重要，因為官方寫法通常會一直 remove(0) 消耗前序序列。
 */