import java.util.ArrayList;

class ListNode{
    int val;
    ListNode prev;
    ListNode next;
    ListNode(int val, ListNode prev, ListNode next){
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
class LinkedList{
    ListNode head;
    ListNode tail;
    void AddAtTail(ListNode newNode){
        if (head == null){
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        return;
    }
    void printList(ListNode head){
        if (head == null){
            return;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(head.val);

        ListNode cur = head;
        while (cur.next != null){
            cur = cur.next;
            list.add(cur.val);
        }
        System.out.println(list);
    }
}
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        //TODO
        return head;
    }

    static void main(String[] args) {
        Solution24 sol = new Solution24();
        LinkedList mainList = new LinkedList();
        int[] nums = {1, 2, 3, 4};
        for (int i: nums){
            ListNode newNode = new ListNode(i, null, null);
            mainList.AddAtTail(newNode);
        }
        mainList.printList(mainList.head);
//        sol.swapPairs(mainList.head);
    }
}