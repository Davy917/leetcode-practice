#迭代寫法，遞迴寫法見python
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
    LinkedList(ListNode head, ListNode tail){
        this.head = head;
        this.tail = tail;
    }
    void AddAtTail(ListNode newNode){
        if (head == null){
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }
    void printList(ListNode head){
        if (head == null){
            System.out.println("head = null");
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
    LinkedList mainList;
    Solution24(){
        mainList = new LinkedList(null, null);
    }
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            mainList.printList(head);
            return head;
        }
        ListNode dummy = new ListNode(0, null, head);
        ListNode odd = dummy;
        ListNode even = dummy.next;
        ListNode temp;
        while (even.next != null){
            temp = even.next;
            even.next = even.next.next;
            temp.next = odd.next;
            odd.next = temp;
            //pointers shift
            if (even.next != null){
                even = even.next;
            }
            odd = odd.next.next;
            System.out.println("odd.val = " + odd.val);
            System.out.println("even.val = " + even.val);
            mainList.printList(dummy);
        }
        return dummy.next;
    }

    static void main(String[] args) {
        Solution24 sol = new Solution24();
        int[] nums = {1, 2, 3, 4};
        ListNode newNode;
        for (int i: nums){
            newNode = new ListNode(i, null, null);
            sol.mainList.AddAtTail(newNode);
        }
        sol.swapPairs(sol.mainList.head);
    }
}
/*
Loop1:
odd        even    temp
  ↓        ↓        ↓
[d|-]--->[1|-]--->[2|-]--->[3|-]--->[4|-]

temp--->[2|-]
odd         even
  ↓         ↓
[d|-]--->[1|-]--->[3|-]--->[4|-]

odd        temp    even
  ↓         ↓       ↓
[d|-]--->[2|-]--->[1|-]--->[3|-]--->[4|-]

//pointers shift

                 odd        even    temp
                  ↓         ↓        ↓
[d|-]--->[2|-]--->[1|-]--->[3|-]--->[4|-]

Loop2:
                 odd        even    temp
                  ↓         ↓        ↓
[d|-]--->[2|-]--->[1|-]--->[3|-]--->[4|-]

temp--->[4|-]
                 odd
                  ↓
[d|-]--->[2|-]--->[1|-]--->[3|-]

                 odd        even
                  ↓         ↓
[d|-]--->[2|-]--->[1|-]--->[4|-]--->[3|-]

//pointers shift

                                     odd        even
                                      ↓         ↓
[d|-]--->[2|-]--->[1|-]--->[4|-]--->[3|-]--->null

exit
 */