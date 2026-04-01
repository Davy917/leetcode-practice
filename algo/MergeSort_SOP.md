# MergeSort 兩版本比較與快速複習手冊

> **目的**：隔一段時間後能在 5 分鐘內重新上手本 repo 的兩份 MergeSort 實作，理解設計差異、曾踩過的 bug 以及底層原理。
>
> 原始碼參考：
> - Basic 版：[`algo/MergeSort_basic.java`](MergeSort_basic.java)
>   （GitHub：<https://github.com/Davy917/leetcode-practice/blob/main/algo/MergeSort_basic.java>）
> - Advance 版：[`algo/MergeSort_advance.java`](MergeSort_advance.java)
>   （GitHub：<https://github.com/Davy917/leetcode-practice/blob/main/algo/MergeSort_advance.java>）

---

## 一、兩版本快速對照表

| 比較項目 | **MergeSort_basic** | **MergeSort_advance**（空間優化版） |
|----------|--------------------|------------------------------------|
| **merge 接收的參數** | 兩個獨立子陣列 `int[] arr1, int[] arr2` | 原陣列 `arr` + `start, end` 邊界 + 共用 buffer `result` |
| **result 陣列配置** | 每次 `merge` 都 `new int[arr1.length + arr2.length]` | **只配置一次** `new int[arr.length]`，全程共用 |
| **空間複雜度** | O(n log n)（每層遞迴都配置新陣列） | O(n)（只有一個 buffer） |
| **mergeSort 回傳值** | 回傳新陣列 `int[]` | 無回傳值（`void`），直接修改 `arr` |
| **result 的寫入起點** | 從 `index 0` 開始（子陣列都是獨立新陣列） | 從 `start` 開始（在大陣列的某個子區間）|
| **result index 計算** | `result[index1 + index2]` | `resultIndex`（從 `start` 出發遞增）或<br>`result[index1 + index2 - start2]`（精簡版）|
| **回填 arr** | 最外層呼叫後 `System.arraycopy(result, 0, arr, 0, result.length)` | 每次 `merge` 完後只回填 `[start..end]` 區間 |
| **遞迴終止條件** | `start == end`，回傳 `new int[]{arr[start]}` | `start == end`，直接 `return`（不需回傳） |

---

## 二、為什麼 `result[index1 + index2]` 在 basic 版可以直接用，而 advance 版不行？

### Basic 版的直覺

```java
// MergeSort_basic.merge(int[] arr1, int[] arr2)
int index1 = 0, index2 = 0;
result[index1 + index2] = arr1[index1];   // 第一次寫入 result[0]
```

- `arr1`、`arr2` 都是從 **index 0** 開始的獨立小陣列。
- `result` 也是新建的陣列，**從 index 0 開始填**。
- 每次寫入 `result` 的下一個位置 = 「已從 arr1 拿走的元素數」＋「已從 arr2 拿走的元素數」
  = `index1 + index2`。
- 初始：`0 + 0 = 0`，完全正確。

### Advance 版為什麼不能直接用 `index1 + index2`？

```java
// MergeSort_advance.merge_simplify
// 此時 index1 = start，index2 = start2（均非 0）
result[index1 + index2] = ...   // ❌ 第一次寫入 result[start + start2]，超過 end！
```

- 在 advance 版，`index1` 初始值是 `start`，`index2` 初始值是 `start2 = middle + 1`。
- 第一次寫入位置變成 `start + start2`，遠超 `end`，**越界或寫到完全錯誤的位置**。
- 正確做法一（清晰版）：用獨立的 `resultIndex = start`，每次 `resultIndex++`。
- 正確做法二（精簡版）：計算 `index1 + index2 - start2`
  - 代入初始值：`start + start2 - start2 = start`（從 start 開始）✅
  - 隨 index 推進，這個值會連續遞增，正確對應 `result[start..end]`。

---

## 三、遞迴執行順序與呼叫堆疊說明

### 執行順序：左邊先、右邊後

```java
// MergeSort_basic 的 mergeSort 私有方法
int[] left  = mergeSort(arr, start, middle);      // ① 完整執行完才繼續
int[] right = mergeSort(arr, middle + 1, end);    // ② 左邊完成後才開始
return merge(left, right);                         // ③ 兩邊都完成後合併
```

- 第 ① 行是一個**同步呼叫**，它會一路遞迴到底（每次細分為更小的 start/end），
  全部執行完並回傳後，才把值指定給 `left`，程式才繼續往下走。
- 第 ② 行的右邊遞迴，**一定在左邊完全完成之後才啟動**。

### 「右邊遞迴會經過左邊程式碼」的真相

你可能注意到右邊遞迴 `mergeSort(arr, middle+1, end)` 在執行時，
其內部也有 `left = mergeSort(...)` 和 `right = mergeSort(...)`。
這個「left」是**右半部子問題的左半部**，和最外層的左半部是不同的區間：

```
最外層：start=0, end=5  →  左 [0..2]、右 [3..5]
右子問題（[3..5]）：     →  右的左 [3..4]、右的右 [5..5]
```

每一層呼叫都有**自己獨立的一份區域變數**（`start`、`end`、`middle`、`left`、`right`），
彼此完全隔離，這是 Java 呼叫堆疊（Call Stack）的保證。

### 為什麼左邊已完成的部分不受影響？（Basic 版）

- `mergeSort` 回傳的是**新建的陣列**（base case：`new int[]{arr[start]}`；遞迴：`merge(left, right)` 也 new 一個新陣列）。
- `left` 變數指向這個新陣列，和 `arr` 原本的區間是**不同的記憶體**。
- 右邊遞迴執行時，只會讀取 `arr[middle+1..end]` 這段，並建立自己的新陣列，
  完全不會修改 `left` 所指向的陣列。
- 因此「左邊已排好的陣列」在右邊遞迴跑完後依然完整無誤。

### 呼叫堆疊示意（以 `arr=[2,6,1,3,5,4]` 為例）

```
呼叫堆疊由下往上成長，return 後消失：

Frame 6: mergeSort(arr, 0, 5)
  left  指向 [1,2,6]（由 Frame 2 回傳）
  right 指向 [3,4,5]（由 Frame 5 回傳）
  正在執行 merge([1,2,6],[3,4,5]) → [1,2,3,4,5,6]

Frame 5: mergeSort(arr, 3, 5)      ← 右子問題，start/end 是 3,5（非 0,5）
Frame 4: mergeSort(arr, 3, 4)      ← 右子問題的左半部
Frame 3: mergeSort(arr, 4, 4) → [5]
Frame 2: mergeSort(arr, 0, 2)
Frame 1: mergeSort(arr, 0, 1)
Frame 0: mergeSort(arr, 0, 0) → [2]   ← base case，直接 return
```

每個 Frame 的 `start`、`end`、`left`、`right` 互不干擾，
Frame 回傳後上一層的值完全不受影響。

---

## 四、遞迴樹：`arr=[2,6,1,3,5,4]`（index 0..5）

```
mergeSort(arr, 0, 5)
|
|-- left  = mergeSort(arr, 0, 2)
|   |
|   |-- left  = mergeSort(arr, 0, 1)
|   |   |
|   |   |-- left  = mergeSort(arr, 0, 0)  ->  [2]
|   |   |
|   |   `-- right = mergeSort(arr, 1, 1)  ->  [6]
|   |       merge([2], [6])               ->  [2, 6]
|   |
|   `-- right = mergeSort(arr, 2, 2)      ->  [1]
|       merge([2, 6], [1])                ->  [1, 2, 6]
|
`-- right = mergeSort(arr, 3, 5)
    |
    |-- left  = mergeSort(arr, 3, 4)
    |   |
    |   |-- left  = mergeSort(arr, 3, 3)  ->  [3]
    |   |
    |   `-- right = mergeSort(arr, 4, 4)  ->  [5]
    |       merge([3], [5])               ->  [3, 5]
    |
    `-- right = mergeSort(arr, 5, 5)      ->  [4]
        merge([3, 5], [4])                ->  [3, 4, 5]

最後在最外層:
merge([1, 2, 6], [3, 4, 5])              ->  [1, 2, 3, 4, 5, 6]
```

> 注意：`mergeSort(arr, 3, 5)` 內部也有 `left`/`right` 變數，
> 但那是「右半部 [3..5] 的左半部 [3..4]」，與最外層的 left [0..2] 是完全不同的區間與變數。

---

## 五、歷史 Bug 與修正記錄

### Bug 1：`System.arraycopy` 的複製範圍錯誤（嚴重）

**問題程式碼**（`MergeSort_advance.merge`）：

```java
// ❌ 錯誤：每次都把 result[0..end] 複製回 arr[0..end]
if (end + 1 >= 0) System.arraycopy(result, 0, arr, 0, end + 1);
```

**問題分析**：
- 每次 `merge` 只保證 `result[start..end]` 正確。
- 但把 `result[0..start-1]`（可能是舊資料或預設 0）也蓋回 `arr`，破壞了前面已排好的部分。
- 另外 `if (end + 1 >= 0)` 永遠為真（可拿掉）。

**修正後**：

```java
// ✅ 只回填這次 merge 的區間 [start..end]
System.arraycopy(result, start, arr, start, end - start + 1);
```

---

### Bug 2：`merge_simplify` 右半邊剩餘迴圈寫錯來源變數（嚴重）

**問題程式碼**（`MergeSort_advance.merge_simplify`）：

```java
// ❌ 錯誤：index2 在遞增，卻從 arr[index1]（左指針）取值
while (index2 <= end) {
    result[index1 + index2 - start2] = arr[index1];   // ← 應為 arr[index2]
    index2++;
}
```

**問題分析**：
- 右半邊還有剩餘時，應把 `arr[index2]` 依序放入 `result`。
- 誤寫成 `arr[index1]` 會重複填入左指針目前的值，產生錯誤的排序結果。

**修正後**：

```java
// ✅ 取 arr[index2]（右半邊目前位置）
while (index2 <= end) {
    result[index1 + index2 - start2] = arr[index2];
    index2++;
}
```

---

## 六、`System.arraycopy` 使用重點

### 方法簽名

```java
System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
```

| 參數 | 意義 |
|------|------|
| `src` | 來源陣列 |
| `srcPos` | 來源陣列的起始 index（0-based） |
| `dest` | 目標陣列 |
| `destPos` | 目標陣列的起始 index |
| `length` | 要複製的元素數量 |

### 本 repo 用到的兩種場景

```java
// 場景 1 (basic 版)：最外層把 result 全部複製回 arr
System.arraycopy(result, 0, arr, 0, result.length);

// 場景 2 (advance 版)：只回填這次 merge 的子區間
System.arraycopy(result, start, arr, start, end - start + 1);
// end - start + 1 = 這次 merge 的元素總數
```

### 常見例外

| 情境 | 例外類型 |
|------|----------|
| index 或 length 超出範圍 | `ArrayIndexOutOfBoundsException` |
| 型別不相容（物件陣列） | `ArrayStoreException` |
| src 或 dest 為 null | `NullPointerException` |

### 小提醒

- `System.arraycopy` 比手寫 `for` 迴圈快（底層有最佳化）。
- `src` 和 `dest` 可以是同一個陣列，它會正確處理重疊區間（類似 `memmove`）。
- 若只需「建立新陣列並複製」，也可用 `Arrays.copyOf` 或 `Arrays.copyOfRange`。

---

## 七、遞迴中的 debug print 會拖慢執行

```java
// MergeSort_advance.mergeSort 中的 debug print
System.out.println("拆分左邊");   // ← 每次遞迴都印，陣列大時影響巨大
System.out.println("拆分右邊");
```

- MergeSort 的遞迴樹共有 O(n) 個節點（n 個葉節點 + n-1 個內部節點），
  每個非葉節點呼叫時都會印「拆分左邊」與「拆分右邊」，合計 O(n) 次 I/O 操作。
- 標準 I/O 比記憶體存取慢幾個數量級，n 大時累積的 I/O 開銷非常明顯。
- **正式使用或效能測試前，請移除所有 `System.out.println`（或改用 flag 控制）**。

---

## 八、正確性自我檢查清單

在完成 MergeSort 實作或修改後，逐項確認：

- [ ] **基底條件**：`start == end` 時正確回傳/返回（basic 回 `new int[]{arr[start]}`，advance 直接 `return`）
- [ ] **middle 計算**：`(start + end) / 2`，避免 overflow 可改 `start + (end - start) / 2`
- [ ] **左右遞迴區間不重疊不遺漏**：左為 `[start..middle]`，右為 `[middle+1..end]`
- [ ] **merge 的 resultIndex 起點正確**：advance 版從 `start` 開始，不是 0
- [ ] **`System.arraycopy` 的 srcPos/destPos/length 三個都對**：
  - `srcPos = start`，`destPos = start`，`length = end - start + 1`
- [ ] **左半邊剩餘迴圈**：從 `arr[index1]` 取值，寫到 `result`
- [ ] **右半邊剩餘迴圈**：從 `arr[index2]` 取值（不是 `arr[index1]`！），寫到 `result`
- [ ] **merge_simplify 的 offset 公式**：`index1 + index2 - start2`（初始值代入應等於 `start`）
- [ ] **回填 arr 的範圍**：只回填 `[start..end]`，不要從 0 開始覆蓋整段
- [ ] **移除 debug print**：遞迴中的 `System.out.println` 在正式測試前拿掉
- [ ] **Null/空陣列防禦**：可加 `if (arr == null || arr.length <= 1) return;`
