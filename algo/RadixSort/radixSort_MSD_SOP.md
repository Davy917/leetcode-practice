# radixSort\_MSD SOP — 5 分鐘快速上手

> 原始碼：`algo/RadixSort/radixSort_MSD.py`  
> 前置知識：建議先把 `bucketSort` 學完再來看這份文件。

---

## TL;DR（30 秒速讀版）

| 問題 | 答案 |
|------|------|
| 演算法是什麼？ | MSD（Most Significant Digit）基數排序：先用最高位分桶，再對每個桶遞迴用次高位分桶。 |
| 分桶靠什麼？ | 每一輪用 counting sort，桶不是真正的 list，而是 `arr` 內的一段**連續區間**。 |
| 為何 counting 大小是 19？ | digit 範圍是 -9..9，用 `+9` 位移後對應到 0..18，共 19 格。 |
| `counting_copy` 幹嘛用？ | 保存 prefix sum 的邊界，因為放置元素時 `counting` 會被逐一 `-= 1` 破壞。 |
| 最後那個 for 迴圈在做什麼？ | 把 `counting_copy` 轉換成「每個 digit 桶在 `arr` 裡的 `[start, end]`」，並對非空桶遞迴。 |

---

## 快速心智模型

```
radixSort(arr)
  └─ 找 max_digit_length（最大值的位數）
     └─ radixSort_MSD(arr, 0, n-1, max_digit_length)
          ├─ 用第 position 位 digit 做 counting sort → 把 arr 依高位排好
          └─ for 每個非空桶：
               └─ radixSort_MSD(arr, bucketStart, bucketEnd, position-1)
```

每一層遞迴負責「某一個 digit 位」的分桶；  
最底層（position=0 或只剩 1 個元素）直接 return。

---

## Step-by-Step 逐段解說

### 1. `radixSort(arr)` — 找最大位數

```python
def radixSort(arr):
    if arr is None:
        return
    max_val = 0
    for val in arr:
        if abs(val) > abs(max_val):
            max_val = abs(val)

    max_digit_length = 0
    while max_val != 0:
        max_val = max_val // 10
        max_digit_length += 1

    radixSort.radixSort_MSD(arr, 0, len(arr) - 1, max_digit_length)
```

**重點：**
- `max_digit_length` 就是遞迴深度上限（從最高位往個位走）。

---

### 2. 進入遞迴 — base case

```python
def radixSort_MSD(arr, start, end, position):
    if start == end or position == 0:
        return
```

- `start == end`：只剩 1 個元素，不需排序。
- `position == 0`：已經沒有更低位可分桶了，停止。

---

### 3. `dev = 10^(position-1)` — 取出正確位數

```python
dev = pow(10, position - 1)
```

| position | dev | 取出的位 |
|----------|-----|----------|
| 1 | 1 | 個位 |
| 2 | 10 | 十位 |
| 3 | 100 | 百位 |

用 `abs(x) // dev % 10` 可以不受位數長短影響，取出任意位置的 digit。

---

### 4. `radix` 計算 — 把數字映射到 0..18 的桶

```python
radix = abs(arr[index]) // dev % 10 * (-1 if arr[index] < 0 else 1) + 9
```

分解成 3 步：

| 步驟 | 運算 | 說明 |
|------|------|------|
| ① 取 digit | `abs(x) // dev % 10` | 先取絕對值再取位，避免負號干擾 |
| ② 加符號 | `* sign`（正為 +1，負為 -1） | 讓負數 digit 變成負值（-9..-1）、正數保持正（0..9）|
| ③ 位移 | `+ 9` | 把 -9..9 映射到 0..18，符合 counting 陣列索引 |

例：`-35`，十位數 → `abs(-35)//10%10 = 3` → `3 * -1 = -3` → `-3 + 9 = 6`，放到 bucket 6。

---

### 5. `counting[0] -= 1` — 為什麼要減一？

```python
counting[0] -= 1
```

這份實作的 prefix sum 目標是讓 `counting[i]` 代表「radix ≤ i 的元素，在 result 裡的**最後索引（end index）**」，而不是「元素數量」。

若 counting[0] 原本是 `k`（有 k 個元素 radix=0），end index 應該是 `k-1`，所以先減一。  
其他桶在 prefix sum 時自然會算對（因為累加後前一桶的 end+1 才是下一桶的 start）。

---

### 6. Prefix Sum — `counting[i] += counting[i-1]`

```python
for index in range(1, len(counting)):
    counting[index] += counting[index - 1]
```

做完後 `counting[i]` = **radix ≤ i 的所有元素，在 result 裡的最後索引（end index）**。

範例（只列有元素的 bucket）：

| radix | count | prefix sum (end index) |
|-------|-------|------------------------|
| 11    | 2     | 1 |
| 12    | 3     | 4 |
| 14    | 4     | 8 |

---

### 7. `counting_copy` — 為什麼需要複製一份？

```python
counting_copy = [0] * len(counting)
counting_copy[0: len(counting_copy)] = counting[0: len(counting)]
```

下一步放置元素時會做：
```python
result[counting[radix]] = arr[index]
counting[radix] -= 1
```

每放一個元素，`counting[radix]` 就 `-= 1`，所以 `counting` 最後會面目全非。  
`counting_copy` 是放置**前**的快照，後面計算桶邊界時需要它。

---

### 8. 穩定放置 — 從右往左掃

```python
result = [0] * (end - start + 1)
for index in range(end, start - 1, -1):
    radix = abs(arr[index]) // dev % 10 * (-1 if arr[index] < 0 else 1) + 9
    result[counting[radix]] = arr[index]
    counting[radix] -= 1
```

- `result` 大小是 `end - start + 1`（local 索引 0..n-1）。
- **從右往左**掃 `arr[start..end]`：因為 `counting[radix]` 初始指向桶的最後位置，從右到左放能保持穩定性（相同 radix 的原始相對順序不變）。

---

### 9. `arr[start:end+1] = result` — 為什麼要 `+1`？

```python
arr[start: end + 1] = result
```

Python slice `a[s:e]` 是 **不含 e**（半開區間），所以要寫 `end + 1` 才能包含 `arr[end]`。  
這行把 local result 寫回全域 arr 的對應範圍。

---

### 10. 最後的 for 迴圈 — 計算桶邊界並遞迴

```python
for i in range(0, len(counting_copy)):
    bucket_local_start = 0 if i == 0 else counting_copy[i-1] + 1
    bucket_local_end = counting_copy[i]

    bucketStart = start + bucket_local_start
    bucketEnd = start + bucket_local_end

    if bucketStart < bucketEnd:   # 空桶或單元素桶跳過
        radixSort.radixSort_MSD(arr, bucketStart, bucketEnd, position - 1)
```

**邏輯推導：**

- `counting_copy[i]` = 桶 i 的最後索引（local）。
- 桶 i 的第一個索引 = 前一桶最後索引 + 1 = `counting_copy[i-1] + 1`。
  - 為什麼 `+1`？因為 `counting_copy[i-1]` 已被前一桶的最後元素佔用，當前桶只能從下一格開始。
- 加上 `start` 才能轉換回全域 `arr` 索引。
- `bucketStart < bucketEnd`（嚴格小於）代表至少 2 個元素才遞迴；1 個或 0 個元素不需排序。

---

## 常見陷阱

| 陷阱 | 說明 |
|------|------|
| 全負數陣列 | `max_val` 找不到正數，`max_digit_length=0`，不排序。需手動修正。 |
| `counting[0] -= 1` 漏掉 | prefix sum 的 end index 會多 1，導致放置位置錯誤。 |
| 忘記 `counting_copy` | 直接用 `counting` 算桶邊界，但此時 `counting` 已被破壞，桶範圍計算錯誤。 |
| `arr[start:end]` 少寫 `+1` | 少寫最後一個元素的回寫。 |
| `bucket_local_start` 少寫 `+1` | 桶與桶之間邊界重疊，同一個元素被兩個桶各遞迴一次。 |
| `bucketStart < bucketEnd` 用 `<=` | 單元素桶也會遞迴，造成無窮迴圈（`start==end` base case 能擋，但多了不必要的呼叫）。 |

---

## 範例完整執行：`[27, 53, 35, 52, 51, 32, 36, 23, 58]`

`max_val = 58` → `max_digit_length = 2` → 呼叫 `radixSort_MSD(arr, 0, 8, 2)`

### 第 1 輪：position=2（十位數），dev=10

**計算各元素 radix（十位 digit + 9）：**

| 值 | 十位 digit | radix |
|----|-----------|-------|
| 27 | 2 | 11 |
| 53 | 5 | 14 |
| 35 | 3 | 12 |
| 52 | 5 | 14 |
| 51 | 5 | 14 |
| 32 | 3 | 12 |
| 36 | 3 | 12 |
| 23 | 2 | 11 |
| 58 | 5 | 14 |

**counting（prefix sum 後，含 counting[0]-=1）：**

```
counting = [-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 4, 4, 8, 8, 8, 8, 8]
```

- radix 11 end=1 → result[0..1]
- radix 12 end=4 → result[2..4]
- radix 14 end=8 → result[5..8]

**從右往左放置後 result（穩定）：**

```
result = [27, 23, 35, 32, 36, 53, 52, 51, 58]
```

回寫：`arr = [27, 23, 35, 32, 36, 53, 52, 51, 58]`

**桶邊界（global arr 索引，start=0）：**

| bucket i | bucket_local_start | bucket_local_end | bucketStart | bucketEnd | 元素 |
|----------|--------------------|------------------|-------------|-----------|------|
| 11 | 0 | 1 | 0 | 1 | [27, 23] |
| 12 | 2 | 4 | 2 | 4 | [35, 32, 36] |
| 14 | 5 | 8 | 5 | 8 | [53, 52, 51, 58] |

---

### 第 2 輪：position=1（個位數），各桶分別遞迴

**桶 (0..1)：[27, 23]**

| 值 | 個位 digit | radix |
|----|-----------|-------|
| 27 | 7 | 16 |
| 23 | 3 | 12 |

排序後：`arr[0..1] = [23, 27]`

---

**桶 (2..4)：[35, 32, 36]**

| 值 | 個位 digit | radix |
|----|-----------|-------|
| 35 | 5 | 14 |
| 32 | 2 | 11 |
| 36 | 6 | 15 |

排序後：`arr[2..4] = [32, 35, 36]`

---

**桶 (5..8)：[53, 52, 51, 58]**

| 值 | 個位 digit | radix |
|----|-----------|-------|
| 53 | 3 | 12 |
| 52 | 2 | 11 |
| 51 | 1 | 10 |
| 58 | 8 | 17 |

排序後：`arr[5..8] = [51, 52, 53, 58]`

---

**最終結果：`arr = [23, 27, 32, 35, 36, 51, 52, 53, 58]` ✅**

---

## 遞迴樹

```
radixSort_MSD(arr[0..8], pos=2)   →  [27, 53, 35, 52, 51, 32, 36, 23, 58]
│   十位數分桶後 arr 變成 → [27, 23, 35, 32, 36, 53, 52, 51, 58]
│
├─ bucket 十位=2 → radixSort_MSD(arr[0..1], pos=1)   : [27, 23]
│  └─ 個位排序 → arr[0..1] = [23, 27]
│
├─ bucket 十位=3 → radixSort_MSD(arr[2..4], pos=1)   : [35, 32, 36]
│  └─ 個位排序 → arr[2..4] = [32, 35, 36]
│
└─ bucket 十位=5 → radixSort_MSD(arr[5..8], pos=1)   : [53, 52, 51, 58]
   └─ 個位排序 → arr[5..8] = [51, 52, 53, 58]

最終 arr = [23, 27, 32, 35, 36, 51, 52, 53, 58]
```

---

## Q&A 速查

**Q：`for i in range(0, len(counting_copy))` 在做什麼？**  
A：枚舉 19 個可能的 radix 桶（0..18），利用 `counting_copy` 計算每個桶在當前 `arr[start..end]` 內的區間，然後對「至少有 2 個元素的桶」遞迴執行下一位 digit 的分桶排序。本質上是把 prefix sum 的 end index 翻譯成 `[start, end]` pair，再轉換成全域索引。

**Q：`bucket_local_start = counting_copy[i-1] + 1` 為什麼要 `+1`？**  
A：`counting_copy[i-1]` 是前一個桶的最後索引（inclusive end），當前桶從下一格才開始，所以必須 `+1`。省略 `+1` 會讓相鄰桶的範圍重疊一個元素，導致同一個元素被重複遞迴處理。

**Q：這裡的「桶」和經典桶排序（Bucket Sort）差在哪？**

| | 桶排序（Bucket Sort） | 這裡的 MSD Radix Sort |
|-|----------------------|----------------------|
| 桶的資料結構 | 獨立的 list（動態容器） | `arr` 的連續子陣列（用索引界定） |
| 分桶依據 | 數值範圍（期待均勻分布） | 某一位 digit（0..9） |
| 桶內排序 | 比較排序（如 insertion sort） | 下一位 digit 的遞迴分桶 |
| 穩定性 | 取決於桶內排序演算法 | 穩定（counting sort + 從右往左放置） |

概念上一樣：**把元素分成多個群組，對每群組分別排序**；實作和分桶策略不同。

---

## 一頁速查表

```
radixSort(arr)
├── max_val = 正數最大值 (⚠️ 僅正數)
├── max_digit_length = 最大值位數
└── radixSort_MSD(arr, 0, n-1, max_digit_length)

radixSort_MSD(arr, start, end, position)
├── [BASE] start==end or position==0 → return
├── dev = 10^(position-1)            # 取出第 position 位
├── radix = abs(x)//dev%10 * sign + 9  # 映射到 0..18
├── counting[radix] += 1             # 計數
├── counting[0] -= 1                 # 調整為 end index
├── prefix sum counting              # counting[i] = 桶i的 end index
├── counting_copy = counting[:]      # 保存邊界（後面會用）
├── result = [0]*(end-start+1)
├── for index in range(end, start-1, -1):  # 從右往左，穩定放置
│     result[counting[radix]] = arr[index]
│     counting[radix] -= 1
├── arr[start:end+1] = result        # 回寫 (end+1 因為 slice 不含右端)
└── for i in range(19):              # 枚舉所有 radix 桶
      bucket_local_start = 0 if i==0 else counting_copy[i-1]+1
      bucket_local_end   = counting_copy[i]
      bucketStart = start + bucket_local_start
      bucketEnd   = start + bucket_local_end
      if bucketStart < bucketEnd:    # ≥2 個元素才遞迴
          radixSort_MSD(arr, bucketStart, bucketEnd, position-1)
```
