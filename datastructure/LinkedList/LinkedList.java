package LinkedList;

public class LinkedList{
    static ListNode head;
    static int size;
    public void addAtHead(int val){
        addAtIndex(0, val);
    }

    public void addAtTail(int val){
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val){
        if (index > size)
            return;
        ListNode dummy = new ListNode(-1, head);
        ListNode newNode = new ListNode(val, null);
        ListNode cur = dummy;
        int j = 0;
        while (j < index) {
            cur = cur.next;
            j++;
        }
        if (index == 0){
            dummy.next = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            ListNode temp = cur.next;
            cur.next = newNode;
            newNode.next = temp;
        }
        size++;
    }

    public void deleteAtIndex(int index){
        if(index < 0 || index >= size)
            return;
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        int j = 0;
        while (j < index){
            cur = cur.next;
            j++;
        }
        if (index == 0){
            head = head.next;
            dummy.next = head;
        } else {
            if (cur.next != null && cur.next.next != null)
                cur.next = cur.next.next;
            else
                cur.next = null;
        }
        size--;
    }

    public ListNode buildLinkedList(int[] nums) {
        LinkedList.head = null; // 很重要，避免殘留上次資料
        for (int num : nums) {
            addAtTail(num);
        }
        return head;
    }
    public void printList(ListNode head){
        System.out.print("[");
        ListNode cur = head;
        while (cur != null){
            System.out.printf("%d ", cur.val);
            cur = cur.next;
        }
        System.out.println("]");
    }
}



/**
 * 在 deleteAtIndex，需刪除的是 visitor.next，因此迴圈必須保證 visitor.next 存在，避免對 visitor.next.next 解參考時為 null。
 * 在 addAtIndex，需要找到前一個節點，允許 visitor走到 null以判斷索引超出範圍，所以檢查 visitor != null 即可。
 */
