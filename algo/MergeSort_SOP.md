# MergeSort 兩版本比較 SOP

> **快速複習導向**：比較本 repo 內兩種 MergeSort 實作的技術細節差異，以及討論中提出的核心問題，隔一段時間回來能在 5 分鐘內重新上手。
>
> 原始碼參考：
> - Basic 版：[`algo/MergeSort_basic.java`](MergeSort_basic.java)
> - Optimized 版：[`algo/MergeSort_advance.java`](MergeSort_advance.java)

---

## 一、兩版本快速對照表

| 比較項目 | **Basic** `MergeSort_basic` | **Optimized** `MergeSort_advance` |
|----------|----------------------------|-----------------------------------|
| **方法簽名** | `int[] mergeSort(arr, start, end)` | `void mergeSort(arr, start, end, result)` |
| **回傳方式** | 每層回傳新的排序陣列 | 就地修改 `arr`，無回傳值 |
| **額外空間** | 每次 merge 都 `new int[]`，O(n log n) | 一開始只配置一個 `result` buffer，O(n) |
| **merge 參數** | 兩個獨立陣列 `arr1`, `arr2` | 原陣列 `arr` + 區間 `[start..end]` + 共用 buffer `result` |
| **result 寫入起點** | 從 `result[0]` 開始 | 從 `result[start]` 開始 |
| **index 計算公式** | `result[index1 + index2]` | `result[resultIndex]`（從 `start` 起跳） |
| **回填 arr** | 最外層一次性 `System.arraycopy` | 每次 merge 後只回填 `[start..end]` |
| **適用情境** | 概念清晰、教學用途 | 降低 GC 壓力、空間更節省 |

---

## 二、何時選用哪個版本？

- **選 Basic**：
  - 學習遞迴、理解 merge sort 概念
  - 陣列規模小、不在意 GC 成本
  - 想讓程式碼直觀易讀

- **選 Optimized**：
  - 陣列規模大，想降低記憶體配置次數
  - 需要「空間 O(n)」的保證
  - 已熟悉 basic 版後再優化

---

## 三、遞迴流程心智模型（Step-by-Step）

### Basic 版流程

```
1. mergeSort(arr, start, end)
   ├─ if start == end → return new int[]{arr[start]}   // 基底：單元素
   ├─ middle = (start + end) / 2
   ├─ left  = mergeSort(arr, start, middle)             // 遞迴左半，回傳新陣列
   ├─ right = mergeSort(arr, middle+1, end)             // 遞迴右半，回傳新陣列
   └─ return merge(left, right)                        // 合併兩獨立陣列 → 新陣列
```

### Optimized 版流程

```
1. mergeSort(arr, 0, arr.length-1, result)             // 傳入共用 buffer
   ├─ if start == end → return                         // 基底：單元素
   ├─ middle = (start + end) / 2
   ├─ mergeSort(arr, start, middle, result)             // 遞迴左半（就地排序）
   ├─ mergeSort(arr, middle+1, end, result)             // 遞迴右半（就地排序）
   └─ merge(arr, start, end, result)                   // 合併 arr[start..end] → result[start..end] → 回寫 arr
```

---

## 四、Merge 索引規則：為何 `index1+index2` vs `resultIndex`？

### Basic 版：`result[index1 + index2]`

```
arr1 = [2, 6]   (index 從 0 開始)
arr2 = [1]      (index 從 0 開始)
result = new int[3]

第 1 步：arr2[0]=1 < arr1[0]=2 → result[0+0] = 1,  index2=1
第 2 步：index2 耗盡 → result[0+1] = arr1[0]=2, index1=1
第 3 步：result[1+1] = arr1[1]=6, index1=2
```

**為什麼成立**：`arr1`、`arr2` 都從 index 0 開始，已放入 result 的元素總數 = `index1 + index2`，下一個寫入位置就是 `index1 + index2`。

---

### Optimized 版：`resultIndex`（從 `start` 起跳）

```
arr = [2, 6, 1, 3, 5, 4]，合併 arr[0..2]（start = 0, end = 2）
  左段 arr[0..1] = [2, 6]（已排序）
  右段 arr[2..2] = [1]

result 要寫入 result[start..end] = result[0..2]
  → resultIndex 必須初始化為 start（此例剛好是 0）
  → 若換成合併 arr[3..5]，resultIndex 就要初始化為 3，不能硬寫 0
```

若把 Optimized 版的 `index1`、`index2` 初始值代入 basic 版公式：
- `index1 = start`，`index2 = start2 = middle+1`
- `index1 + index2 = start + (middle+1)` → **遠大於 end**，直接越界

**正確做法**（`merge()` 版本）：
```java
int resultIndex = start;  // 從 start 起跳
result[resultIndex++] = arr[index1 或 index2];
```

**精簡公式**（`merge_simplify()` 版本）：
```java
result[index1 + index2 - start2] = ...
// 初始代入：start + start2 - start2 = start ✓
```

---

## 五、System.arraycopy 正確用法

### 方法簽名

```java
System.arraycopy(src, srcPos, dest, destPos, length)
//               來源  起始位   目標  目標起始  複製長度
```

### Basic 版（最後一次性回填整個排序結果）

```java
// 外層 mergeSort 拿到最終排序陣列後，寫回原陣列
int[] result = mergeSort(arr, 0, arr.length - 1);
System.arraycopy(result, 0, arr, 0, result.length);
// ✅ result 是完整排好的新陣列，從 0 開始複製全部
```

### Optimized 版（每次 merge 後只回填子區間）

```java
// 合併完 result[start..end]，回寫到 arr[start..end]
System.arraycopy(result, start, arr, start, end - start + 1);
// ✅ srcPos = start（從 result 的 start 位置讀）
// ✅ destPos = start（寫回 arr 的 start 位置）
// ✅ length = end - start + 1（只複製這次處理的區間）
```

---

## 六、常見 Bug 與修正 Checklist

### ❌ Bug 1：`merge()` 回填範圍錯誤（嚴重）

```java
// 錯誤：srcPos 和 destPos 都從 0 開始，且附帶永遠為真的無用條件判斷
if (end + 1 >= 0) System.arraycopy(result, 0, arr, 0, end + 1);
// 問題①：if (end + 1 >= 0) 永遠成立（end >= 0），這個判斷毫無意義
// 問題②：srcPos = 0、destPos = 0，會把 result[0..end] 全部覆蓋回 arr[0..end]
```

**問題**：當 `start != 0` 時，`result[0..start-1]` 可能還是預設值 0 或舊資料，
此舉會把錯誤的值覆蓋到 `arr[0..start-1]`。

```java
// 正確：只回填這次 merge 的區間
System.arraycopy(result, start, arr, start, end - start + 1);
```

---

### ❌ Bug 2：`merge_simplify()` 右半段剩餘迴圈填入錯誤變數

```java
// 錯誤：用 arr[index1]（左半段指標）代入右半段剩餘
while (index2 <= end) {
    result[index1 + index2 - start2] = arr[index1];  // ❌ 應是 arr[index2]
    index2++;
}
```

**問題**：右半段有剩時，應把 `arr[index2]` 放進去，卻放了 `arr[index1]`（左指標），
會重複塞左半段元素進去。

```java
// 正確：用 arr[index2]
while (index2 <= end) {
    result[index1 + index2 - start2] = arr[index2];  // ✅
    index2++;
}
```

---

### ⚠️ Bug 3：`merge_simplify()` 回填時直接修改參數 `start`（可讀性問題）

```java
// 原寫法：會改動傳入的 start 參數，之後 debug 較不方便
while (start <= end) {
    arr[start] = result[start];
    start++;
}
```

```java
// 建議：用 arraycopy 最乾淨
System.arraycopy(result, start, arr, start, end - start + 1);
```

---

### 📋 完整 Checklist（每次實作 Optimized 版前確認）

- [ ] `mergeSort` 遞迴基底條件：`if (start == end) return;`
- [ ] `merge()` 中 `resultIndex` 初始值為 `start`，不是 0
- [ ] 左半段剩餘迴圈：填入 `arr[index1]` ✓
- [ ] 右半段剩餘迴圈：填入 `arr[index2]` ✓（常見錯填 `arr[index1]`）
- [ ] 回填 arr：`System.arraycopy(result, start, arr, start, end - start + 1)` ✓
- [ ] 確認沒有 `System.arraycopy(result, 0, arr, 0, end+1)` 這種從頭複製的錯誤
- [ ] 提交前移除或關閉所有 `System.out.println` 的 debug 輸出

---

## 七、遞迴問題 Q&A

### Q1：做完左邊遞迴後，右邊遞迴裡又會出現「左/右遞迴」，怎麼確定右邊有正確執行且不影響左邊？

**A**：

1. **順序執行**：`left = mergeSort(arr, start, middle)` 完整跑完才輪到下一行 `right = mergeSort(...)` 開始。
2. **各層有獨立的 stack frame**：每次呼叫都有自己的 `start`、`end`、`middle`、`left`、`right` 局部變數，不會互相干擾。
3. **Basic 版回傳獨立新陣列**：`left` 指向一個全新的陣列，右邊遞迴不會去修改它；只有最後的 `merge()` 才會讀它，而那時 `left` 和 `right` 都已就緒。
4. **「右邊裡的左遞迴」不是整體的左半邊**：`mergeSort(arr, middle+1, end)` 內部雖然也會分出自己的「左右」，但那是 `[middle+1..end]` 這段的左右，區間完全不同。

---

### Q2：`merge_simplify()` 為何用 `index1 + index2 - start2` 而不是 `index1 + index2`？

**A**：

| | Basic 版 | Optimized 版 |
|---|---|---|
| `index1` 初始值 | `0` | `start` |
| `index2` 初始值 | `0` | `start2 = middle + 1` |
| 第一次寫入位置 | `0 + 0 = 0` ✓ | `start + start2` ≠ `start` ❌ |

加上 `- start2` 的目的是讓「第一次寫入位置」剛好等於 `start`：

```
index1 + index2 - start2
= start + start2 - start2   (代入初始值)
= start                     ✓
```

---

## 八、遞迴樹範例（arr = [2, 6, 1, 3, 5, 4]）

```text
範例陣列（index）:
arr = [2, 6, 1, 3, 5, 4]
idx   0  1  2  3  4  5

mergeSort(arr, 0, 5)
|
|-- left  = mergeSort(arr, 0, 2)
|   |
|   |-- left  = mergeSort(arr, 0, 1)
|   |   |
|   |   |-- left  = mergeSort(arr, 0, 0)  -> [2]
|   |   |
|   |   `-- right = mergeSort(arr, 1, 1)  -> [6]
|   |       merge([2], [6])               -> [2, 6]
|   |
|   `-- right = mergeSort(arr, 2, 2)      -> [1]
|       merge([2, 6], [1])                -> [1, 2, 6]
|
`-- right = mergeSort(arr, 3, 5)
    |
    |-- left  = mergeSort(arr, 3, 4)
    |   |
    |   |-- left  = mergeSort(arr, 3, 3)  -> [3]
    |   |
    |   `-- right = mergeSort(arr, 4, 4)  -> [5]
    |       merge([3], [5])               -> [3, 5]
    |
    `-- right = mergeSort(arr, 5, 5)      -> [4]
        merge([3, 5], [4])                -> [3, 4, 5]

最後在最外層:
merge([1, 2, 6], [3, 4, 5])              -> [1, 2, 3, 4, 5, 6]
```

**重點說明**：

- `mergeSort(arr, 3, 5)`（整體的 right）內部也會分出自己的 `left` = `mergeSort(arr, 3, 4)` 和 `right` = `mergeSort(arr, 5, 5)`。這個內部的 `left` 對應的是右半段 `[3..5]` 的左半邊 `[3..4]`，**不是最一開始整體的左半邊 `[0..2]`**。
- 整體左半 `[0..2]` 的排序結果 `[1, 2, 6]` 存在一個獨立的新陣列裡，不會在跑右半段遞迴時被覆蓋。

---

## 十、實際跑一遍：arr = [2, 6, 1, 5, 3, 4] 右半邊遞迴（Optimized 版）

> **情境**：整體 `mergeSort(arr, 0, 5)` 做完左半 `[0..2]` 後，輪到右半 `mergeSort(arr, 3, 5)`。  
> 此節示範 `[3..5]` 裡每一層遞迴如何把 `arr` 對應區間逐步排好。

```text
測資：
arr = [2, 6, 1, 5, 3, 4]
idx    0  1  2  3  4  5
右半邊 [3..5] = [5, 3, 4]
```

### Step 1：mergeSort(arr, 3, 5)

```text
start=3, end=5, middle=4
→ 先做左半：mergeSort(arr, 3, 4)
→ 再做右半：mergeSort(arr, 5, 5)
→ 最後合併：merge(arr, 3, 5)
```

### Step 2：進入左半 mergeSort(arr, 3, 4)

```text
start=3, end=4, middle=3
→ mergeSort(arr, 3, 3)  // start==end → return（單元素 [5]，天然有序）
→ mergeSort(arr, 4, 4)  // start==end → return（單元素 [3]，天然有序）
→ merge(arr, 3, 4)
```

執行 `merge(arr, 3, 4)`：合併 `arr[3..3]=[5]` 與 `arr[4..4]=[3]`，結果 `[3,5]` 回寫。

```text
merge 前：arr = [2, 6, 1, 5, 3, 4]
                          ^  ^
                        [3..4] = [5, 3]

merge 後：arr = [2, 6, 1, 3, 5, 4]
                          ^  ^
                        [3..4] = [3, 5]  ← 已排序
```

### Step 3：進入右半 mergeSort(arr, 5, 5)

```text
start=5, end=5
start==end → return（單元素 [4]，天然有序）
```

此時 `arr` 狀態：
- `[3..4]` 已排序 = `[3, 5]`
- `[5..5]` 天然有序 = `[4]`

### Step 4：回到 Step 1，執行 merge(arr, 3, 5)

```text
合併左段 arr[3..4]=[3,5] 與右段 arr[5..5]=[4]，結果 [3,4,5] 回寫。

merge 前：arr = [2, 6, 1, 3, 5, 4]
                          ^  ^  ^
                        [3..5] = [3, 5, 4]

merge 後：arr = [2, 6, 1, 3, 4, 5]
                          ^  ^  ^
                        [3..5] = [3, 4, 5]  ← 已排序
```

### 遞迴拆分總覽

```text
mergeSort(arr, 3, 5)
├─ mergeSort(arr, 3, 4)
│   ├─ mergeSort(arr, 3, 3)  → return（base case，單元素 [5]，天然有序）
│   ├─ mergeSort(arr, 4, 4)  → return（base case，單元素 [3]，天然有序）
│   └─ merge(arr, 3, 4)      → arr[3..4] = [3, 5]
├─ mergeSort(arr, 5, 5)      → return（base case，單元素 [4]，天然有序）
└─ merge(arr, 3, 5)          → arr[3..5] = [3, 4, 5]
```

### 關鍵重點

- **Optimized 版 base case 回傳 `void`**：`if (start == end) return;`  
  單一元素的區間天然有序，不需要建立新陣列；直接 return 告訴上一層「這段 OK 了」。

- **有序性是在「回溯時」由 `merge(...)` 建立的**：  
  每次 `merge(arr, start, end, result)` 執行後，`result[start..end]` 被排序完成並回寫到 `arr[start..end]`，所以上一層接收到的左右子段都已是有序的。

- **左半結果不受右半影響**：  
  左半完成後，其排序結果已寫回 `arr[start..middle]`；右半遞迴只會讀寫 `arr[middle+1..end]` 這個不重疊的區間，兩段互不干擾。

---

## 十一、效能注意事項

- **`System.out.println` 在遞迴中會大幅拖慢大型輸入**：每遞迴一層就印一行，陣列有 n 個元素時會印 O(n log n) 行。正式使用或提交 LeetCode 前務必移除或用條件開關控制。
- Basic 版每次 `merge` 都 `new int[]`，若陣列很大（例如 10^6 個元素），GC 壓力明顯；此時 Optimized 版的單一 `result` buffer 優勢更顯著。
