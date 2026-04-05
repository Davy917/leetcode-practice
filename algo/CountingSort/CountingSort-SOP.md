# Counting Sort 兩版本比較 SOP

> **快速複習導向**：比較本 repo 內兩種 Stable Counting Sort 實作的核心差異與技術細節，隔一段時間回來能在 5 分鐘內重新上手。
>
> 原始碼參考：
> - Advance / Start-index 版：[`algo/CountingSort/countingSort_advance.py`](countingSort_advance.py)
> - Reverse / Last-index 版：[`algo/CountingSort/countingSort_reverse.py`](countingSort_reverse.py)
> - Basic 版（固定 1–9，含 queue 變體）：[`algo/CountingSort/countingSort_basic.py`](countingSort_basic.py)

---

## 一、兩版本快速對照表

| 比較項目 | **Advance（Start-index）** | **Reverse（Last-index）** |
|----------|---------------------------|--------------------------|
| **prefix 轉換目標** | 每格存「該值的起始位置（start index）」 | 每格存「該值的最後位置（last index）」 |
| **prefix 寫法** | `precount += counting[i]`<br>`counting[i] = precount - counting[i]` | `for i in range(1, total_range):`<br>`counting[i] += counting[i-1]`<br>再整體 `-1` |
| **填入 result 的掃描方向** | **正向**（left → right） | **反向**（right → left） |
| **填入後指針動作** | `counting[idx] += 1`（往右推） | `counting[idx] -= 1`（往左退） |
| **穩定性保證** | 正向掃 + start 往右推 → 同值保持原順序 | 反向掃 + last 往左退 → 同值保持原順序 |
| **可讀性** | 較直觀（start 概念清晰） | 需要理解 `-1` 的由來，可讀性略低 |
| **實際速度** | 微略快（正向存取對 cache 友善） | 相同量級，微略慢 |

---

## 二、核心問題 Q&A

### Q1：我們不是已經知道 `arr.length` 了嗎，為什麼要多此一舉求 `range`？

`arr.length` 告訴你「有多少個元素」；  
`range = max - min + 1` 告訴你「值域有多大（有多少種可能的值）」。  
這是兩個完全不同的概念。

計數排序的核心是用 **陣列下標表示某個值的 bucket**：

```
counting[0]  ↔  值 min
counting[1]  ↔  值 min + 1
...
counting[range-1]  ↔  值 max
```

映射公式：`idx = element - min`（把任意整數映射到 0-based 合法下標）

**反例：若只用 `arr.length` 當 counting 長度**

```
arr = [100, 200]
arr.length = 2，但值域 range = 101
counting[element - min] → counting[100] → 越界 ❌

arr = [-2, 5, -2, 0]，min=-2, max=5, range=8
element=5 → idx = 5 - (-2) = 7 → counting[7]（合法）
若用長度 4 → counting[7] 不存在 ❌
```

**固定 1–9 的版本不需要算 range** 是因為值域「提前已知」：range=9、min=1，直接寫死 `counting = [0] * 9`。泛用版則必須動態計算。

---

### Q2：`records = [deque() for _ in range(9)]` 是什麼？為何不能用 `[deque()] * 9`？

這行一次建立 9 個**彼此獨立**的 queue，對應數字 1–9 的 9 個 bucket。

| 寫法 | 結果 | 正確？ |
|------|------|--------|
| `[deque() for _ in range(9)]` | 每次迴圈都呼叫 `deque()` → 9 個**不同物件** | ✅ |
| `[deque()] * 9` | `deque()` 只呼叫一次 → 9 個指標指向**同一個物件** | ❌ |

**Bug 示範**：

```python
bad = [deque()] * 9
bad[0].append(1)
print(bad[1])  # deque([1])  ← bad[1] 也被改了！

good = [deque() for _ in range(9)]
good[0].append(1)
print(good[1])  # deque([])  ← 互不影響 ✓
```

等價的一般寫法：

```python
records = []
for _ in range(9):
    records.append(deque())
```

---

### Q3：Advance 版 prefix「魔法」兩行在做什麼？

**目標**：把 `counting` 從「次數表」轉成「起始位置（start index）表」。

```python
precount = 0
for i in range(total_range):
    precount += counting[i]          # precount = sum(counting[0..i])（含自己）
    counting[i] = precount - counting[i]  # sum(counting[0..i-1])  = start index
```

- `counting[i]` 更新前還是原本的次數，所以 `precount - counting[i]` = `sum(0..i-1)` = **比這個值小的元素有幾個** = 這個值第一次出現的位置（start index）。

**更可讀的等效 basic 版**（邏輯一樣，但用 `temp` 拆開）：

```python
precount = 0
for i in range(total_range):
    temp = counting[i]        # 暫存舊次數
    counting[i] = precount    # 直接設為 start index
    precount += temp          # 再更新前綴和
```

**具體範例**（`counting = [2, 0, 3, 1]`，對應值 1–4）：

| i | 舊次數 | precount（更新後） | counting[i]（start index） |
|---|-------|--------------------|---------------------------|
| 0 | 2 | 2 | 0 → 值 1 從 index 0 開始 |
| 1 | 0 | 2 | 2 → 值 2 從 index 2 開始 |
| 2 | 3 | 5 | 2 → 值 3 從 index 2 開始 |
| 3 | 1 | 6 | 5 → 值 4 從 index 5 開始 |

---

### Q4：Reverse 版第三個迴圈會得到 `[1,1,1,1,2,3,6]`，這代表什麼？

以 `arr = [8, 7, 2, 8, 6, 8, 2]`，`min=2, max=8, range=7` 為例：

統計後 `counting = [2, 0, 0, 0, 1, 1, 3]`（對應值 2–8）。

跑完 prefix + 整體 `-1` 後得 `[1, 1, 1, 1, 2, 3, 6]`，代表：

| i | 值 (min+i) | last index | 排序後驗證 |
|---|-----------|-----------|-----------|
| 0 | 2 | 1 | 2 出現兩次 → 最後在 index 1 ✓ |
| 1–3 | 3–5 | 1 | 不存在（last 被前面擠到 1） |
| 4 | 6 | 2 | 6 最後在 index 2 ✓ |
| 5 | 7 | 3 | 7 最後在 index 3 ✓ |
| 6 | 8 | 6 | 8 出現三次 → 最後在 index 6 ✓ |

排序結果應為：`[2, 2, 6, 7, 8, 8, 8]` ← 與上表完全吻合。

---

### Q5：`counting[0] -= 1` 是什麼意思？有沒有可讀性更高的寫法？

**為什麼存在**：直接做 prefix sum 得到的是「≤ 該值的累計個數」，但 last index = 累計個數 - 1（0-based），差 1。先讓 `counting[0]` 少 1，prefix sum 傳遞後每格自動都少 1。

**精簡版（目前 repo 的寫法）**：

```python
counting[0] -= 1                          # 讓第 0 格少 1
for i in range(1, total_range):
    counting[i] += counting[i - 1]        # prefix sum 連帶把「少 1」傳遞出去
```

**可讀性更高的兩步寫法（推薦複習時使用）**：

```python
# 步驟 1：先算「≤ 該值的累計個數」
for i in range(1, total_range):
    counting[i] += counting[i - 1]

# 步驟 2：整體減 1，轉成 last index（0-based）
for i in range(total_range):
    counting[i] -= 1
```

或者建立獨立的 `last_pos` 陣列（保留 `freq` 方便 debug）：

```python
freq = counting[:]          # 保留原始次數（方便 debug）
last_pos = [0] * total_range
running = 0
for i in range(total_range):
    running += freq[i]
    last_pos[i] = running - 1   # 直接算出 last index

# 後續用 last_pos 放元素
for i in range(len(arr) - 1, -1, -1):
    idx = arr[i] - min
    result[last_pos[idx]] = arr[i]
    last_pos[idx] -= 1
```

---

## 三、完整 Python 參考實作

### Advance 版（start-index，正向填入）

```python
def counting_sort_advance(arr: list[int]) -> None:
    if not arr or len(arr) <= 1:
        return
    lo, hi = min(arr), max(arr)
    total_range = hi - lo + 1

    # 1. 統計次數
    freq = [0] * total_range
    for x in arr:
        freq[x - lo] += 1

    # 2. 轉成 start index（basic 可讀寫法）
    precount = 0
    for i in range(total_range):
        temp = freq[i]
        freq[i] = precount      # freq[i] = 值 (lo+i) 的起始位置
        precount += temp

    # 3. 正向掃 arr，依序填入 result
    result = [0] * len(arr)
    for x in arr:
        idx = x - lo
        result[freq[idx]] = x
        freq[idx] += 1          # 下一個同值往右推（保持穩定）

    arr[:] = result
```

### Reverse 版（last-index，反向填入）

```python
def counting_sort_reverse(arr: list[int]) -> None:
    if not arr or len(arr) <= 1:
        return
    lo, hi = min(arr), max(arr)
    total_range = hi - lo + 1

    # 1. 統計次數
    freq = [0] * total_range
    for x in arr:
        freq[x - lo] += 1

    # 2. 轉成 last index（兩步可讀寫法）
    for i in range(1, total_range):
        freq[i] += freq[i - 1]  # 先算累計個數
    for i in range(total_range):
        freq[i] -= 1             # 再轉成 0-based last index

    # 3. 反向掃 arr，依序填入 result
    result = [0] * len(arr)
    for i in range(len(arr) - 1, -1, -1):
        idx = arr[i] - lo
        result[freq[idx]] = arr[i]
        freq[idx] -= 1           # 下一個同值往左退（保持穩定）

    arr[:] = result
```

> **Java 對應**：`element - min` 的邏輯完全相同。Java 版以 `int[] counting = new int[range]` 取代 Python list；其他邏輯一致（LeetCode 官方 Java 版即為 Advance / start-index 寫法）。

---

## 四、穩定性（Stability）直覺說明

**Advance 版穩定性**：正向掃描 `arr`，同值元素從左到右被依序放入 `result[start], result[start+1], ...`；先讀到的（在原陣列中排左邊的）先放，相對順序不變。

**Reverse 版穩定性**：反向掃描 `arr`，同值元素從右到左被依序放入 `result[last], result[last-1], ...`；後讀到的（在原陣列中排左邊的）放到更左邊的位置，相對順序不變。

**為什麼 Reverse 版要反向掃**：如果正向掃，同值元素後讀到的會覆蓋先放的位置，破壞穩定性。反向掃才能保證「原陣列中排越右的同值元素，被放到越右邊的 last 位置」。

---

## 五、效能比較

| 指標 | Advance / Reverse（純陣列 prefix 版） | records / queue 版（`deque` 桶子） |
|------|--------------------------------------|-----------------------------------|
| **時間複雜度** | `O(n + range)` | `O(n + range)` |
| **空間複雜度** | `O(n + range)` | `O(n + range) + 物件開銷` |
| **常數因子** | 很小（純整數陣列存取） | 大（deque node 配置、popleft 呼叫） |
| **記憶體配置** | 固定 2 個陣列 | n 個 deque node + 1 個 deque list |
| **適用情境** | 面試、競賽、實際效能考量 | 教學、直覺理解穩定性 |

**關鍵限制：Counting Sort 適合 range 小的資料。**

- `arr = [1, 1000000]`：range = 1,000,000，counting 陣列佔 4 MB，通常不划算。
- 適合情境：成績（0–100）、字元（0–127）、固定範圍的整數排序。

**Advance vs Reverse 常數差異**：
- Advance（正向掃）對 CPU cache 略友善（循序記憶體存取）。
- 實際差距極小（微秒級），在量級上完全相同。選哪個主要看個人習慣或題目要求。

---

## 六、Debug Checklist

遇到輸出不對時，依序 print 以下中間狀態：

```python
# Step 1：確認統計是否正確
print("freq:", freq)
# 期望：freq[i] = arr 中出現 (lo + i) 的次數

# Step 2：確認 prefix 轉換是否正確
#   Advance 版：freq[i] 應是 start index（= 比它小的元素個數）
#   Reverse 版：freq[i] 應是 last index（= 累計個數 - 1）
print("after prefix:", freq)

# Step 3：確認填入過程（加在填入迴圈內，加上 bounded dump 避免無限輸出）
MAX_PRINT = 20
for step, x in enumerate(arr):
    if step >= MAX_PRINT:
        print(f"... (only showing first {MAX_PRINT} steps)")
        break
    idx = x - lo
    print(f"  place arr[{step}]={x} → result[{freq[idx]}]")
    result[freq[idx]] = x
    freq[idx] += 1  # Advance 版

# Step 4：確認最終 result
print("result:", result)
```

**常見錯誤一覽**：

| 症狀 | 可能原因 |
|------|---------|
| 越界 `IndexError` | 用了 `arr.length` 而非 `range` 當 counting 長度 |
| 結果有 0 殘留 | prefix 計算錯誤，某個 start/last index 超出範圍 |
| 結果正確但不穩定 | Advance 版：忘記 `freq[idx] += 1`；Reverse 版：用了正向掃 |
| 負數輸入排錯 | 忘記 `element - min`（min 可能是負數），`element` 直接當下標 |
| `records` 桶子串在一起 | 用了 `[deque()] * n`（同一物件），應改用 list comprehension |

---

## 七、一句話記憶法

| 版本 | 核心公式 | 掃描方向 | 指針動作 |
|------|---------|---------|---------|
| **Advance** | `start[i] = sum(freq[0..i-1])` | left → right | `freq[idx] += 1` |
| **Reverse** | `last[i] = sum(freq[0..i]) - 1` | right → left | `freq[idx] -= 1` |
