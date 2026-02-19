class P0706_MyHashMap {

    static class ListNode{
        int key;
        int val;
        ListNode next;
        ListNode(int key, int val, ListNode next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    ListNode[] bucketSet;//每個bucket是一個ListNode
    private static final int CAPACITY = 10000;//宣告靜態常量, 把桶的總數寫死

    public P0706_MyHashMap() {
        bucketSet = new ListNode[CAPACITY];//創建10000個空bucket
    }

    int hashFunction(int key){
        return key % CAPACITY;//取餘運算, 決定key放在哪個bucket
    }
//-------------------------------------------------------
    public int get(int key) {
        int bucketIndex = hashFunction(key);//呼叫hashFunction,計算bucket編號, 把結果存入bucketIndex
        ListNode bucketList = bucketSet[bucketIndex];
        while (bucketList != null){
            if (bucketList.key == key){
                return bucketList.val;
            }
            bucketList = bucketList.next;//這行賦值把區域變數 bucketList 的參考從目前節點「移動」到該節點的 next 參考：不會複製或修改節點本身，只改變指向哪個物件。
        }
        return  -1;
    }

    public void put(int key, int val) {

        remove(key);// 先删除已存在的 key（如果有）
        int bucketIndex = hashFunction(key);
        ListNode bucketList = new ListNode(key, val, bucketSet[bucketIndex]);//創建新節點
        bucketSet[bucketIndex] = bucketList;//把新節點,插入鏈表頭
    }

    public void remove(int key) {

        int bucketIndex = hashFunction(key);
        ListNode bucketList = bucketSet[bucketIndex];
        if (bucketList == null){return;}
        if (bucketList.key == key){
            bucketSet[bucketIndex] = bucketList.next;//指向新的引用來跳過目標節點，從而實現刪除
            return;
        }
        while (bucketList.next != null){
            if (bucketList.next.key == key){
                bucketList.next = bucketList.next.next;
                return;
            }
            bucketList = bucketList.next;
        }
    }

    static void main(String[] args) {

    }
}

/**
 * bucketList-->node
 * bucketSet-->map
 输入：
 ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 输出：
 [null, null, null, 1, -1, null, 1, null, -1]

 一張圖帶過鏈表:
 head
 ↓
 ┌──────────┐      ┌──────────┐      ┌──────────┐
 │key:  10  │      │ key: 110 │      │ key: 210 │
 │ val: 100 │  ──→ │ val: 200 │  ──→ │ val: 300 │  ──→ null
 │ next: ───┼──┐   │ next: ───┼──┐   │ next: ───┼──┐
 └──────────┘  │   └──────────┘  │   └──────────┘  │
               └─────────────────┘                 └──→ null


 關於Remove方法
 刪除中間節點為何是 bucketList.next = bucketList.next.next

 bucketSet[]
 map[]
 ┌─────┬────────────┐
 │  0  │ → head     │
 └─────┴───┼────────┘
           ↓
 ┌─────────┐     ┌─────────┐     ┌─────────┐
 │ key: 5  │     │ key:105 │     │ key:205 │
 │ val:  50│  →  │ val:150 │  →  │ val:250 │  → null
 │ next: ──┼──┐  │ next: ──┼──┐  │ next: ──┼──┐
 └─────────┘  │  └─────────┘  │  └─────────┘  │
 node         └──→ node.next  └───────────────┘
 (前一个节点)      (目标节点)      node.next. next

 關於put方法演示
 MyHashMap bucketSet = new MyHashMap();

示例 1: 插入新键值对
 bucketSet.put(7, 70);
  链表: [7,70] → null

 bucketSet.put(107, 170);
  链表: [107,170] → [7,70] → null (头插法)

 bucketSet.put(207, 270);
  链表: [207,270] → [107,170] → [7,70] → null

示例 2: 更新已存在的键
 bucketSet.put(107, 999);
  ① remove(107): [207,270] → [7,70] → null
  ② 创建新节点: [107,999]
  ③ 头插法: [107,999] → [207,270] → [7,70] → null
 */