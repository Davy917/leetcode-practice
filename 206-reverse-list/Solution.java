import LinkedList.LinkedList;
import LinkedList.ListNode;

class Solution206 {
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }
    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null)
            return pre;
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

    static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        LinkedList ll = new LinkedList();
        ListNode head = ll.buildLinkedList(nums);
        ll.printList(head);
        Solution206 sol = new Solution206();
        ll.printList(sol.reverseList(head));
    }
}

/*
自己寫的:
狀態圖一定要自己在紙上畫

嘗試把迭代轉成遞歸
迭代:
ListNode pre = null, cur = head;
while(cur != null){
    ListNode temp = cur.next;
    cur.next = pre;
    pre = cur;
    cur = temp;
}

這行就是遞歸退出條件
cur != null

這兩行繞不過去, 一定要寫在遞歸裡
ListNode temp = cur.next;
cur.next = pre;

pre, cur一定要往前走, 所以傳參如下:
reverse(cur, temp)

完整寫法見上方
*/