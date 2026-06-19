//使用額外空間Map，非一次遍歷
import java.util.HashMap;
import java.util.Map;

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
    public ListNode deleteDuplicatesWithDummy(ListNode head) {

        if (head == null) {
            return null;
        }

        // 1) count frequency
        Map<Integer, Integer> map = new HashMap<>();
        ListNode current = head;
        while (current != null) {
            map.put(current.val, map.getOrDefault(current.val, 0) + 1);
            current = current.next;
        }

        /**
         * dummy：假頭節點，固定存在，dummy.next = head
         * prev：永遠指向「目前結果鏈表的最後一個保留節點」
         * curr：掃描原鏈表的游標
         */

        // 2) rebuild links in-place using dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (map.get(curr.val) > 1) {
                // remove curr
                prev.next = curr.next;
            } else {
                // keep curr
                prev = curr;
            }
            curr = curr.next;
        }

        return dummy.next;
    }

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
        for (int i : nums){ sol.addAtTail(i); }
        sol.printList(sol.head);
        //ListNode result = sol.deleteDuplicates(sol.head);
        ListNode result = sol.deleteDuplicatesWithDummy(sol.head);
        sol.printList(result);
    }
}
/**deleteDuplicatesWithDummy
 * 初始化：
 * dummy -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> null
 * prev
 * curr  (curr=head)
 *
 * Step 1：curr = 1（map[1]=1，保留）
 * dummy -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 *          prev
 *               curr
 *
 * Step 2：curr = 2（map[2]=1，保留）
 * dummy -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 *               prev
 *                    curr
 * Step 3：curr = 第一個 3（map[3]=2，刪除）
 * 刪除時做：prev.next = curr.next
 * 此刻 prev 在 2，curr 在第一個 3
 * 所以把 2.next 從「指向 3」改成「指向下一個節點」（也就是第二個 3）
 * dummy -> 1 -> 2 ------> 3 -> 4 -> 4 -> 5
 *               prev      curr(第二個3)
 *
 *
 * Step 4：curr = 第二個 3（map[3]=2，仍刪除）
 * 再做一次：prev.next = curr.next
 *
 * 把 2.next 從第二個 3 改指向 4
 * dummy -> 1 -> 2 ------> 4 -> 4 -> 5
 *               prev      curr(第一個4)
 * 到這裡，「值為 3 的整段」全部被移除。
 *
 * Step 5：curr = 第一個 4（map[4]=2，刪除）
 * prev 還在 2，執行：prev.next = curr.next 把 2.next 指向第二個 4
 * dummy -> 1 -> 2 ------> 4 -> 5
 *               prev      curr(第二個4)
 *
 * Step 6：curr = 第二個 4（map[4]=2，刪除）
 * 再做：prev.next = curr.next 把 2.next 指向 5
 * dummy -> 1 -> 2 ------> 5
 *               prev      curr(5)
 *
 * Step 7：curr = 5（map[5]=1，保留）
 * 保留：prev = curr
 * dummy -> 1 -> 2 -> 5 -> null
 *                    prev
 *                    curr
 * curr 走到 null 結束。
 *
 * dummy 為何好用（重點）
 * 如果「開頭就要刪」也能一致處理，例如： 1 -> 1 -> 2 -> 3
 * 你用 dummy 後，刪掉開頭的 1 時只需要改 dummy.next，不用寫一堆特判 head 的程式。
 */