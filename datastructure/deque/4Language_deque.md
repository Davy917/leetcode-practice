這是一個關於在 Java、Python、JavaScript 和 Go 中實作佇列（Queue）的對比。佇列是一種先進先出（FIFO, First-In-First-Out）的資料結構。

### 語言對比總覽

| 語言 | 推薦實作方式 | 新增（入隊） | 刪除（出隊） | 取得頭部（Peek） |
| :--- | :--- | :--- | :--- | :--- |
| **Java** | `java.util.ArrayDeque` | `.offer()` | `.poll()` | `.peek()` |
| **Python** | `collections.deque` | `.append()` | `.popleft()` | `q[0]` |
| **JavaScript** | `Array` | `.push()` | `.shift()` | `q[0]` |
| **Go** | `slice` | `append()` | `queue = queue[1:]` | `queue[0]` |

---

### 詳細實作範例

#### 1. Java
Java 建議使用 `ArrayDeque` 或 `LinkedList`。在 LeetCode 中，`ArrayDeque` 通常效能較好。

java, python 實戰優先看 239-max-sliding-window 再來看 1438-longest-subarray

```java
import java.util.ArrayDeque;
import java.util.Queue;

// 宣告
Queue<Integer> queue = new ArrayDeque<>();

// 入隊 (尾部添加)
queue.offer(1);
queue.offer(2);

// 出隊 (頭部移除)
int val = queue.poll(); // 返回 1

// 取得頭部元素 (不移除)
int head = queue.peek();
```

#### 2. Python
Python 的標準 `list` 可以作為佇列，但 `pop(0)` 的時間複雜度是 $O(n)$。**強烈建議使用 `collections.deque`**，其 `popleft()` 的時間複雜度為 $O(1)$。

java, python 實戰優先看 239-max-sliding-window 再來看 1438-longest-subarray

```python
from collections import deque

# 宣告
queue = deque()

# 入隊
queue.append(1)
queue.append(2)

# 出隊
val = queue.popleft() # 返回 1

# 取得頭部元素
head = queue[0]
```

#### 3. JavaScript
JavaScript 中通常直接使用陣列（`Array`）。

```javascript
// 宣告
let queue = [];

// 入隊
queue.push(1);
queue.push(2);

// 出隊
let val = queue.shift(); // 返回 1，陣列自動重新索引

// 取得頭部元素
let head = queue[0];
```
*注意：`shift()` 操作在 JavaScript 底層陣列過大時，可能會導致 $O(n)$ 的效能問題，但在一般 LeetCode 題目中通常是可以接受的。若極端追求效能，可自行實作鏈結串列。*

#### 4. Go
Go 沒有內建高效的 Queue 容器，最常見且推薦的做法是使用 `slice`。

Go 實戰 101-is-symmetric/solution.go

```go
// 宣告
queue := []int{}

// 入隊
queue = append(queue, 1)
queue = append(queue, 2)

// 出隊
val := queue[0]
queue = queue[1:] // 切片操作，移除第一個元素

// 取得頭部元素
head := queue[0]
```
*優點：語法簡潔，且 `slice` 底層連續記憶體對快取（Cache）非常友善。*