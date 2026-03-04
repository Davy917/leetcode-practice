import java.util.HashSet;
import java.util.Set;

class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}

class CycleLinkedList{
    Node head;
    Node tail;
    CycleLinkedList(Node head){
        this.head = head;
        this.tail = head;
    }
    void AddAtTail(int val){
        Node newNode = new Node(val);
        Node current = head;
        while (current.next!=null){
            current = current.next;
        }
        current.next = newNode;
        current = newNode;
        tail = current;
    }

    void PrintList(Node head){
        System.out.print("[");
        Node current = head;
        while (current != null){
            System.out.print(current.val);
            if (current.next != null){
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
}

class Solution141 {
    public boolean hasCycle(Node head) {
        if (head == null){
            return false;
        }
        Set<Node> set = new HashSet<>();
        set.add(head);
        Node current = head;
        while (current.next != null){
            current = current.next;
            if (set.contains(current)){
                System.out.println("true");
                return true;
            }
            set.add(current);
        }
        System.out.println("false");
        return false;
    }

    static void main(String[] args) {
        Solution141 sol = new Solution141();
        //純手動塞值
        Node head = new Node(3);
        int[] nums = {2, 7, 9};
        CycleLinkedList list = new CycleLinkedList(head);
        for (int i : nums){
            list.AddAtTail(i);
        }
        //製造環形鏈表
        list.tail.next = list.head;
        sol.hasCycle(head);
    }
}