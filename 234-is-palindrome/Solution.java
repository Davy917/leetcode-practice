//將值複製到數組中然後使用雙指針
import java.util.ArrayList;
import java.util.List;

class Solution234 {
    // Helper class for linked channelList node
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }

    ListNode head;

    public void addAtTail(int val){
        /*
        初始状态：
        head ──→ [ListNode对象A]
                   ↑
                tailNode (指向同一个对象)
         */
        ListNode newNode = new ListNode(val);
        ListNode tailNode = head;
        //避免head為空
        if (head == null){
            head = newNode;
            return;
        }
        while (tailNode.next != null){
            tailNode = tailNode.next;//why??
        }
        tailNode.next = newNode;
        newNode.next = null;
    }

    public void printList(ListNode head) {
        Solution234.ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            current = current.next;
        }
        System.out.println("]");
    }

    public boolean isPalindrome(ListNode head) {
        //使用額外空間
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        if (current == null){return true;}

        while (current != null){
            list.add(current.val);
            current = current.next;
        }

        int len = list.size();
        int j=1;
        for (int i=0; i<len/2; i++){
            if (list.get(i).equals(list.get(len-j))){
                //System.out.println(channelList.get(i) +" is equal " + channelList.get(len-j));
                j++;
            }
            else { return false; }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution234 sol = new Solution234();
        int nums[] = {1, 2, 3, 2, 1};
        for (int i : nums){
            sol.addAtTail(i);
        }
        System.out.println("input = ");
        sol.printList(sol.head);
        boolean result = sol.isPalindrome(sol.head);
        System.out.println("isPalindrome result = " + result);
    }
}
/*
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。

输入：head = [1,2,2,1]
输出：true

输入：head = [1,2]
输出：false
 */