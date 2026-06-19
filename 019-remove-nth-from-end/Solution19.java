class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}
class CycleLinkedList {
    Node head;
    Node tail;
    void AddAtTail(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
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
class Solution19 {
    public Node removeNthFromEnd(Node head, int n) {
        if (head == null){return null;}
        Node current = head;
        int listSize = 1;

        while (current.next !=null){
            current = current.next;
            listSize++;
        }
        //求得listSize 後 listSize - n 其實就是 index
        //這一段就是 P707 中 deleteAtHead
        if (listSize - n == 0){
            head = head.next;
            return head;
        }
        //剩下的就參考P707 中 deleteAtIndex 設計
        current = head;
        int counter = 0;
        while (current.next != null && counter < listSize - n - 1){
            current = current.next;
            counter++;
            System.out.printf("loop = %s ", counter);
        }
        System.out.printf("%n" + "now current.val = %s", current.val);

        if (current.next == null){return null;}//注意
        current.next = current.next.next;//注意
        return head;
    }

    public static void main(String[] args) {
        Solution19 sol = new Solution19();
        int[] nums = {1, 2, 3, 4, 5};
        int n = 2;

        CycleLinkedList list = new CycleLinkedList();
        for (int i : nums){
            list.AddAtTail(i);
        }
        list.PrintList(list.head);
        sol.removeNthFromEnd(list.head, n);
    }
}