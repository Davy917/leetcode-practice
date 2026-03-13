//parse 以及 printByLevels 由 AI 生成
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
    Node tail;
    void AddAtTail(Node newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            System.out.printf("add %s at head%n", newNode.val);
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        System.out.printf("add %s at tail%n", newNode.val);
    }

}
class Solution430 {
    public static Node parse(Integer[] data) {
        if (data == null || data.length == 0 || data[0] == null) return null;

        int i = 0;

        // 1) Build top-level list until first null
        Node head = new Node(data[i++], null, null, null);
        Node tail = head;

        List<Node> parents = new ArrayList<>();
        parents.add(head);

        while (i < data.length && data[i] != null) {
            Node n = new Node(data[i++], tail, null, null);
            tail.next = n;
            tail = n;
            parents.add(n);
        }

        // skip nulls after top level
        while (i < data.length && data[i] == null) i++;

        // 2) BFS-like: for each parent node, if it has a child list in the stream, attach it
        Deque<Node> queue = new ArrayDeque<>(parents);

        while (i < data.length && !queue.isEmpty()) {
            Node parent = queue.pollFirst();

            // If stream indicates "no child" for this parent, it is represented by null at current position.
            if (i < data.length && data[i] == null) {
                i++; // consume this "no child" marker
                continue;
            }

            // If next token is non-null, it starts a child list for this parent.
            if (i < data.length && data[i] != null) {
                Node childHead = new Node(data[i++], null, null, null);
                Node childTail = childHead;

                List<Node> childNodes = new ArrayList<>();
                childNodes.add(childHead);

                while (i < data.length && data[i] != null) {
                    Node n = new Node(data[i++], childTail, null, null);
                    childTail.next = n;
                    childTail = n;
                    childNodes.add(n);
                }

                // attach
                parent.child = childHead;

                // enqueue all child nodes as potential parents for deeper levels
                for (Node cn : childNodes) queue.addLast(cn);

                // after finishing this child list, we are at a null separator (or end)
                while (i < data.length && data[i] == null) i++;
            }
        }

        return head;
    }

    // Debug printer: prints each level as lines: Level k: 1-2-3 ; with child pointers shown separately
    public static void printByLevels(Node head) {
        if (head == null) {
            System.out.println("(empty)");
            return;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.add(head);
        int level = 0;
        while (!q.isEmpty()) {
            Node start = q.pollFirst();
            System.out.print("Level " + level + ": ");
            Node cur = start;
            while (cur != null) {
                System.out.print(cur.val);
                if (cur.next != null) System.out.print(" <-> ");
                if (cur.child != null) q.addLast(cur.child);
                cur = cur.next;
            }
            System.out.println();
            level++;
        }
    }
    //
    //找到有child的Node, 用visitor遍歷
    public Node flatten(Node head) {
        if (head == null){
            return null;
        }
        Node cur = head;
        System.out.printf("cur = %s%n", cur.val);

        while (cur != null){
            if (cur.child == null){
                cur = cur.next;
                System.out.printf("cur = %s%n", cur.val);
                continue;
            }

            System.out.printf("have child%n");
            //把3 跟 7 接上
            Node temp = cur.next;//先寫一個temp, 子列表併回母列表會用到
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;//注意, 要記得把原節點 child 設 null
            //開始遍歷7
            while (cur.next != null){
                cur = cur.next;
            }
            //子列表併回母列表
            cur.next = temp;
            temp.prev = cur;
        }
        return head;
    }

    static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12};
        Node head = parse(nums);
        printByLevels(head);
        Solution430 sol = new Solution430();
        sol.flatten(head);
    }
}
/*
第一步:找到有child的Node
第二步:
 */
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