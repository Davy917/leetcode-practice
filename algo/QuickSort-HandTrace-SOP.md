# QuickSort 手寫推導 SOP

> 適用於 `QuickSort.java` 中的 partition 實作（pivot = arr[start]，最後 exchange(start, right)）

---

## 一、核心不變量（Invariants）

每次進入 `partition(arr, start, end)` 時，永遠記住這三件事：

| 角色 | 代表的意義 |
|------|-----------|
| `pivot` | `arr[start]` 的值，全程不變（即使 arr[start] 被換掉，pivot 變數仍保留原值） |
| `left` | 從 start+1 出發，往右掃描，找第一個 **> pivot** 的位置 |
| `right` | 從 end 出發，每找到一個 > pivot 就接收過來並往左縮，最終成為 **pivot 放置點** |

---

## 二、partition 流程固定節奏

```
進入 partition
  │
  ├─ 設定 pivot = arr[start], left = start+1, right = end
  │
  ╔═════════════ while (left < right) ═══════════════╗
  ║  內層 while: 讓 left 右移，跳過所有 <= pivot 的值  ║
  ║  → left 停在「第一個 > pivot」或追上 right 為止    ║
  ║                                                   ║
  ║  if (left != right):                              ║
  ║    exchange(arr, left, right)  // 把 >pivot 丟右邊 ║
  ║    right--                                        ║
  ╚═══════════════════════════════════════════════════╝
  │
  ├─ 收尾判斷①: if (left==right && arr[right]>pivot) right--
  │   → 保證 right 指的位置 <= pivot（pivot 才能放這裡）
  │
  ├─ 收尾判斷②: if (right != start) exchange(arr, start, right)
  │   → 把 pivot 放回正確位置；若 right==start 表示 pivot 已是最小值，不需移動
  │
  └─ return right  // middle = pivot 的最終下標
```

---

## 三、手寫推導表格（每輪只填 6 欄）

每次只在下列**固定檢查點**寫一次狀態，不需要逐行追蹤：

| # | 檢查點 | start | end | pivot | left | right | arr 狀態 | 動作說明 |
|---|--------|-------|-----|-------|------|-------|----------|----------|
| 1 | 進入 partition | | | | | | | |
| 2 | 內層 while 結束（left 停哪？） | | | | | | | |
| 3 | 若做了 exchange | | | | | | | 紀錄交換結果 |
| 4 | left/right 相遇 | | | | | | | |
| 5 | 收尾 right-- 是否發生 | | | | | | | |
| 6 | 最後 exchange(start, right) | | | | | | | middle = right |

---

## 四、完整示範：arr = {4, 2, 7, 1, 6, 3, 5}

### Layer 0：quickSort(arr, 0, 6)

**partition(arr, 0, 6)**

- pivot = 4, left = 1, right = 6
- 陣列：`[4, 2, 7, 1, 6, 3, 5]`

| 輪次 | left 掃到哪 | 動作 | 交換後陣列 | right |
|------|-------------|------|-----------|-------|
| 1 | left=2（arr[2]=7 > 4，停） | exchange(2, 6)，right-- | `[4, 2, 5, 1, 6, 3, 7]` | 5 |
| 2 | left=4（arr[4]=6 > 4，停） | exchange(4, 5)，right-- | `[4, 2, 5, 1, 3, 6, 7]` | 4 |
| 3 | left=4，right=4，left<right 不成立 → 退出 while | | | |

收尾：arr[4]=3 <= 4，不需 right--  
exchange(arr, 0, 4) → `[3, 2, 5, 1, 4, 6, 7]`... 

> ⚠️ 注意：arr 在 while 迴圈中持續變動，手推時每輪都要更新當下的陣列狀態。

實際執行後 **middle = 3**（`arr[3] = 4`），陣列變為：`[1, 2, 3, 4, 6, 5, 7]`

---

### 遞迴 layer 追蹤（完整版）

```
layer0：quickSort(arr, 0, 6)
  partition → middle=3，arr=[1,2,3,4,6,5,7]
  呼叫左邊 → layer1：quickSort(arr, 0, 2)

  layer1：quickSort(arr, 0, 2)
    partition → middle=0
    呼叫左邊 → layer2：quickSort(arr, 0, -1)

    layer2：quickSort(arr, 0, -1)
      start>=end → return（回到 layer1，start/end 仍為 0/2）

    layer1：quickSort(arr, 0, 2)
    呼叫右邊 → layer2：quickSort(arr, 1, 2)

    layer2：quickSort(arr, 1, 2)
      partition → middle=2
      呼叫左邊 → layer3：quickSort(arr, 1, 1)

      layer3：quickSort(arr, 1, 1)
        start>=end → return（回到 layer2）

      layer2：quickSort(arr, 1, 2)
      呼叫右邊 → layer3：quickSort(arr, 3, 2)

      layer3：quickSort(arr, 3, 2)
        start>=end → return（回到 layer2）

      layer2：quickSort(arr, 1, 2)
        return（左右都處理完，回到 layer1）

    layer1：quickSort(arr, 0, 2)
      return（左右都處理完，回到 layer0）

layer0：quickSort(arr, 0, 6)
  呼叫右邊 → layer1：quickSort(arr, 4, 6)

  layer1：quickSort(arr, 4, 6)
    partition → middle=5
    呼叫左邊 → layer2：quickSort(arr, 4, 4)

    layer2：quickSort(arr, 4, 4)
      start>=end → return（回到 layer1）

    layer1：quickSort(arr, 4, 6)
    呼叫右邊 → layer2：quickSort(arr, 6, 6)

    layer2：quickSort(arr, 6, 6)
      start>=end → return（回到 layer1）

    layer1：quickSort(arr, 4, 6)
      return（左右都處理完，回到 layer0）

layer0：quickSort(arr, 0, 6)
  return（整體排序完成）
```

---

## 五、關鍵提醒：為什麼 `start/end` 回到上一層後不會改變？

`start`、`end` 都是 Java primitive `int`，**值傳遞**。  
每一層遞迴的 `start/end` 是獨立副本，子呼叫 return 後，上一層的 `start/end` 完全不受影響。

```
layer1 呼叫 quickSort(arr, 0, -1)
          ↓ layer2 執行並 return
layer1 回來時，start=0, end=2 完全不變
```

---

## 六、常見錯誤檢核清單

| 問題 | 正確理解 |
|------|----------|
| `if (left==right && arr[right]>pivot)` 是用來「補做交換」？ | ❌ 這個 if 只做 `right--`，是**修正 pivot 的放置位置**，不做任何元素交換 |
| `if (left != right)` 是怕「換不了」？ | ❌ 是避免**相遇時做無效交換並提前執行 right--**，讓 right 的決策留給收尾 if |
| `if (right != start)` 多餘？ | ❌ pivot 若是區間最小值，right 最後會退回 start，此時不需要交換 |
| 每層遞迴 return 後 start/end 會改變？ | ❌ 值傳遞，子呼叫不影響上層的參數 |
| 手推要每行都追蹤？ | ❌ 只在「固定 6 個檢查點」記錄，不需要逐行 |

---

## 七、一句話總結推導法

> 只要記住：**pivot 固定在 start、left 找 > pivot、right 接收 > pivot 並往左縮**，每輪照固定節奏填表，不需要死背任何一行程式碼。
