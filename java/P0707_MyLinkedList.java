class ListNode {
    /**
     * ListNode next; 是宣告一個參考（reference）欄位，類型是 ListNode。
     * 它本身不會建立新的節點物件，初始值會是 null（若未賦值）。
     * 當你用 new 建立另一個節點，或把已存在的節點指派給 next，該欄位才會引用（指向）那個節點。
     */
    ListNode next;
    int val;
    ListNode(int x){val = x;}//每當建立新node，從外面傳值進來
}

public class P0707_MyLinkedList{
    /**
     * head; 是型別為 ListNode 的參考（reference）欄位，儲存「指向」鏈表第一個節點物件的參考；它本身不是節點物件。
     * 預設值為 null，只有在用 new 建立節點或將已有節點指派給 head 時，head 才會指向那個節點。
     */
    ListNode head;

    public int get(int index){

        ListNode visitor = head;
        int counter = 0;

        while (counter != index && visitor != null){
            visitor = visitor.next;
            counter++;
        }
        return visitor == null ? -1 : visitor.val;
    }

    public void addAtHead(int val){
        ListNode newNode = new ListNode(val);
        newNode.next = this.head;
        this.head = newNode;
    }

    public void addAtTail(int val){
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

    public void addAtIndex(int index, int val){
        ListNode newNode = new ListNode(val);
        ListNode visitor = head; //visitor是index的前一個node
        if (index == 0){addAtHead(val); return;}//預防機制

        int counter = 0;
        while (counter != index-1 && visitor != null){
            visitor = visitor.next;
            counter++;
        }
        if (visitor == null){return;}
        newNode.next = visitor.next;
        visitor.next = newNode;
    }

/**
* 會發生 visitor == null 的情況：
* 鏈表為空：head == null，所以一開始 visitor = head 就是 null。
* 傳入的 index 大於或等於鏈表長度：遍歷時會走到最後一個節點的 next（即 null），下一次賦值 visitor = visitor.next 後變為 null。
* 傳入負數 index：因為 counter 從 0 開始，counter != index-1 可能永遠為真，迴圈會持續移動直到走出鏈表使 visitor 變成 null。
* 範例（鏈表長度 3，index = 5）：遍歷到最後節點後，visitor.next 為 null，接著 visitor = visitor.next 使 visitor == null。

假設index = 2

                    visitor     index = 2
                       ↓           ↓
head--->[A | -]---> [B | -]---> [C | -]---> [D | ]

                        [newNode | ]
*/
    public void deleteAtHead(){
        if (head == null){return;}
        this.head = head.next;
    }

    public void deleteAtTail(){
        ListNode tailNode = head;
        if (head == null){return;}
        while (tailNode.next != null){
            if (tailNode.next.next == null){
                tailNode.next =null;
                return;
            }
            tailNode = tailNode.next;
        }
    }
    public void deleteAtIndex(int index){
        if (index == 0){ deleteAtHead(); return; }
        if (head == null){return;}
        ListNode visitor = head; //visitor是index的前一個node

        int counter = 0;
        while (counter != index-1 && visitor.next != null){
            visitor = visitor.next;
            counter++;
        }
        if (visitor.next == null){return;}
        visitor.next = visitor.next.next;
    }

/**
 * 當 visitor.next == null 表示 visitor 已經是鏈表的最後一個節點（沒有下一個節點）。會在以下情況發生：
 * - 鏈表只有一個節點且要刪除的 index > 0（例如刪除 index == 1），此時 visitor = head 且 visitor.next == null。
 * - 遍歷過程中已到達尾節點，但還沒達到 index-1，也就是傳入的 index 超過或等於鏈表長度，迴圈會在遇到尾節點時停止。
 * - 若傳入負數 index（未檢查負值），迴圈同樣可能走到尾端而使 visitor.next == null。
 * 結果是迴圈會終止，後續的 if (visitor.next == null) { return; } 會阻止執行刪除操作（因為目標索引不存在）。
 */

    static void main(String[] args) {
        P0707_MyLinkedList myLinkedList = new P0707_MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
        myLinkedList.get(1);              // 返回 2
        myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
        myLinkedList.get(1);              // 返回 3
    }
}



/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
/*
什麼情況下visitor == null
鏈表為空（head == null），visitor 一開始就是 null。
指定的 index 超過或等於鏈表長度，遍歷時會走到最後一個節點的 next（即 null）後仍未達到目標索引。
若傳入負數 index，counter 永遠不會等於 index，迴圈會一直走到 visitor 變為 null。
簡單說就是：遍歷已走出鏈表尾端（index 超出範圍 或 空表）。
 */