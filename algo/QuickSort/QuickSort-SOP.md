# QuickSort 三版本比較文檔

> **快速複習導向**：比較本 repo 內三種 QuickSort partition 實作的技術細節差異，建議先把三種版本都理解過再來看這份SOP, 比較好理解
>
> 原始碼參考：
> - V1 / V2：[`algo/QuickSort_basic.java`](QuickSort_basic.java)
> - V3：[`algo/QuickSort_advance.java`](QuickSort_advance.java)

---

## 一、三版本快速對照表

| 比較項目 | **V1** `partition` | **V2** `partition_v2` | **V3** `advance.partition` |
|----------|-------------------|-----------------------|---------------------------|
| **pivot 選擇** | `arr[start]`（存入變數） | `arr[start]`（存入變數） | `arr[start]`（直接用 `arr[start]`，不存變數） |
| **left 初始位置** | `start + 1` | `start + 1` | `start`（含 pivot 本身） |
| **right 初始位置** | `end` | `end` | `end` |
| **指針掃描方式** | 只有 left 往右掃；right 被動接收 | left 和 right 雙向掃 | left 和 right 雙向掃 |
| **掃描順序** | 僅 left 掃 | **先掃 left，再掃 right** | **先掃 right，再掃 left** |
| **left 掃描條件** | `arr[left] <= pivot` | `arr[left] <= pivot` | `arr[start] >= arr[left]` |
| **right 掃描條件** | （right 不掃描） | `arr[right] >= pivot` | `arr[start] <= arr[right]` |
| **交換時機** | `if (left != right)` 才 swap | `if (left < right)` 才 swap | **無條件** swap（包含自己換自己） |
| **swap 後指針動作** | swap 後只做 `right--` | swap 後做 `left++` 且 `right--` | 不額外移動（下一輪外層 while 再繼續） |
| **是否需要收尾 `right--` if** | ✅ 需要 `if(left==right && arr[right]>pivot) right--` | ✅ 需要同樣的收尾 if | ❌ 不需要（先掃 right 保證相遇點安全） |
| **pivot 歸位方式** | `if (right != start) swap(arr, start, right)` | `swap(arr, start, right)`（無 guard） | `swap(arr, start, right)`（無 guard） |
| **重複值（等於 pivot）行為** | left 遇等於值繼續前進（`<=` 跳過）；right 不掃，停在等於值上 | left 跳過等於值（`<=`）；right 跳過等於值（`>=`）；兩指針都會略過等於 pivot 的元素 | right 跳過等於值（`<=`）；left 跳過等於值（`>=`）；行為同 V2 |

---

## 二、各版本技術細節

### V1：`partition`（單指針 + 右端接收）

```
left = start+1, right = end
while (left < right):
    內層 while: left 往右掃，跳過 arr[left] <= pivot
    if (left != right):
        swap(arr, left, right)   // 把 >pivot 的值放到 right 位置
        right--                  // right 往左縮，下一個 >pivot 繼續送過來

// 收尾 ①：相遇點若 > pivot，right 再退一格
if (left == right && arr[right] > pivot) right--

// 收尾 ②：避免 pivot 是最小值時做 swap(start, start)（也避免多一筆 swap log）
if (right != start) swap(arr, start, right)

return right
```

**關鍵理解**：
- right 是「接收槽」，每次接收一個 `> pivot` 的值後往左縮。
- 因為 right 只被動退，退到相遇時不保證 `arr[right] <= pivot`，所以需要收尾 if。
- `if (left != right)` 防止相遇後做無效交換，並確保 right 的最終位置由收尾 if 決定。

---

### V2：`partition_v2`（雙指針 + swap 後雙向內縮）

```
left = start+1, right = end
while (left < right):
    內層 while: left 往右掃，跳過 arr[left] <= pivot
    內層 while: right 往左掃，跳過 arr[right] >= pivot
    if (left < right):
        swap(arr, left, right)
        left++   // 剛確認 arr[left] < pivot（已交換），排除
        right--  // 剛確認 arr[right] > pivot（已交換），排除

// 收尾：相遇點若 > pivot，right 再退一格
if (left == right && arr[right] > pivot) right--

swap(arr, start, right)   // 無 guard，允許 swap(start, start)
return right
```

**關鍵理解**：
- swap 後 `left++/right--` 的原因：兩個位置已確認放到正確區域，排除它們以縮小未處理範圍，並防止某些邊界情況下指針不前進。
- 仍需要收尾 if，原因同 V1（left 先掃，left 可能先撞到相遇點，此時 right 尚未找到安全位置）。
- 不加 `if (right != start)` guard 是因為 `swap(arr, i, i)` 只是自己換自己，不會破壞正確性（只是多一次無意義動作）。

---

### V3：`advance.partition`（先右後左雙指針，無收尾 if）

```
left = start, right = end   // left 從 start 出發（含 pivot 本身）
while (left < right):
    先掃 right: while (left < right && arr[start] <= arr[right]) right--
    再掃 left:  while (left < right && arr[start] >= arr[left])  left++
    swap(arr, left, right)   // 無條件 swap（left==right 時 swap 自己也無妨）

swap(arr, start, right)   // pivot 歸位
return right
```

**關鍵理解**：
- **先掃 right 的根本原因**：pivot 固定在 `arr[start]`，最後做 `swap(arr, start, right)` 把 pivot 放到 right 位置。這代表 right 是 pivot 的「最終落點」，right 必須先找到一個 `< pivot` 的位置確保落點安全，然後 left 再去填洞。如果先掃 left，left 可能先撞到相遇點，此時 right 還沒找到正確位置，pivot 歸位後結果可能出錯。
- 先掃 right 保證：相遇時 `arr[right] <= pivot`（right 已確認是安全落點），因此不需要收尾 `if (left==right && arr[right]>pivot) right--`。
- `arr[start]` 全程作為 pivot 參考值（不存入獨立變數），但這沒問題，因為 `arr[start]` 在 partition 過程中不會被覆蓋（left 從 start 出發，`arr[start] >= arr[left]` 的等號讓 left 跳過 start 本身）。

---

## 三、範例陣列 `[4, 2, 7, 1, 6, 3, 5]` 三版本 partition(0, 6) 高層次 trace

> 目的：看出三種寫法在同一輸入下的分區過程差異（只追蹤每輪 swap 結果，不逐行列出）。

### V1 trace

初始：pivot=4, left=1, right=6，arr=`[4, 2, 7, 1, 6, 3, 5]`

| 輪次 | left 掃到 | 動作 | arr 結果 | right |
|------|-----------|------|----------|-------|
| 1 | arr[2]=7 > 4，停（left=2） | swap(2,6)，right-- | `[4, 2, **5**, 1, 6, 3, **7**]` | 5 |
| 2 | arr[2]=5 > 4，停（left=2） | swap(2,5)，right-- | `[4, 2, **3**, 1, 6, **5**, 7]` | 4 |
| 3 | left 掃到 left=4，left==right，退出 | 不 swap | `[4, 2, 3, 1, 6, 5, 7]` | 4 |

收尾：arr[4]=6 > 4 → right--=3；swap(0,3)

**結果**：arr=`[1, 2, 3, 4, 6, 5, 7]`，**middle=3**

---

### V2 trace

初始：pivot=4, left=1, right=6，arr=`[4, 2, 7, 1, 6, 3, 5]`

| 輪次 | left 停 | right 停 | 動作 | arr 結果 | left/right |
|------|---------|----------|------|----------|------------|
| 1 | arr[2]=7（left=2） | arr[5]=3（right=5） | swap(2,5)，left++，right-- | `[4, 2, **3**, 1, 6, **7**, 5]` | left=3, right=4 |
| 2 | left=4，left==right，掃描停止 | right=4，left==right，掃描停止 | 不 swap | `[4, 2, 3, 1, 6, 7, 5]` | left=4, right=4 |

收尾：arr[4]=6 > 4 → right--=3；swap(0,3)

**結果**：arr=`[1, 2, 3, **4**, 6, 7, 5]`，**middle=3**

---

### V3 trace

初始：left=0, right=6，arr=`[4, 2, 7, 1, 6, 3, 5]`（pivot = arr[0] = 4）

| 輪次 | right 掃到 | left 掃到 | swap | arr 結果 | left/right |
|------|-----------|----------|------|----------|------------|
| 1 | arr[5]=3 < 4，停（right=5） | arr[2]=7 > 4，停（left=2） | swap(2,5) | `[4, 2, **3**, 1, 6, **7**, 5]` | left=2, right=5 |
| 2 | arr[3]=1 < 4，停（right=3） | left=3，left==right，掃描停止（left=3） | swap(3,3)，自己換自己 | 不變 | left=3, right=3 |

收尾（無收尾 if）：swap(0,3)（arr[0]=4↔arr[3]=1）

**結果**：arr=`[1, 2, 3, **4**, 6, 7, 5]`，**middle=3**

---

### 三版本 partition 結果對比

| 版本 | 第一次 partition 結果 | middle | 左區 [0..middle-1] | 右區 [middle+1..6] |
|------|----------------------|--------|---------------------|---------------------|
| V1 | `[1, 2, 3, 4, 6, 5, 7]` | 3 | `[1, 2, 3]` | `[6, 5, 7]` |
| V2 | `[1, 2, 3, 4, 6, 7, 5]` | 3 | `[1, 2, 3]` | `[6, 7, 5]` |
| V3 | `[1, 2, 3, 4, 6, 7, 5]` | 3 | `[1, 2, 3]` | `[6, 7, 5]` |

> **重點**：三個版本都把 pivot=4 放到 index=3（正確位置），左區全部 ≤ 4，右區全部 ≥ 4。右區的內部順序有差異（V1 為 [6,5,7]，V2/V3 為 [6,7,5]），但這不影響最終排序結果——遞迴呼叫會繼續處理各子區間。

---

## 四、常見 Bug 對照

| Bug 現象 | V1 觸發場景 | V2 觸發場景 | V3 觸發場景 |
|----------|------------|------------|------------|
| **等號方向寫反**（如 `<` 寫成 `>`） | `arr[left] <= pivot` 寫成 `arr[left] < pivot` → 遇等於值時 left 停下，可能把等於 pivot 的值換到右邊 | left/right 掃描條件反向，會導致相等元素歸到錯誤區域 | `arr[start] <= arr[right]` 或 `arr[start] >= arr[left]` 方向寫反，right/left 停在錯誤位置 |
| **先掃哪邊的問題** | 只有 left 掃，無此問題 | 先掃 left 可能讓 left 先撞到相遇點，right 還沒找到安全位置 → 收尾 if 救場；若移除收尾 if 則 pivot 歸位出錯 | 若改成先掃 left：left 先撞到相遇點，right 尚未確認安全，`swap(start, right)` 後 pivot 可能在 `> pivot` 的位置 → 排序錯誤 |
| **swap 後忘記移指針（V2 特有）** | 不適用 | 忘記 `left++/right--` → 下一輪掃描從同一位置開始，雖然內層 while 多半會推進，但在全等序列（如 `[2,2,2,2]`）可能導致死迴圈或多餘循環 | 不適用（V3 自然由外層 while 推進） |
| **忘記收尾 `right--`（V1/V2 特有）** | 漏寫 → 相遇點若 `arr[right] > pivot`，swap(start, right) 後 pivot 跑到 `> pivot` 的位置，分區錯誤 | 同 V1 | 不需要收尾 if，不存在此 bug |
| **V1/V2 忘記 `if (left != right)` / `if (left < right)` 的 swap guard** | 相遇後仍執行 swap 並做 `right--`，讓 right 提早退過界，影響收尾 if 的判斷 | 相遇後仍執行 swap 並做 `left++/right--`，讓指針越過相遇點，收尾 if 無法正確判斷 | 不適用（V3 外層 while 會自然結束） |

---

## 五、為什麼先掃 right 可以省掉收尾 if？

這是 V3 和 V1/V2 最根本的設計差異，值得單獨說明：

**V1/V2 的問題根源**（先掃 left 或只掃 left）：
- 外層 while 結束時，`left == right`，相遇點的值**不確定是 > 還是 ≤ pivot**。
- 若相遇點 `arr[right] > pivot`，pivot 不能放這裡 → 需要 `right--` 修正。

**V3 的解法**（先掃 right）：
- 每輪 **先讓 right 往左找到一個 `< pivot` 的位置**，確認 right 是安全落點，然後再讓 left 往右掃。
- 外層 while 結束時，`left == right`，而最後一步一定是 right 先停（right 找到 `< pivot` 停下），所以相遇點**保證是 `arr[right] <= pivot`**。
- 因此 `swap(arr, start, right)` 直接安全，無需收尾 if。

```
先掃 right → right 停在「< pivot」的位置（安全落點）
再掃 left  → left 從安全位置往右推
相遇 → 相遇點 = right 的最後停留點 → 保證 <= pivot
```

---

## 附錄：手推技巧與不變量

> 從原 SOP 保留的手推精華，複習時可搭配第三節的 trace 一起使用。

### A. 核心不變量記憶

| 版本 | 每輪結束後的不變量 |
|------|-------------------|
| V1 | `arr[start+1..right-1]` 全部 ≤ pivot；`arr[right..end]` 全部 ≥ pivot（包含剛被移過去的） |
| V2 | `arr[start+1..left-1]` 全部 ≤ pivot；`arr[right+1..end]` 全部 ≥ pivot |
| V3 | `arr[start..left-1]` 全部 ≤ pivot；`arr[right+1..end]` 全部 ≥ pivot |

### B. 手推固定檢查點（適用三種版本）

每次 partition 只在下列時機記錄狀態，不需逐行追蹤：

1. **進入 partition**：記 start, end, pivot, left, right, arr
2. **每次 swap 發生後**：記 arr 新狀態、left/right 新位置
3. **外層 while 結束**：記 left, right, arr（確認相遇點）
4. **收尾 if 是否觸發**（V1/V2）：記 right 是否做了 `right--`
5. **pivot 歸位後**：記 arr 最終狀態、回傳的 middle

### C. 遞迴呼叫的 start/end 不變性

`start`、`end` 都是 Java primitive `int`，**值傳遞**，子呼叫 return 後上一層的 `start/end` 完全不受影響：

```
quickSort(arr, start, middle - 1)  // 不含 middle
quickSort(arr, middle + 1, end)    // 不含 middle
// middle 已就位，再也不進 partition，是 QuickSort 原地排序的保證
```

### D. 一句話記憶法

- **V1**：left 找壞人（> pivot），右端 right 收容壞人，right 收縮；相遇後檢查相遇點，pivot 歸位。
- **V2**：left 找左邊的壞人，right 找右邊的壞人，一次換一對，換完雙向內縮；相遇後檢查，pivot 歸位。
- **V3**：先讓 right 找到安全落點（< pivot），再讓 left 往右填洞，相遇即是 pivot 落點，直接歸位，無需收尾 if。
