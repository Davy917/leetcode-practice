# 圖論演算法學習清單

## 無向圖

### 無權圖 - 廣度優先、深度優先

- [130-solve](../../130-solve/Solution.java)
- [200-num-Islands](../../200-num-Islands/solution.js)
- [286-walls-and-gates](../../286-walls-and-gates/solution.go) - 介紹多源BFS vs 單源BFS
- [529-update-board](../../529-update-board/solution.py)
- [695-max-area-of-island](../../695-max-area-of-island/solution.go)
- [733-flood-fill](../../733-flood-fill/solution.go)
- [934-shortest-bridge](../../934-shortest-bridge/solution.py) - BFS、DFS都有用到
- [1020-num-enclaves](../../1020-num-enclaves/solution.py)
- [1091-shortest-path-binary-matrix](../../1091-shortest-path-binary-matrix/solution.js)
- [1034-color-border](../../1034-color-border/solution.js)
- [1254-closed-island](../../1254-closed-island/solution.js)

#### 隱式圖

- [752-open-lock](../../752-open-lock/solution.go) - 沒有自己寫出來，不是在陣列中做BFS

### 無環圖

#### 並查集

- [323-count-components](../../323-count-components/solution.js)
- [547-find-circle-num](../../547-find-circle-num/Solution.java)
- [684-find-redundant-connection](../../684-find-redundant-connection/solution.js)

#### 二分圖 - 染色法

- [785-is-bipartite](../../785-is-bipartite/solution.js) - 不用自己轉鄰接表
- [886-possible-bipartition](../../886-possible-bipartition/solution.py) - 要自己轉鄰接表

---

## 有向圖

### 無權圖 - 廣度優先、深度優先

- [417-pacific-atlantic](../../417-pacific-atlantic/solution.py)
- [909-snakes-and-ladders](../../909-snakes-and-ladders/solution.go)

#### 隱式圖

- [279-num-squares](../../279-num-squares/solution.go)

### 有環圖 - 染色法

- [207-can-finish](../../207-can-finish/solution.py)
- [210-find-order](../../210-find-order/Solution.java)
- [1136-minimum-semesters](../../1136-minimum-semesters/solution.py)

### 無環圖

- [797-all-path-source-target](../../797-all-path-source-target/Solution.java)

#### 無環圖 - 染色法

- [802-eventual-safe-nodes](../../802-eventual-safe-nodes/solution.js)

---

## 什麼是隱式圖？

### 隱式圖（Implicit Graph）

隱式圖是指**不需要預先建構或儲存在記憶體中**的圖。圖的節點和邊是**在演算法執行過程中，按需動態產生的**。

### 與顯式圖的對比

| 特性 | 顯式圖（Explicit Graph） | 隱式圖（Implicit Graph） |
|------|--------------------------|--------------------------|
| 定義 | 事先用鄰接表或鄰接矩陣存好 | 不預先建圖，執行時即時產生 |
| 節點 | 全部已知且固定 | 可能很多，甚至無限，按需展開 |
| 邊 | 事先存好 | 透過規則即時計算 |
| 記憶體 | 與圖的大小成正比 | 通常只存當前探索的部分 |

### 用279題說明

#### 顯式圖的做法（不一定需要）

```
先建好所有鄰接關係：
12 → [11, 8, 3]
11 → [10, 7, 2]
10 → [9, 6, 1]
...（全部建好再開始搜）
```

#### 隱式圖的做法（常見寫法）

```go
從 12 開始：
  → 套規則：減去 1, 4, 9 → 產生 [11, 8, 3]
  → 對每個新節點，再套同樣規則繼續展開
```

你並沒有真的建出一張圖，而是透過一條簡單的規則：

> **「當前數字減去一個完全平方數 = 下一個狀態」**

就足以在搜尋過程中**即時推導出所有鄰居**。這就是隱式圖。

### 一句話總結

> 隱式圖 = **只定義規則，不儲存結構**，演算法邊跑邊產生鄰居的圖。

你之前提交的程式碼中，迴圈 `for i := 1; i*i <= current; i++` 就是在動態產生鄰居，而不是事先建好鄰接表——這就是典型的隱式圖應用 👍
