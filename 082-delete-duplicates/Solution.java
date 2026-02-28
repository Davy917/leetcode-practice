import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution82 {

    static class ListNode {
    int val;
    ListNode next;
    ListNode(int val){this.val = val;}
    }

    ListNode head;

    public void addAtTail(int val) {

        ListNode newNode = new ListNode(val);
        ListNode tailNode = head;

        if (head == null) {
            head = newNode;
            return;
        }
        while (tailNode.next != null) {
            tailNode = tailNode.next;//why??
        }
        tailNode.next = newNode;
        newNode.next = null;
    }

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null){ return null; }

        ListNode current = head;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(current.val, 1);

        while (current.next != null){
            current = current.next;
            map.put(current.val, map.getOrDefault(current.val, 0) + 1);
        }
        System.out.println("map = " + map);


        //處理頭節點，為Duplicates的情形
        while (head != null && (map.get(head.val)>1)){ head = head.next; }

        //頭節點處理完了，但是鏈表也空了
        if (head == null) { return null; }

        //頭節點處理完了，但是鏈表還沒空
        current = head;
        while (current.next != null){
            System.out.println("current.next.val = " + current.next.val);

            if (map.get(current.next.val) > 1){
                current.next = current.next.next;
            }
            else {
                current = current.next;
            }
        }
        return head;
    }
    /*
    head

    1-   -->1--->1
     */

    public void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null){
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    static void main(String[] args) {
        Solution82 sol = new Solution82();
        int[] nums = {1,2,3,3,4,4,5};
        for (int i : nums){
            sol.addAtTail(i);
        }
        sol.printList(sol.head);
        ListNode result = sol.deleteDuplicates(sol.head);
        sol.printList(result);
    }
}
