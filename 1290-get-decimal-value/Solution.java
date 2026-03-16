//自己手寫的版本, 先知道整個鏈表的size才有辦法做運算
//另外一版, 不用知道鏈表的整個size也能求解, 用python寫
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
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }
}

class Solution1290 {
    public int getDecimalValue(ListNode head) {
        ListNode dummy = new ListNode(0, null, head);
        ListNode cur = dummy;
        int size = -1, result = 0;

        while (cur.next != null){
            cur = cur.next;
            System.out.println(cur.val);
            size++;
        }
        System.out.println(size);

        cur = dummy;
        while (cur.next != null){
            cur = cur.next;
            if (cur.val == 1){
                for (int j = size; j > 0; j--){
                    cur.val *= 2;
                }
            }
            size--;
            System.out.println(cur.val);
            result += cur.val;
        }

        System.out.println(result);
        return result;
    }

    static void main(String[] args) {
        int[] nums = {1, 0, 0, 1};
        LinkedList mainList = new LinkedList();
        for (int i : nums){
            ListNode newNode = new ListNode(i, null, null);
            mainList.AddAtTail(newNode);
        }
        Solution1290 sol = new Solution1290();
        sol.getDecimalValue(mainList.head);
    }
}