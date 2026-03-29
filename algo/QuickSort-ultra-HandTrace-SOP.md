# 手推 QuickSort_ultra 的 SOP

> **用途**：拿著白紙，5～10 分鐘內能依此 SOP 完整推演 [`QuickSort_ultra.java`](QuickSort_ultra.java)（尾遞迴優化 + 隨機 pivot）的執行流程。

---

## 一、三大難點速覽

手推 `QuickSort_ultra` 前，先把這三件事記住：

| # | 難點 | 一句話說明 |
|---|------|-----------|
| 1 | **隨機 pivot** | `partition` 每次隨機選索引，路徑不唯一，手推時必須先固定化 |
| 2 | **只遞迴較小區間** | `quickSort` 用 `if/else` 判斷兩側長度，短的那邊才走遞迴呼叫 |
| 3 | **while 迭代大側** | 長的那邊不遞迴，而是更新 `start` 或 `end` 後繼續 `while` 下一輪 |

---

## 二、固定隨機 pivot 的方式（手推必做）

`partition` 源碼：

```java
int pivotIdx = (int)(start + Math.random() * (end - start + 1));
swap(arr, start, pivotIdx);
```

> **命名約定（本 SOP 統一用法）**
> - `pivotIdx`：本次隨機選到的**索引**（傳入 `swap`）
> - `pivotFinal`：`partition` **回傳值**，即 pivot 排好後的最終位置

手推時請先選一個固定化規則，並在文件頂端標註：

| 規則名稱 | 說明 | 適用場景 |
|----------|------|---------|
| **A. 每次選 start** | `pivotIdx = start` | 最簡單，不改陣列排列 |
| **B. 每次選中間** | `pivotIdx = (start + end) / 2` | 接近平衡分割，樹較扁 |
| **C. 預先寫 pivotIdx 序列** | 自定義每次選哪個，例如 `[3, 5, 6, 0, 1, 2]` | 驗證特定分支 |

> **目的**：不是要求「正確模擬 random」，而是讓推演路徑唯一，方便逐步追蹤。

---

## 三、手推表格模板

每一輪 `while (start < end)` 的迭代，填一行：

| while 輪次 | `(start, end)` | `pivotIdx`（選到） | `pivotFinal`（= partition 回傳） | 左長 L = pivotFinal - start | 右長 R = end - pivotFinal | L < R？ | 走 if/else | 遞迴哪邊 | while 下一輪邊界更新 |
|-----------|---------------|-------------------|----------------------------------|----------------------------|--------------------------|---------|-----------|---------|-------------------|
| 1 | (0, 6) | ? | ? | ? | ? | ? | ? | ? | start=? 或 end=? |
| 2 | (?, ?) | ? | ? | ? | ? | ? | ? | ? | ... |

> **填表規則**：
> - `pivotIdx`：用你選定的固定化規則填入
> - `pivotFinal`：由 partition 推演（下節說明）後填入
> - L < R 為 true → 走 `if`，遞迴左邊 `(start, pivotFinal-1)`，然後 `start = pivotFinal + 1`
> - L < R 為 false（含 L==R）→ 走 `else`，遞迴右邊 `(pivotFinal+1, end)`，然後 `end = pivotFinal - 1`

---

## 四、partition 三個 Checkpoint 推演法

> 平時手推**只需記錄這三個時機**，不需要逐步追蹤每一次指針移動。
> 只有除錯時才需要逐步推掃描細節。

### Checkpoint 1：`swap(start, pivotIdx)` 後 pivot 值固定在 `arr[start]`

```
pivotIdx 選好 → swap(arr, start, pivotIdx)
此後 arr[start] = pivot 值，全程不再移動（直到 Checkpoint 3）
記下：pivot 值 = arr[start]，left = start，right = end
```

### Checkpoint 2：left / right 雙指針交會，right 即為 `pivotFinal`

```
外層 while (left < right) 結束時 left == right
此時 arr[right] <= pivot 值（因為先掃 right）
所以 right = 最終 pivot 落點 = pivotFinal
```

> **雙指針掃描口訣**（只在除錯時逐步追）：
> 1. 右指針往左：`while (left < right && arr[right] >= arr[start]) right--`
> 2. 左指針往右：`while (left < right && arr[left] <= arr[start]) left++`
> 3. 若 `left < right`：`swap(arr, left, right)` → 回到步驟 1

### Checkpoint 3：`swap(start, right)` 歸位，回傳 `pivotFinal`

```
swap(arr, start, right)  // pivot 歸位到 right 位置
return right             // pivotFinal = right
```

---

## 五、核心不變量與口訣

### 不變量

> **partition 後 `arr[pivotFinal]` 已永久就位，後續所有遞迴 / while 迭代都必須排除它。**

```
左子區間：(start, pivotFinal - 1)   // 不含 pivotFinal
右子區間：(pivotFinal + 1, end)     // 不含 pivotFinal
```

確保區間每輪都縮小：
- 走 `if` 分支：`start = pivotFinal + 1`（往右移，必定 > 舊 start）
- 走 `else` 分支：`end = pivotFinal - 1`（往左移，必定 < 舊 end）

### 一句話口訣

> **「pivot 落地不再動，左右各退一格，小邊遞迴大邊留。」**

---

## 六、待處理區間 Stack 視角

看到 log 印出 `quickSort(6,6)` 後又回到 `(0,2)` 感到困惑？用「待處理區間 stack」理解：

```
初始 stack（概念上）：[(0,6)]

第 1 輪 while：處理 (0,6)，pivotFinal=3，走 else
  → 壓入遞迴：[(4,6)]
  → 更新外層 while 為 (0,2)（end=2）
  → stack 概念：[(0,2), (4,6)]  ← (0,2) 等著；先進入遞迴 (4,6)

遞迴 (4,6) 跑完（含其中 (6,6)）→ return 回外層
外層 while 繼續，處理 (0,2)
```

> **重點**：`(0,2)` 不是「新的遞迴」，而是**同一層 `quickSort(arr,0,6)` 的 while 下一輪**，只是被 `(4,6)` 的遞迴暫時插隊。等遞迴 return 後，`end` 已被更新為 `pivotFinal-1 = 2`，while 自然從 `(0,2)` 繼續。

---

## 七、完整手推範例

**輸入**：`arr = [4, 2, 7, 1, 6, 3, 5]`，索引 0～6

**固定 pivot 規則**：選用規則 B（`pivotIdx = (start+end)/2`，Java 整數除法，結果自動無條件捨去小數）

---

### 第 1 輪 while：`(start, end) = (0, 6)`

**Checkpoint 1**：`pivotIdx = (0+6)/2 = 3`
- `swap(arr, 0, 3)` → `arr = [1, 2, 7, 4, 6, 3, 5]`，pivot 值 = `arr[0]` = **1**

**Checkpoint 2**：掃描後 left/right 交會
- 右掃：`arr[6]=5 >= 1, arr[5]=3 >= 1, arr[4]=6 >= 1, arr[3]=4 >= 1, arr[2]=7 >= 1, arr[1]=2 >= 1`，right 縮到 0，與 left 相遇
- 相遇點 `right = 0`，`arr[0]=1 <= 1` ✓

**Checkpoint 3**：`swap(arr, 0, 0)` 自己換自己，`pivotFinal = 0`
- `arr = [1, 2, 7, 4, 6, 3, 5]`

**回到 quickSort**：
- L = pivotFinal - start = 0 - 0 = 0
- R = end - pivotFinal = 6 - 0 = 6
- L < R (0 < 6) → **走 if**
- 遞迴左邊：`quickSort(arr, 0, -1)` → while(0 < -1) 不成立，立刻 return
- 更新：`start = pivotFinal + 1 = 1`

---

### 第 2 輪 while：`(start, end) = (1, 6)`

**Checkpoint 1**：`pivotIdx = (1+6)/2 = 3`
- `swap(arr, 1, 3)` → `arr = [1, 4, 7, 2, 6, 3, 5]`，pivot 值 = `arr[1]` = **4**

**Checkpoint 2**：left=1, right=6 開始掃
- 右掃：`arr[6]=5 >= 4, arr[5]=3 >= 4`？3 < 4，停，right=5
- 左掃：`arr[1]=4 <= 4`，left=2；`arr[2]=7 <= 4`？7 > 4，停，left=2
- swap(2,5)：`arr = [1, 4, 3, 2, 6, 7, 5]`
- 右掃：`arr[5]=7 >= 4, arr[4]=6 >= 4, arr[3]=2 >= 4`？2 < 4，停，right=3
- 左掃：`arr[2]=3 <= 4`，left=3；left==right，停
- 相遇點 `right = 3`，`arr[3]=2 <= 4` ✓

**Checkpoint 3**：`swap(arr, 1, 3)` → `arr = [1, 2, 3, 4, 6, 7, 5]`，`pivotFinal = 3`

**回到 quickSort**：
- L = 3 - 1 = 2；R = 6 - 3 = 3
- L < R (2 < 3) → **走 if**
- 遞迴左邊：`quickSort(arr, 1, 2)`（見下節）
- 更新：`start = pivotFinal + 1 = 4`

---

### 遞迴深一層：`quickSort(arr, 1, 2)`

**第 2-1 輪 while：`(start, end) = (1, 2)`**

**Checkpoint 1**：`pivotIdx = (1+2)/2 = 1`
- `swap(arr, 1, 1)` 自己換自己，pivot 值 = `arr[1]` = **2**

**Checkpoint 2**：left=1, right=2 開始掃
- 右掃：`arr[2]=3 >= 2`，right=1，left==right，停
- 相遇點 `right = 1`，`arr[1]=2 <= 2` ✓

**Checkpoint 3**：`swap(arr, 1, 1)` 自己換自己，`pivotFinal = 1`

**回到 quickSort(1,2)**：
- L = 1 - 1 = 0；R = 2 - 1 = 1
- L < R (0 < 1) → **走 if**
- 遞迴左邊：`quickSort(arr, 1, 0)` → while(1 < 0) 不成立，立刻 return
- 更新：`start = 1 + 1 = 2`，while(2 < 2) 不成立，return

`quickSort(arr, 1, 2)` 結束，`arr[1..2]` 已就位 = `[2, 3]`

---

### 回到外層，繼續第 3 輪 while：`(start, end) = (4, 6)`

**Checkpoint 1**：`pivotIdx = (4+6)/2 = 5`
- `swap(arr, 4, 5)` → `arr = [1, 2, 3, 4, 7, 6, 5]`，pivot 值 = `arr[4]` = **7**

**Checkpoint 2**：left=4, right=6 開始掃
- 右掃：`arr[right] >= arr[start]`（即 >= 7）？`arr[6]=5 >= 7`？否，right 立刻停在 6
- 左掃：`arr[4]=7 <= 7`，left=5；`arr[5]=6 <= 7`，left=6；left==right，停
- 相遇點 `right = 6`，`arr[6]=5 <= 7` ✓

**Checkpoint 3**：`swap(arr, 4, 6)` → `arr = [1, 2, 3, 4, 5, 6, 7]`，`pivotFinal = 6`

**回到 quickSort**：
- L = 6 - 4 = 2；R = 6 - 6 = 0
- L < R (2 < 0)？No → **走 else**
- 遞迴右邊：`quickSort(arr, 7, 6)` → while(7 < 6) 不成立，立刻 return
- 更新：`end = pivotFinal - 1 = 5`，while(4 < 5) 成立，繼續

---

### 第 4 輪 while：`(start, end) = (4, 5)`

**Checkpoint 1**：`pivotIdx = (4+5)/2 = 4`
- `swap(arr, 4, 4)` 自己換自己，pivot 值 = `arr[4]` = **5**

**Checkpoint 2**：left=4, right=5 開始掃
- 右掃：`arr[5]=6 >= 5`，right=4，left==right，停
- 相遇點 `right = 4`，`arr[4]=5 <= 5` ✓

**Checkpoint 3**：`swap(arr, 4, 4)` 自己換自己，`pivotFinal = 4`

**回到 quickSort**：
- L = 4 - 4 = 0；R = 5 - 4 = 1
- L < R (0 < 1) → **走 if**
- 遞迴左邊：`quickSort(arr, 4, 3)` → while(4 < 3) 不成立，立刻 return
- 更新：`start = 4 + 1 = 5`，while(5 < 5) 不成立，return

`quickSort(arr, 0, 6)` 完全結束，最終 `arr = [1, 2, 3, 4, 5, 6, 7]` ✓

---

### 手推彙整表

| while 輪次 | 所在層 | `(start, end)` | `pivotIdx` | `pivotFinal` | L | R | L < R？ | 走 | 遞迴哪邊 | 更新邊界 |
|-----------|--------|---------------|-----------|-------------|---|---|---------|---|---------|--------|
| 1 | 外層(0,6) | (0, 6) | 3 | 0 | 0 | 6 | ✓ | if | (0, -1) 立刻 return | start=1 |
| 2 | 外層(0,6) | (1, 6) | 3 | 3 | 2 | 3 | ✓ | if | (1, 2) ← 進遞迴 | start=4 |
| 2-1 | 遞迴(1,2) | (1, 2) | 1 | 1 | 0 | 1 | ✓ | if | (1, 0) 立刻 return | start=2，退出 |
| 3 | 外層(0,6) | (4, 6) | 5 | 6 | 2 | 0 | ✗ | else | (7, 6) 立刻 return | end=5 |
| 4 | 外層(0,6) | (4, 5) | 4 | 4 | 0 | 1 | ✓ | if | (4, 3) 立刻 return | start=5，退出 |

---

## 八、命名提醒

| 變數 | 本 SOP 用詞 | Java 源碼對應 | 注意 |
|------|-----------|-------------|------|
| 隨機選出的索引 | `pivotIdx` | `pivot`（源碼中的局部變數） | 用完即丟，僅供 swap |
| partition 回傳值 | `pivotFinal` | `quickSort` 內的 `pivot` | 代表最終落點 |

> **避免混淆**：源碼中 `quickSort` 方法接收 `partition` 回傳值也命名為 `pivot`，手推時建議在紙上統一改用 `pivotFinal`，以區分「選到的索引」和「排好的位置」。

---

## 附錄：空白手推表格（可影印使用）

```
輸入陣列：___________________________
固定 pivot 規則：____________________

| 輪次 | 層 | (start,end) | pivotIdx | pivotFinal | L | R | L<R | if/else | 遞迴 | 更新邊界 |
|------|---|------------|---------|-----------|---|---|-----|---------|------|--------|
|      |   |            |         |           |   |   |     |         |      |        |
|      |   |            |         |           |   |   |     |         |      |        |
|      |   |            |         |           |   |   |     |         |      |        |
|      |   |            |         |           |   |   |     |         |      |        |
|      |   |            |         |           |   |   |     |         |      |        |

partition Checkpoint 草稿欄：
輪次 ___ ：swap(start,pivotIdx) 後 arr = ___________________________
          pivot 值 = ___，left = ___，right = ___
          掃描後交會：right = ___，arr[right] = ___
          swap(start, right) 後 arr = ___________________________，pivotFinal = ___
```
