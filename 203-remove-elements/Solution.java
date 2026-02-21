class Solution203 {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){ this.val = val; }
    }

    ListNode head;
    /**
     * head ──→ [ListNode对象A]
     * 变量本身：存储的是对象的内存地址（指针）
     * 变量指向的内容：是整个链表的第一个节点
     * 通过这个变量：可以遍历访问整个链表
     * 所以，head 既是一个指针（引用），又代表了整个链表的起点。两种说法都对，只是角度不同。
     */

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

    public ListNode removeElements(ListNode head, int val) {
        //處理頭節點，為val的情形
        while (head != null && head.val == val){ head = head.next; }

        //頭節點處理完了，但是鏈表也空了
        if (head == null) { return null; }//當鏈表為空時

        //頭節點處理完了，但是鏈表還沒空
        ListNode current = head;
        while (current.next != null){
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    // 辅助方法：打印链表
    public void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    static void main(String[] args) {
        Solution203 sol = new Solution203();
        int val = 6;
        //int[] nums = {1, 2, 6, 3, 4, 5, 6};
        int[] nums = {6, 5, 3, 6, 7};
        for (int i : nums){
            sol.addAtTail(i);
        }
        System.out.println("As is = ");
        sol.printList(sol.head);

        ListNode newHead = sol.removeElements(sol.head, val);//??
        System.out.println("to be = ");
        sol.printList(newHead);
        /**
         * addAtTail 的最後兩句並沒有改動ListNode head,
         * 但是我們在最後輸出的時候sol.printList(sol.head);
         * 卻能夠打印出鏈表中的所有值, 請問是如何做到的
         * 初始状态：
         * head ──→ [ListNode对象A]
         *               ↑
         *           tailNode (指向同一个对象)
         * 执行 tailNode = tailNode.next 后：
         * head ──→ [ListNode对象A] ──→ [ListNode对象B]
         *                                 ↑
         *                              tailNode
         * 执行 tailNode.next = newNode 后：
         * head ──→ [ListNode对象A] ──→ [ListNode对象B] ──→ [newNode]
         * 虽然 addAtTail 方法没有直接修改 head 变量，但它修改的是 head 指向的对象的内部状态（即修改了对象的 next 字段）。
         * 因此，通过 head 引用仍然能访问整个链表的所有节点。这就是引用的威力！
         */
    }
}

/**
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * head--->[1 | -]---> [2 | -]---> [6 | -]---> [3 | -]---> [4 | -]---> [5 | -]---> [6 | ]
 *
 * 输出：[1,2,3,4,5]
 * head--->[1 | -]---> [2 | -]---> [3 | -]---> [4 | -]---> [5 | ]
 */

/*
正確版本:
public ListNode removeElements(ListNode head, int val) {
    // 1. 处理头节点：如果头节点的值等于 val，移动 head
    while (head != null && head.val == val) {
        head = head.next;
    }

    // 2. 如果链表已经为空，直接返回
    if (head == null) {
        return null;
    }

    // 3. 处理中间和尾部节点
    ListNode current = head;
    while (current.next != null) {
        if (current.next.val == val) {
            // 删除 current.next 节点
            current.next = current.next.next;
        } else {
            // 只有在不删除时才移动指针
            current = current.next;
        }
    }

    return head;
}
 */