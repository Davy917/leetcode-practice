import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode prev;
    ListNode next;

    ListNode(int val, ListNode prev, ListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}

class LinkedList {
    ListNode head;
    ListNode tail;

    void AddAtTail(ListNode newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            System.out.printf("add %s at head%n", newNode.val);
            return;
        }
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
        System.out.printf("add %s at tail%n", newNode.val);
    }

    void PrintList(ListNode head){
        if (head == null){return;}
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        list.addLast(cur.val);
        while (cur.next != null){
            cur = cur.next;
            list.addLast(cur.val);
        }
        System.out.println(list);
    }
}

class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList_v2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        return reverse(pre, cur);
    }

    public ListNode reverse(ListNode pre, ListNode cur){
        if (cur == null){
            return pre;
        }
        ListNode temp;
        temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }
    static void main(String[] args) {
        LinkedList mainList = new LinkedList();
        int[] nums = {1, 2, 3};
        for (int i : nums) {
            ListNode newNode = new ListNode(i, null, null);
            mainList.AddAtTail(newNode);
        }
        Solution206 sol = new Solution206();
        //mainList.head = sol.reverseList(mainList.head);
        mainList.head = sol.reverseList_v2(mainList.head);
        mainList.PrintList(mainList.head);
    }
}

    /*
    1, 2, 3

     pre       cur       temp
     ↓          ↓         ↓
    [None|]   [1|-]--->[2|-]--->[3|-]

     pre       cur       temp
     ↓          ↓         ↓
    [None|]<---[-|1]    [2|-]--->[3|-]

               pre
               cur       temp
                ↓         ↓
    [None|]<---[-|1]    [2|-]--->[3|-]

               pre        cur
                          temp
                ↓         ↓
    [None|]<---[-|1]    [2|-]--->[3|-]

               pre        cur
                                 temp
                ↓         ↓        ↓
    [None|]<---[-|1]    [2|-]--->[3|-]

               pre        cur
                                 temp
                ↓         ↓        ↓
    [None|]<---[-|1] <---[-|2]    [3|-]

                          cur
                         pre      temp
                ↓         ↓        ↓
    [None|]<---[-|1] <---[-|2]    [3|-]

                                  cur
                         pre      temp
                ↓         ↓        ↓
    [None|]<---[-|1] <---[-|2]    [3|-]

                                  cur
                         pre               temp
                          ↓        ↓        ↓
    [None|]<---[-|1] <---[-|2]    [3|-]--->None


                                  cur
                         pre               temp
                          ↓        ↓        ↓
    [None|]<---[-|1] <---[-|2] <---[-|3]   None

                                  cur
                                   pre     temp
                                   ↓        ↓
    [None|]<---[-|1] <---[-|2] <---[-|3]   None

                                           cur
                                   pre     temp
                                   ↓        ↓
    [None|]<---[-|1] <---[-|2] <---[-|3]   None
     */