class Node{
    int val;
    Node prev;
    Node next;
    Node child;
    Node(int val, Node prev, Node next, Node child){
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
}
class LinkedList{
    Node head;
    LinkedList(Node head){this.head = head;}
}
class Solution430 {
    private Node buildexample() {

        Node newNode12 = new Node(12, null, null, null);
        Node newNode11 = new Node(11, null, newNode12, null);
        Node newNode10 = new Node(10, null, null, null);
        Node newNode9 = new Node(9, null, newNode10, null);
        Node newNode8 = new Node(8, null, newNode9, newNode11);
        Node newNode7 = new Node(7, null, newNode8, null);
        Node newNode6 = new Node(6, null, null, null);
        Node newNode5 = new Node(5, null, newNode6, null);
        Node newNode4 = new Node(4, null, newNode5, null);
        Node newNode3 = new Node(3, null, newNode4, newNode7);
        Node newNode2 = new Node(2, null, newNode3, null);
        Node newNode1 = new Node(1, null, newNode2, null);

    //Set prev
        //layer3
        newNode12.prev = newNode11;
        //layer2
        newNode10.prev = newNode9;
        newNode9.prev = newNode8;
        newNode8.prev = newNode7;
        //layer1
        newNode6.prev = newNode5;
        newNode5.prev = newNode4;
        newNode4.prev = newNode3;
        newNode3.prev = newNode2;
        newNode2.prev = newNode1;

        LinkedList mainList = new LinkedList(newNode1);
        return mainList.head;
    }
    private Node buildexample2(){
        Node newNode3 = new Node(3, null, null, null);
        Node newNode2 = new Node(2, null, null, newNode3);
        Node newNode1 = new Node(1, null, null, newNode2);
        LinkedList subList = new LinkedList(newNode1);
        return subList.head;
    }
    public Node flatten(Node head) {
        if (head == null){return null;}
        Node cur = head;
        while (cur != null){
            System.out.printf("%n");
            if (cur.child == null){
                System.out.printf("cur = %s", cur.val);
                cur = cur.next;
                continue;
            }
            System.out.printf("cur = %s have child%n", cur.val);

            //先存 cur.next, cur.child 後面會用到
            Node temp = cur.next;
            Node childHead = cur.child;//注意，容易忽略

            //把3 跟 7 接上
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;//注意, 要記得把原節點 child 設 null

            //找子列表尾
            while (cur.next != null){
                cur = cur.next;
            }

            //子列表尾接回原本 next（若原本 next 不存在就不用接）
            cur.next = temp;
            if (temp != null) { //注意, if 判斷式如果沒加, 在某些 condition 會噴錯
                temp.prev = cur;
            }

            //cur移到7
            cur = childHead;
        }
        return head;
    }
/*
           cur   temp
             ↓   ↓
     1---2---3---4---5---6--NULL
             |
  childHead→ 7---8---9---10--NULL
                 |
                 11--12--NULL

                         temp
                         ↓
     1---2---3           4---5---6--NULL
             |           |
  childHead→ 7---8---9---10 ← cur
                 |
                 11--12--NULL

             cur              temp
             ↓                ↓
 1---2---3---7---8---9---10---4---5---6--NULL
                 |
      childHead→ 11--12--NULL

      後面以此類推
 */
    static void main(String[] args) {
        Solution430 sol = new Solution430();
        Node exampleNode = sol.buildexample();
        Node exampleNode2 = sol.buildexample2();
        sol.flatten(exampleNode2);
    }
}
/*

 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

序列化其中的每一级之后：
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]

为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，
以表示没有节点连接到上一级的上级节点。
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]

合并所有序列化结果，并去除末尾的 null 。
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 */