class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}
class CycleLinkedList{
    static Node head;
    static Node tail;
    void AddAtTail(int val){
        Node newNode = new Node(val);
        if (head == null){
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next!=null){
            current = current.next;
        }
        current.next = newNode;
        current = newNode;
        tail = newNode;
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
class Solution142 {
    public Node detectCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null){
            System.out.printf("slow = %s, ", slow.val);
            System.out.printf("fast = %s%n", fast.val);
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                System.out.printf("ans = %s", CycleLinkedList.tail.next.val);
                return CycleLinkedList.tail.next;
            }
        }
        return null;
    }

    static void main(String[] args) {
        Solution142 sol = new Solution142();
        CycleLinkedList list = new CycleLinkedList();
        int pos = 1;//pos = -1代表無環, pos = 0代表head形成環
        int[] nums = {3, 2, 0, -4, 8, 9};
        for (int i : nums){
            list.AddAtTail(i);
        }
        //list.PrintList(list.head);

        //手動製造環形鏈表
        Node current = CycleLinkedList.head;

        for (int i=0; i<pos; i++){
            current = current.next;
        }
        CycleLinkedList.tail.next = current;
        //System.out.println("tail.val = " + list.tail.val);
        sol.detectCycle(CycleLinkedList.head);
    }
}