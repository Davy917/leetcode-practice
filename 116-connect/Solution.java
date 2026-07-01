//connect 標準的層續遍歷, connect_v2就是優化到不用deque的版本
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
        Node leftMost = root;
        leftMost.next = null;

        if (leftMost.left != null && leftMost.right != null){
            leftMost.left.next = leftMost.right;
            leftMost.right.next = null;
        }
        while (leftMost.left != null){
            Node curNode = leftMost.left;
            while (curNode != null){
                if (curNode.left != null){
                    curNode.left.next = curNode.right;
                }
                if (curNode.right != null && curNode.next != null) {
                    curNode.right.next = curNode.next.left;
                }
                curNode = curNode.next;
            }
            leftMost = leftMost.left;
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

怎麼優化到不用deque?
請看connect_v2

connect_v2 思路:
最重要的等式先解出來
curNode.left.next = curNode.right;
curNode.right.next = curNode.next.left;

leftMost 指的是該層最左邊那個節點
curNode 的起始位置就是 leftMost 下一層最左邊
此時就可以把上面列出來的公式進行套用

先看過官解, 再用自己的理解寫一遍
https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/solutions/446938/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
*/