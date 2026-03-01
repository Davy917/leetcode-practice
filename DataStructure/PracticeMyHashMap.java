import java.security.Key;

public class PracticeMyHashMap {

    private static class ListNode{
        int key;
        int val;
        ListNode next;
        ListNode(int key, int val, ListNode next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    private static final int CAPACITY = 10000;//宣告靜態常量，把桶的總數寫死
    ListNode[] bucketSet;// bucketSet 是一個陣列，陣列中每一格儲存的是對 ListNode 物件的參考（reference）

    int hashFunction(int key){
        return CAPACITY % key;
    }

    PracticeMyHashMap(){
        bucketSet = new ListNode[CAPACITY];//創建10000個空bucket
    }

    int get(int key){
        int bucketIndex = hashFunction(key);
        ListNode bucketList  = bucketSet[bucketIndex];//bucketList 是一個區域變數，用來儲存 bucketSet[bucketIndex] 的參考
        while (bucketList != null){
            if (bucketList.key == key){
                return bucketList.val;
            }
            bucketList = bucketList.next;
        }
        return -1;
    }

    void put(int key, int val){
        remove(key);
        int bucketIndex = hashFunction(key);
        ListNode bucketList = new ListNode(key, val, bucketSet[bucketIndex]);
        bucketSet[bucketIndex] = bucketList;
    }

    void remove(int key) {
        int bucketIndex = hashFunction(key);
        ListNode bucketList = bucketSet[bucketIndex];
        if (bucketList == null){return;}
        if (bucketList.key == key){
            bucketSet[bucketIndex] = bucketList.next;
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
}
/**
在 Java 裡 ListNode 是參考型別，ListNode bucketList = bucketSet[bucketIndex];
這行會把陣列中該位置的參考複製到 bucketList。若陣列該元素為 null，bucketList 也會是 null。
對 bucketList.next 或其他欄位的修改會影響陣列中的節點，但如果把 bucketList 指向新的物件（例如 bucketList = new ListNode(...)），
不會改變 bucketSet[bucketIndex]，除非你再執行 bucketSet[bucketIndex] = bucketList;。

 拆解如何創建新節點
 ListNode bucketList = new ListNode(key, val, bucketSet[bucketIndex]);//創建新節點
 // ↓              ↓    ↓              ↓    ↓    ↓
 // 类型         变量名  创建对象      参数1  参数2  参数3

 第一步假設初始狀態:
 // 已有的链表
 bucketSet[0] = null
 bucketSet[1] = [6,60] → [106,160] → null
 bucketSet[2] = [7,70] → null

 // 现在要执行
 int key = 107, val = 999;
 int bucketIndex = 1;  // hashFunction(107) = 1
 ListNode bucketList = new ListNode(key, val, bucketSet[bucketIndex]);

 步骤 ① 计算参数值
 new ListNode(key, val, bucketSet[bucketIndex])
               ↓   ↓         ↓
 new ListNode(107, 999, bucketSet[1])
                             ↓
 new ListNode(107, 999, [6,60]节点的引用)
 图示：
 bucketSet[1] 指向:
 ┌──────────┐     ┌──────────┐
 │ key:  6  │     │ key: 106 │
 │ val: 60  │  →  │ val:160  │  → null
 └──────────┘     └──────────┘
       ↑
 bucketSet[1] 存储的是这个节点的内存地址

 步骤 ② 调用构造函数
ListNode(int key, int val, ListNode next) {
 this.key = key;      // ← 接收参数 107
 this.val = val;      // ← 接收参数 999
 this.next = next;    // ← 接收参数 bucketSet[1]（[6,60]节点的引用）
 }
 执行过程：
 在堆内存中创建新对象:

 ┌─────────────────┐
 │   ListNode      │  ← 新分配的内存空间
 ├─────────────────┤
 │ key:   ?        │  ← 未初始化
 │ val:   ?        │  ← 未初始化
 │ next:  ?        │  ← 未初始化
 └─────────────────┘

 ↓ 执行 this.key = key (107)

 ┌─────────────────┐
 │   ListNode      │
 ├─────────────────┤
 │ key:   107      │  ✅
 │ val:   ?        │
 │ next:  ?        │
 └─────────────────┘

 ↓ 执行 this.val = val (999)

 ┌─────────────────┐
 │   ListNode      │
 ├─────────────────┤
 │ key:   107      │  ✅
 │ val:   999      │  ✅
 │ next:  ?        │
 └─────────────────┘

 ↓ 执行 this.next = next (bucketSet[1])

 ┌─────────────────┐
 │   ListNode      │
 ├─────────────────┤
 │ key:   107      │  ✅
 │ val:   999      │  ✅
 │ next:  ─────────┼───→ [6,60] → [106,160] → null
 └─────────────────┘  ✅

 步骤 ③ 返回对象引用
 ListNode bucketList = new ListNode(...);
 //           ↑                    ↑
 //         变量名            返回的内存地址

 栈内存 (Stack):
 ┌────────────────┬─────────────┐
 │ bucketList     │ 0x1A2B3C4D  │  ← 存储新节点的内存地址
 └────────────────┴─────────────┘

 堆内存 (Heap):
 地址: 0x1A2B3C4D
 ┌───────────────────┐
 │ key:   107        │
 │ val:   999        │
 │ next:  0x5E6F7G8H │  ← 指向 [6,60] 节点的地址
 └───────────────────┘
 ↓
 地址: 0x5E6F7G8H
 ┌───────────────────┐
 │ key:   6          │
 │ val:   60         │
 │ next:  0x9I0J1K2L │  ← 指向 [106,160] 节点
 └───────────────────┘
 ↓
 ...
 完整可视化
 bucketSet[]
 ┌─────┬────────────────────────────┐
 │  1  │ → head                     │
 └─────┴───┼────────────────────────┘
           ↓
 ┌──────────┐     ┌──────────┐
 │ key:  6  │     │ key: 106 │
 │ val: 60  │  →  │ val:160  │  → null
 └──────────┘     └──────────┘
 bucketSet[1]

 执行 new ListNode(107, 999, bucketSet[1])：
 ① 参数准备:
 key = 107
 val = 999
 next = bucketSet[1] (指向 [6,60])

 ② 创建新节点:
 ┌──────────┐
 │ key: 107 │
 │ val: 999 │
 │ next: ───┼────→ [6,60] → [106,160] → null
 └──────────┘
 bucketList
 (新创建的节点)

 ③ 结果:
 bucketList ──→ [6,60] → [106,160] → null
 (新节点成为新的链表头)

 执行后（如果再执行 bucketSet[1] = bucketList）：
 bucketSet[]
 ┌─────┬────────────────────────────┐
 │  1  │ → 新的 head                 │
 └─────┴───┼────────────────────────┘
           ↓
 ┌──────────┐     ┌──────────┐     ┌──────────┐
 │ key: 107 │     │ key:  6  │     │ key: 106 │
 │ val:  999│  →  │ val: 60  │  →  │ val:160  │  → null
 └──────────┘     └──────────┘     └──────────┘
 bucketList        原来的头节点
 (新节点)

 我的總結:
 所以做法大概就是在堆內先創建一個ListNode物件，
 然後再用變量名bucketList存取該物件地址，
 最後只需要把bucketSet[bucketIndex]裡面裝的地址改成bucketList現在指向的地址，
 就完成插入了
 */