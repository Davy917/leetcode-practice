package LinkedList;

public class DoublyListNode{
    int key;
    int val;
    int size;
    DoublyListNode next, prev;
    DoublyListNode head, tail;
    DoublyListNode(int key, int val){
        this.key = key;
        this.val = val;
    }

    public int get(int index){
        if (index < 0 || index >= size){
            return -1;
        }
        int counter = 0;
        DoublyListNode visitor = head;
        while (counter != index){
            visitor = visitor.next;
            counter++;
        }
        return visitor.val;
    }
    public void addAtHead(int key, int val){
        DoublyListNode newNode = new DoublyListNode(key, val);
        if (size == 0){
            this.head = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }
        newNode.next = this.head;
        this.head.prev = newNode;
        this.head = newNode;
        this.size++;
    }
    public void addAtTail(int key, int val){
        DoublyListNode newNode = new DoublyListNode(key, val);
        if (size == 0){ addAtHead(key, val); return; }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        this.size++;
    }
    public void addAtIndex(int index, int val){
        //index的三種邊界情形
        if (index < 0 || index > this.size){ return; }
        if (index == 0){ addAtHead(key, val); return; }
        if (index == size){ addAtTail(key, val); return;}
        DoublyListNode newNode = new DoublyListNode(key, val);
        DoublyListNode visitor = head;
        int counter = 0;
        while (counter != index){
            visitor = visitor.next;
            counter++;
        }
        //現在visitor已經在index為下標的節點了
        newNode.next = visitor;
        newNode.prev = visitor.prev;
        visitor.prev.next = newNode;
        visitor.prev = newNode;
        this.size++;
    }
    public void deleteAtHead(){
        if (this.size <= 1){
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }
        this.head.next.prev = null;
        this.head = head.next;
        this.size--;
    }
    public void deleteAtTail(){
        if (this.size <= 1){ deleteAtHead();return; }
        this.tail.prev.next = null;
        this.tail = this.tail.prev;
        this.size--;
    }
    public void deleteAtIndex(int index){
        if (index < 0 || index >= this.size){ return; }//注意
        if (index == 0){ deleteAtHead();return; }
        if (index == this.size-1){ deleteAtTail();return; }//注意
        int counter = 0;
        DoublyListNode visitor = head;
        while (counter < index){
            visitor = visitor.next;
            counter++;
        }
        //現在visitor已經在index為下標的節點了
        visitor.next.prev = visitor.prev;
        visitor.prev.next = visitor.next;
        this.size--;
    }

    public void deleteNode(DoublyListNode node) {
        if (node == null || node == head || node == tail) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void printList(DoublyListNode head){
        DoublyListNode current = head;
        System.out.print("[");

        while (current != null){
            System.out.print(current.key + " " + current.val);
            if (current.next != null){
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.print("]");
    }
}

/*
addAtIndex(int index, int val)
1.操作本質： 在指定 index 的位置之前插入一個新節點。
2.有效 index 範圍： [0, size]
    (1) index = 0：在當前頭部之前插入，成為新的頭部。
    (2) 0 < index < size：在鏈表中間的某個位置插入。
    (3) index = size：在當前尾部之後插入，成為新的尾部。這相當於在邏輯上鏈表的末尾插入。
當 index == size 時，表示您想在所有現有元素之後插入新元素。這正是 addAtTail() 的功能。

例如:
一個大小為 3 的鏈表（索引 0, 1, 2）： A <-> B <-> C addAtIndex(3, X)
意味著將 X 插入到 C 的後面，成為新的尾部： A <-> B <-> C <-> X

deleteAtIndex(int index)
1. 操作本質： 刪除指定 index 位置上的現有節點。
2. 有效 index 範圍： [0, size - 1]
    (1) index = 0：刪除頭部節點。
    (2) 0 < index < size - 1：刪除鏈表中間的某個節點。
    (3) index = size - 1：刪除尾部節點。
當 index == size - 1 時，表示您想刪除鏈表中最後一個現有元素。這正是 deleteAtTail() 的功能。

例如:
一個大小為 3 的鏈表（索引 0, 1, 2）： A <-> B <-> C deleteAtIndex(2)
意味著刪除索引為 2 的節點，也就是 C，這就是刪除尾部： A <-> B

為什麼 addAtIndex 可以接受 index == size，而 deleteAtIndex 不行？
根本原因在於：
1.插入操作可以在現有元素的之間或兩端進行，包括「末尾之後」這個概念。所以 index 可以等於 size，表示在邏輯上最後一個元素的後面。
2.刪除操作只能針對已經存在的元素進行。如果 index == size，那麼這個索引位置上並沒有任何元素可以刪除。鏈表中合法的索引範圍是 0 到 size - 1。

所以，deleteAtIndex 判斷 index == this.size - 1 為邊界，是因為這是它能刪除的最大有效索引。
再往上一個索引（即 size），就已經超出了鏈表的實際範圍，沒有元素可以刪除了。

這兩種方法對 index 邊界處理的不同，完美地反映了它們所執行操作的邏輯差異。
*/