import java.util.HashMap;
import java.util.Map;

class Node{
    int val;
    Node next;
    Node random;
    Node(int val, Node next, Node random){
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
class LinkedList{
    Node head;
    LinkedList(Node head){
        this.head = head;
}
static class Solution138 {
    private Node buildexample(){
        Node newNode4 = new Node(1, null, null);
        Node newNode3 = new Node(10, newNode4, null);
        Node newNode2 = new Node(11, newNode3, null);
        Node newNode1 = new Node(13, newNode2, null);
        Node newNode0 = new Node(7, newNode1, null);
        //random賦值:
        newNode4.random = newNode0;
        newNode3.random = newNode2;
        newNode2.random = newNode4;
        newNode1.random = newNode0;
        newNode0.random = null;

        LinkedList mainList = new LinkedList(newNode0);
        return mainList.head;
    }
    public Node copyRandomList(Node head) {
        if (head == null){return null;}
        Map<Node, Node> map = new HashMap<>();

        //預處理dummy節點
        Node dummy = new Node(0, head, null);
        Node copyDummy = new Node(0, null, null);
        map.put(dummy, copyDummy);

        //把copyNode都建好，放到map中
        Node cur = dummy;
        while (cur.next != null){
            cur = cur.next;
            Node copyNode = new Node(cur.val, null, null);
            map.put(cur, copyNode);
            //System.out.printf("origin val = %s, ", cur.val);
            //System.out.printf("copy val = %s%n", map.get(cur).val);
        }

        //把建好的copyNode都串起來
        cur = dummy;
        System.out.println("cur.val = " + cur.val);//Debug

        while (cur.next != null){
            cur = cur.next;

            Node copyNode = map.get(cur);//增加可讀性
            copyNode.next = map.get(cur.next);
            copyNode.random = map.get(cur.random);
            System.out.println("cur.val = " + cur.val);//Debug
        }
        //上面 while 跑完之後copyDummy.next的值會是空的, 但其實不會影響結果
        return map.get(head);
    }

    static void main(String[] args) {
            Solution138 sol = new Solution138();
            Node exampleNode = sol.buildexample();
            sol.copyRandomList(exampleNode);
        }
    }
}