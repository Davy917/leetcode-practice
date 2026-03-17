import java.util.ArrayList;
import java.util.List;

class ListNode{
    int val;
    ListNode next;
    ListNode prev;
    ListNode(int val,ListNode prev, ListNode next){
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
    }

    void printList(ListNode head){
        List<Integer> numbs = new ArrayList<>();
        ListNode cur = head;
        numbs.addLast(cur.val);
        while (cur.next != null){
            cur = cur.next;
            numbs.addLast(cur.val);
        }
        System.out.println(numbs);
    }
}
class Solution328 {
    LinkedList mainList;
    Solution328() {
        mainList = new LinkedList();
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        ListNode temp;
        //此迴圈在奇數情況下會少跑一次，嘗試優化中
        while (fast.next != null && fast.next.next != null){
            temp = fast.next;
            fast.next = fast.next.next;
            temp.next = slow.next;
            slow.next = temp;
            //移動指針
            slow = slow.next;
            fast = fast.next;
            mainList.printList(head);
            System.out.println("slow.val" + slow.val);
            System.out.println("fast.val" + fast.val);
        }
        //偶數情形
        if (fast.next == null) {
            mainList.printList(head);
            return head;
        }
        //奇數情形
        if (fast.next.next == null){
            temp = fast.next;
            fast.next = null;
            temp.next = slow.next;
            slow.next = temp;
            mainList.printList(head);
        }
        return head;
    }
/*
slow     fast     temp
  ↓        ↓        ↓
[2|-]--->[1|-]--->[3|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]--->null
         slow               fast     temp
           ↓                 ↓        ↓
[2|-]--->[3|-]--->[1|-]--->[5|-]--->[6|-]--->[4|-]--->[7|-]--->null
                   slow                      fast     temp
                    ↓                          ↓        ↓
[2|-]--->[3|-]--->[6|-]--->[1|-]--->[5|-]--->[4|-]--->[7|-]--->null

                   slow                      fast
                    ↓                          ↓
[2|-]--->[3|-]--->[6|-]--->[7|-]--->[1|-]--->[5|-]--->[4|-]--->null

                           slow                      fast
                            ↓                          ↓
[2|-]--->[3|-]--->[6|-]--->[7|-]--->[1|-]--->[5|-]--->[4|-]--->null
 */
public ListNode oddEvenList_v2(ListNode head) {
    if (head == null || head.next == null){
        return head;
    }

    ListNode slow = head;
    ListNode fast = head.next;
    ListNode temp;

    while (fast.next != null){
        temp = fast.next;
        fast.next = fast.next.next;
        temp.next = slow.next;
        slow.next = temp;

        //移動指針
//        if (fast.next != null){
//            fast = fast.next;
//        }
        fast = fast.next;
        slow = slow.next;
        mainList.printList(head);
        System.out.println("slow.val" + slow.val);
        System.out.println("fast.val" + fast.val);
    }
    return head;
}
    static void main(String[] args) {
        int[] nums = {2, 1, 3, 5, 6, 4, 7};
        Solution328 sol = new Solution328();
        for (int i : nums){
            ListNode newNode = new ListNode(i, null, null);
            sol.mainList.AddAtTail(newNode);
        }
//        sol.oddEvenList(sol.mainList.head);
        sol.oddEvenList_v2(sol.mainList.head);
    }
}
/*
输出: [2,3,6,7,1,5,4]
 */