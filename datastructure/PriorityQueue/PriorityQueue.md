## 什麼是優先佇列 (Priority Queue)

**優先佇列 (Priority Queue)** 是一種特殊的佇列數據結構。在普通佇列中，元素遵循「先進先出」(FIFO) 的原則；而在優先佇列中，每個元素都有一個「優先級」。**優先級最高**的元素總是第一個被取出。

- [練習題](./PriorityQueue%20Practice%20list.md)

- **底層實現：** 通常使用 **堆 (Heap)** 實現，這使得插入和刪除（取出最大/最小元素）的操作時間複雜度均為 `O(log N)`。
- **應用場景：** 任務排程、Dijkstra 最短路徑算法、霍夫曼編碼、Top K 問題等。

---

### 優先隊列（Priority Queue）
- 是一個**抽象資料型別（ADT）**
- 定義的是行為：每次取出最高（或最低）優先權的元素
- 不規定底層怎麼實作

### 堆（Heap）
- 是一種**具體的資料結構**
- 是實作優先隊列最常見的方式

### 類比理解

| 概念 | 具體實作 |
|------|---------|
| 優先隊列（要什麼） | 堆（怎麼做） |
| 排序（要什麼） | 快速排序、合併排序（怎麼做） |

就像「交通工具」和「汽車」的關係——汽車是交通工具的一種，但交通工具也可以是飛機、火車。

### 優先隊列的其他實作方式
除了堆，還可以用：
- **有序陣列**（插入 O(n)，取出 O(1)）
- **有序鏈結串列**
- **二元搜尋樹**

但堆是最常用的，因為插入和取出都是 **O(log n)**，效率最均衡。

所以在面試中說「用優先隊列來解」，實作時通常就是用堆 😄
### 各語言中的 Priority Queue 使用方式

#### 1. Java
Java 內建了 `java.util.PriorityQueue`。
- **特性：** 默認為 **最小堆 (Min-Heap)**（根節點為最小元素）。

```java
import java.util.PriorityQueue;
import java.util.Collections;

// 1. 最小堆 (默認)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// 2. 最大堆 (自定義比較器)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// 常用操作
minHeap.offer(10); // 插入
int val = minHeap.poll(); // 取出並移除頭部
int peekVal = minHeap.peek(); // 查看頭部但不移除
```

#### 2. JavaScript
JavaScript **沒有內建**的 `PriorityQueue`，在 LeetCode 中通常需要手動實現一個二元堆積，或者使用簡單的排序處理（效率較低）。

- **自行實現 (簡版思路)：** 通過陣列模擬二元堆積結構，手動編寫 `push` (`siftUp`) 和 `pop` (`siftDown`) 方法。
- **簡單處理：** 若數據量小，可以使用 `array.sort()` 或插入排序維護已排序陣列，但時間複雜度為 `O(N)`。

```javascript
// 簡單示例 (Min-Heap)
class MinHeap {
    constructor() { this.heap = []; }
    push(val) { /* 實作 siftUp */ }
    pop() { /* 實作 siftDown */ }
    peek() { return this.heap[0]; }
}
```

#### 3. Python
Python 使用內建的 `heapq` 模組。
- **特性：** 默認為 **最小堆 (Min-Heap)**。

```python
import heapq

# 創建
pq = []

# 插入
heapq.heappush(pq, 10)
heapq.heappush(pq, 5)

# 取出最小元素
min_val = heapq.heappop(pq)

# 查看最小元素
min_val = pq[0]

# Python 實現最大堆的技巧：存入元素的相反數
heapq.heappush(pq, -10)
max_val = -heapq.heappop(pq)
```

#### 4. Go
Go 使用標準庫中的 `container/heap`。
- **特性：** 需要自己定義一個類型並實現 `heap.Interface` 中的 `Len`, `Less`, `Swap`, `Push`, `Pop` 五個方法。

```go
import (
    "container/heap"
)

// 定義一個 IntHeap 類型
type IntHeap []int

// 實現 heap.Interface
func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] } // < 為最小堆, > 為最大堆
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) { *h = append(*h, x.(int)) }
func (h *IntHeap) Pop() interface{} {
    old := *h
    n := len(old)
    x := old[n-1]
    *h = old[0 : n-1]
    return x
}

// 使用
h := &IntHeap{2, 1, 5}
heap.Init(h)
heap.Push(h, 3)
val := heap.Pop(h)
```