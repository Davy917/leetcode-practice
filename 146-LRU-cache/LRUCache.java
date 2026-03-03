import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    Node head;
    Node tail;
    int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    void deleteNode(Node node) {
        if (node == null){return;}
        if (node.prev != null){
            node.prev.next = node.next;
        }
        else {
            // node 是头节点，更新 dummyList.head
            head = node.next;
        }
        // 处理后继节点
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // node 是尾节点，更新 dummyList.tail
            tail = node.prev;
        }

        // 更新容量计数
        size--;
    }

    void addAtTail(int key, int val) {
        Node newNode = new Node(key, val);
        if (size == 0){
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        // 新节点连接到尾部
        newNode.prev = tail;
        newNode.next = null;

        // 尾节点的 next 指向新节点
        tail.next = newNode;

        // 更新 tail 指针
        tail = newNode;
        size++;
    }

    void printList(Node head){
        if (head == null) {
            System.out.print("[]");
            return;
        }

        Node current = head;
        System.out.print("[");

        while (current != null){
            System.out.print(current.key + ":" + current.val);
            current = current.next;
            if (current != null){
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}

class LRUCache {
    int capacity;
    DoublyLinkedList list;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new DoublyLinkedList();
        map = new HashMap<>();
    }

    public int get(int key) {
    //如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
        if (!map.containsKey(key)){
            System.out.println(-1);
            return -1;
        }
        Node node = map.get(key);
        list.deleteNode(node);  // 从当前位置删除
        list.addAtTail(key, node.val);  // 移到尾部（标记为最近使用）
        map.put(key, list.tail);  // 更新map中的引用
        System.out.println(list.tail.val);
        return list.tail.val;
    }

    public void put(int key, int val) {
    //從尾部插入，從頭部刪除

        //如果關鍵字key已經存在，則變更其數值
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.val = val;  // 更新值
            list.deleteNode(node);  // 从当前位置删除
            list.addAtTail(key, val);  // 移到尾部（最近使用）
            map.put(key, list.tail);  // 更新map中的引用
        }
        else {
            //關鍵字key不存在，且插入操作導致超出capacity上限，逐出最久未使用的关键字, 也就是DoublyListNode 的頭部
            if (map.size() >= capacity){
                map.remove(list.head.key);
                list.deleteNode(list.head);
            }
            //無論超出上限與否，都從尾部插入该组key-val
            list.addAtTail(key, val);
            map.put(key, list.tail);
        }
        System.out.print(" map = " + map);
        System.out.print(" List = ");
        list.printList(list.head);
        System.out.println(" ");
    }

    static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
        lRUCache.put(3, 1);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 * 请你设计并实现一个满足LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */

/*
为什么这样设计？
Node 只负责存储数据（key、val、prev、next）
DoublyLinkedList 负责链表的操作逻辑（增删、遍历）
LRUCache 负责缓存策略（LRU逻辑、HashMap管理）
 */