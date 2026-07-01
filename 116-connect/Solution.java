import java.util.ArrayList;
import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(int _val) {
        val = _val;
    }
};

class Solution116 {
    public Node connect(Node root) {
        if (root == null)
            return null;
        Deque<Node> dq = new ArrayDeque<>();
        root.next = null;
        dq.add(root);
        while (!dq.isEmpty()){
            int curLevelSize = dq.size();
            for (int i = 0; i < curLevelSize; i++) {
                Node curNode = dq.pollFirst();
                if (curNode.right != null)
                    curNode.left.next = curNode.right;

                if (i < curLevelSize-1 && curNode.right != null) //如果i不是最後一個
                    curNode.right.next = dq.peekFirst().left;
                else if (i >= curLevelSize-1 && curNode.right != null)
                    curNode.right.next = null;
                
                if (curNode.left != null)
                    dq.add(curNode.left);
                if (curNode.right != null)
                    dq.add(curNode.right);
            }
        }
        return root;
    }
    public Node connect_v2(Node root) {
        if (root == null)
            return null;
        Node curNode = root;
        curNode.next = null;
        Node l = null, r = null;
        while (){
            r.next = null;
            l= curNode.left;
            r = curNode.right;
            l.next = r;
        }
        return root;
    }
}
/*
思考三個問題:
30行為什麼是用peekFirst而不是pollFirst?

pollFirst會把下一個節點拉出來, 導致下一輪循環無法處理到該節點
我們給curNode.right.next賦值時, 會希望dq的第一個元素保持在該位置
所以peekFirst()用於"偷看"下一個節點來建立連接才是正確的

一定要在每次賦值的時候都加if防護嗎? 目前的代碼中，哪些 null 檢查是冗餘的？
把上面多餘的if 合併整理後再提交
https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/submissions/

怎麼優化到不用deque
*/