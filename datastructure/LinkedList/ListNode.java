package LinkedList;

public class ListNode {
    /**
     * ListNode next; 是宣告一個參考（reference）欄位，類型是 ListNode。
     * 它本身不會建立新的節點物件，初始值會是 null（若未賦值）。
     * 當你用 new 建立另一個節點，或把已存在的節點指派給 next，該欄位才會引用（指向）那個節點。
     */
    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}