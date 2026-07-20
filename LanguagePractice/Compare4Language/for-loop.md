## 四種語言對比

| 語言 | 寫法 | `len/size` 何時計算 | 迴圈內增刪元素 |
|------|------|---------------------|---------------|
| **Python** | `for i in range(len(dq))` | 迴圈開始時**算一次** | ✅ 不影響迭代次數 |
| **Java** | `for (int i = 0; i < deque.size(); i++)` | **每次迭代**都重新算 | ⚠️ 會影響迭代次數 |
| **JavaScript** | `for (let i = 0; i < deque.length; i++)` | **每次迭代**都重新算 | ⚠️ 會影響迭代次數 |
| **Go** | `for i := 0; i < len(deque); i++` | **每次迭代**都重新算 | ⚠️ 會影響迭代次數 |

### 結論

Python 的 `range()` 是**特例**（一次性快照），其他三種語言都是**每次重新計算條件**。

所以在 Java、JavaScript、Go 做 BFS 層序遍歷時，都需要**先固定長度**：

**Java：**
```java
int levelSize = deque.size();
for (int i = 0; i < levelSize; i++) { ... }
```

**JavaScript：**
```javascript
const levelSize = deque.length;
for (let i = 0; i < levelSize; i++) { ... }
```

**Go：**
```go
levelSize := len(deque)
for i := 0; i < levelSize; i++ { ... }
```

記住一個原則：**只要是用索引式 `for` 迴圈（`i = 0; i < ?; i++`），條件都會每次重新計算，就需要先固定長度。** 這是跨語言通用的 BFS 模板要點。

在寫這一道題時發現了這個特性, 所以寫了這一篇MD
107-level-order-bottom