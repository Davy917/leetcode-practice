//雙指針, 鏈表插入排序
import java.util.ArrayList;
import java.util.List;

class ListNode{
    int val;
    ListNode next;
    ListNode(int val, ListNode next){
        this.val = val;
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
        tail.next = newNode;
        tail = newNode;
    }
    void printList(ListNode head){
        if (head == null){
            return;
        }
        List<Integer> mainList = new ArrayList<>();
        mainList.add(head.val);
        ListNode cur = head;
        while (cur.next != null){
            cur = cur.next;
            mainList.add(cur.val);
        }
        System.out.println(mainList);
    }
}

class Solution147 {
    LinkedList mainList;
    Solution147() {
        mainList = new LinkedList();
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null){return null;}
        ListNode dummy = new ListNode(0, head);
        ListNode cur = head.next, lastSorted = head;
        /*
        雙指針: cur, lastSorted
        cur = 主要用來遍歷的指針
        lastSorted = 這個指針指到的節點，之前的鏈表是"已排序好的"
         */

        while (cur != null){
            if (lastSorted.val <= cur.val){
                lastSorted = lastSorted.next;
            }

            else {
                System.out.println("need to swap cur.val " + cur.val);
                System.out.println("As-is:");
                mainList.printList(dummy.next);
                ListNode prev = dummy;
                while (prev.next.val <= cur.val){
                    System.out.println(prev.next.val + " <= cur.val");
                    prev = prev.next;
                }
                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next;
            System.out.println("To-be:");
            mainList.printList(dummy.next);
        }
        return dummy.next;
    }
/*
          head    cur
            ↓       ↓
[d|-]--->[5|-]--->[2|-]--->[3|-]--->[1|-]
            ↑
            lastSorted

          head    cur
            ↓       ↓
[d|-]--->[5|-]--->[2|-]--->[3|-]--->[1|-]
            ↑
            lastSorted

 */
    static void main(String[] args) {
        Solution147 sol = new Solution147();
        //int[] nums = {-1, 5, 3, 4, 0};
        int[] nums = {5, 2, 3, 1};
        for (int i : nums){
            ListNode newNode = new ListNode(i, null);
            sol.mainList.AddAtTail(newNode);
        }
        sol.insertionSortList(sol.mainList.head);
    }
}