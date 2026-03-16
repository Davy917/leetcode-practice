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
}
class Solution876 {
    public ListNode middleNode(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode dummy = new ListNode(0, null, head);
        ListNode cur = dummy;
        int size = 0;
        while (cur.next != null){
            cur = cur.next;
            size++;
            System.out.println(size);
        }
        cur = dummy;
        int index = -1;
        while (cur.next != null){
            cur = cur.next;
            index++;
            System.out.printf("cur.val = %s ", cur.val);
            System.out.printf("index = %s%n", index);
            if (index >= size/2){
                System.out.printf("return cur %s", cur.val);
                break;
            }
        }
        return cur;
    }

    static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] nums2 = {1};
        LinkedList mainList = new LinkedList();
        for (int i : nums2){
            ListNode newNode = new ListNode(i, null, null);
            mainList.AddAtTail(newNode);
        }
        Solution876 sol = new Solution876();
        sol.middleNode(mainList.head);
    }
}