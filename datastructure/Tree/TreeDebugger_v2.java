package Tree;
import java.util.*;

public class TreeDebugger_v2 {
    public static TreeNode buildLevelOrderTree_v2(Integer[] levelOrder) {
        if (levelOrder.length == 0)
            return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int index = 1;

        while (!deque.isEmpty() && index < levelOrder.length){
            int levelSize = deque.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode curNode = deque.pollFirst();
                if (curNode != null && index < levelOrder.length){
                    if (levelOrder[index] != null){
                        var newNode = new TreeNode(levelOrder[index]);
                        curNode.setLeft(newNode);
                        deque.addLast(newNode);
                    }
                    index++;
                }
                if (index < levelOrder.length){
                    if (levelOrder[index] != null){
                        var newNode = new TreeNode(levelOrder[index]);
                        curNode.setRight(newNode);
                        deque.addLast(newNode);
                    }
                    index++;
                }
            }
        }
        return root;
    }
}
/*
20, 28行會有黃底, 因為curNode 如果是 null 那麼呼叫 setLeft, setRight 就會報錯
如果在17, 25行加上 curNode != null 可以解決
但實際上 if (levelOrder[index] != null) 已經確保了curNode不會有null, 所以curNode != null 也不用加
*/