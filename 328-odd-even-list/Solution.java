/*
第一次練習看python
第二次練習看java
 */
import LinkedList.ListNode;
import LinkedList.LinkedList;
class Solution328 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode odd = head, even = head.next, evenHead = head.next;
        while (odd.next != null && odd.next.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        LinkedList ll = new LinkedList();
        ListNode head = ll.buildLinkedList(nums);
        ll.printList(head);
        Solution328 sol = new Solution328();
        ll.printList(sol.oddEvenList(head));
    }
}
/*
自己寫的:
要求, 時間O(n), 空間O(1)
想到什麼? --> 雙指針

不斷地做這件事:
奇數, 斷開偶數節點, 連到下個奇數
偶數, 斷開奇數節點, 連到下個偶數

寫一個迴圈不斷重複, 迴圈結束之後讓最後一個 odd 連上第一個 even 就可以 return 了
*/
