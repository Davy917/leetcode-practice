import java.util.ArrayDeque;
import java.util.Deque;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }
}


class Solution117 {
    public Node connect(Node root) {
        if (root == null)
            return null;
        Deque<Node> dq = new ArrayDeque<>();
        dq.add(root);
        while (!dq.isEmpty()){
            int curLevelSize = dq.size();
            Node last = null;
            for (int i = 0; i < curLevelSize; i++) {
                Node curNode = dq.pollFirst();
                if (curNode.left != null)
                    dq.add(curNode.left);
                if (curNode.right != null)
                    dq.add(curNode.right);
                if (i != 0)
                    last.next = curNode;
                last = curNode;
            }
        }
        return root;
    }
    public Node connect_v2(Node root){
        if (root == null)
            return null;
        Node leftMost = root;
        while (leftMost != null){
            Node first = null;
            Node last = null;
            Node cur = leftMost;

            while (cur != null){
                if (cur.left != null){
                    if (last != null)
                        last.next = cur.left;
                    else
                        first = cur.left;
                    last = cur.left;
                }
                if (cur.right != null){
                    if (last != null)
                        last.next = cur.right;
                    else
                        first = cur.right;
                    last = cur.right;
                }
                cur = cur.next;
            }
            leftMost = first;
        }
        return root;
    }
}
/*
connect是看官解才寫出來的
https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/description/

其巧妙之處在於下面的安排

if (i != 0) 代表非當層的頭節點
    last.next = curNode;
last = curNode;

connect_v2也是看官解才寫出來的
https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/description/


內層while迴圈一開始看不懂, 要靜下來思考

參數說明
leftMost 錨定在當層最左邊的那個指針
cur 當層由左至右移動的指針

first 錨定在下一層最左邊的那個指針, 用來幫助 leftMost 繼續推進
last 下一層由左至右移動的指針

內層迴圈安排非常巧妙, 如果沒有規劃好再寫, 很難一次寫對
*/