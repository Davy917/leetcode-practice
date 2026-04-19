# Binary Search SOP (5 分鐘速讀速查版)

## 1) 先看這段：5 分鐘核心觀念

- **Basic 版**： 用 `algo/BinarySearch/BinarySearch_basic.java`，區間是 **`[left, right]`**（左右都包含）
- **Advance 版**： 用 `algo/BinarySearch/BinarySearch_advance.java`，區間是 **`[left, right)`**（左含右不含）
- 兩版都在做同一件事：每輪用 `middle` 把搜尋區間縮小一半
- 只要記住一句：
  - `[]`： 右界是「最後一個有效索引」
  - `[)`： 右界是「邊界（不含），有效到 right-1」

---

## 2) Basic vs Advance 對照表

| 項目 | Basic (`[left, right]`) | Advance (`[left, right)`) |
|---|---|---|
| 參考檔案 | `BinarySearch_basic.java` | `BinarySearch_advance.java` |
| 初始值 | `left = 0, right = nums.length - 1` | `left = 0, right = nums.length` |
| while 條件 | `left <= right` | `left < right` |
| `nums[middle] < target` | `left = middle + 1` | `left = middle + 1` |
| `nums[middle] > target` | `right = middle - 1` | `right = middle` |
| 終止時機 | `left > right`（區間失效） | `left == right`（空區間 `[left, left)`） |

---

## 3) 圖解：`[left, right]` vs `[left, right)`

假設索引如下：

```text
index:  0   1   2   3   4   5   6   7
```

### A. Basic：`[left, right]`（右界包含）

```text
[0, 6]
index:  0   1   2   3   4   5   6   7
        [=======================]
         ^                      ^
       left                   right (包含)
```

### B. Advance：`[left, right)`（右界不包含）

```text
[0, 7)
index:  0   1   2   3   4   5   6   7
        [=======================)
         ^                      ^
       left                   right (不包含)
```

> 所以在 advance 中，`right = middle` 後，區間變成 `[left, middle)`，`middle` 會自然被排除，不需要再 `-1`。

---

## 4) 三個常見疑問（Advance 版）

### Q1. 為什麼 `while (left < right)` 沒有等號？

- 因為 advance 用的是 `[left, right)`。
- 當 `left == right` 時，區間是 `[left, left)`，是**空區間**，代表已無元素可查，應停止。
- 若寫成 `<=`，會在空區間時仍進迴圈，容易造成邏輯錯誤或不收斂。

### Q2. 為什麼 `right = middle` 不用 `-1`？

- 因為 `right` 是 **exclusive（不含）**。
- 設成 `right = middle` 後，新區間是 `[left, middle)`，有效索引只到 `middle - 1`。
- 也就是說 `middle` 已經被排除，所以不需再減 1。

### Q3. 迴圈後 `if (left != nums.length && nums[left] == target)` 什麼情況會用到？

- 在目前 `BinarySearch_advance.java` 的 `search()` 寫法中（迴圈內遇到相等就直接 `return middle`），這段在正常流程下**不會觸發**。
- 這種「迴圈後再檢查」常見於**邊界型二分**：`lower_bound / upper_bound / 插入位置`。
- 那類寫法通常不在相等時立即回傳，而是先把邊界收斂完，再用 `left` 做最後確認。

---

## 5) 測試案例（可明顯看出兩版差異）

### Case：`nums = [5]`, `target = 4`（長度 1，且不存在）

#### Basic (`[left, right]`)

- 初始：`left=0, right=0`
- `while (left <= right)` 成立，進入
- `middle=0`, `nums[0]=5 > 4` ⇒ `right = middle - 1 = -1`
- 下一輪 `0 <= -1` 不成立，結束回傳 `-1`

#### Advance (`[left, right)`)

- 初始：`left=0, right=1`
- `while (left < right)` 成立，進入
- `middle=0`, `nums[0]=5 > 4` ⇒ `right = middle = 0`
- 下一輪 `0 < 0` 不成立，區間成空 `[0,0)`，回傳 `-1`

### 這個 case 的關鍵差異

- Basic：`right` 可能變成 `-1`
- Advance：`right` 會收斂到 `left`（空區間），**不會變成 `-1`**

---

## 6) 常見錯誤清單（快速自查）

- 把兩種區間寫法混用（`right` 初始值、while 條件、更新規則要成套）
- Advance 寫成 `while (left <= right)`（容易在空區間時多跑）
- Basic 的 `nums[middle] > target` 卻寫成 `right = middle`（可能卡住）
- Advance 的 `nums[middle] > target` 卻寫成 `right = middle - 1`（縮過頭）
