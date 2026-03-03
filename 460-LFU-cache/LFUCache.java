import java.util.HashMap;
import java.util.Map;

class Node{
    int key;
    int val;
    int freq;
    Node prev;
    Node next;
    Node(int key, int val, int freq){
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}
class LFUCache {
    int capacity;
    DoublyListNode dummyList;
    Map<Integer, Node> map;

    public LFUCache(int capacity) {
        dummyList = new DoublyListNode(0, 0);
        map = new HashMap<>();
        this.capacity = capacity;
    }
/*
    public int get(int key) {
        if (!map.containsKey(key)) {
            System.out.println(-1);
            return -1;
        }

        return
    }
 */


    public void put(int key, int val) {
        if (capacity == 0){ return; }
        if (map.containsKey(key)){
            DoublyListNode node = map.get(key);
            Node.val = val;
        }
        else {
            dummyList.addAtTail(key, val);
            map.put(key, dummyList.tail);
        }
        System.out.print(" map = " + map);
        System.out.print(" dummyList = ");
        dummyList.printList(dummyList.head);
        System.out.println(" ");
    }

    static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.put(1, 1);
//        lfu.get(1);      // 返回 1
//        // cache=[1,2], cnt(2)=1, cnt(1)=2
//        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//        // cache=[3,1], cnt(3)=1, cnt(1)=2
//        lfu.get(2);      // 返回 -1（未找到）
//        lfu.get(3);      // 返回 3
//        // cache=[3,1], cnt(3)=2, cnt(1)=2
//        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//        // cache=[4,3], cnt(4)=1, cnt(3)=2
//        lfu.get(1);      // 返回 -1（未找到）
//        lfu.get(3);      // 返回 3
//        // cache=[3,4], cnt(4)=1, cnt(3)=3
//        lfu.get(4);      // 返回 4
    }
}

/*
请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。

实现 LFUCache 类：

LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
int get(int key)- 如果键key 存在于缓存中，则获取键的值，否则返回 -1 。
void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。
当缓存达到其容量capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。

当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。

函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

*/