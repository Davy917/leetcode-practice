# P034 Search Range FAQ（ultra 模板）

> 本文件整理「P034_searchRange」在對話中針對二分搜尋細節的問答，重點聚焦：  
> `right = middle` 的語意、第一段 lower_bound 逐輪推進、第一段結束後驗證條件、第二段找結束位置的逐輪推進，以及為什麼第二段 `middle` 要上取整。

---

## 0. 對照程式片段（P034）

```java
// 第一段：找起始位置（lower_bound）
while (left + 1 != right){
    int middle = left + (right - left) / 2;
    if (nums[middle] < target)
        left = middle;
    else if (nums[middle] > target)
        right = middle;
    else
        right = middle;
}

if (right >= nums.length || nums[right] != target)
    return new int[]{-1, -1};

// 第二段：找結束位置
while (left < right){
    int middle = left + (right - left + 1) / 2; // 上取整
    if (nums[middle] > target){
        right = middle - 1;
    } else {
        left = middle;
    }
}
```

---

## 1) 問題：`right = middle` 會把 `middle` 排除嗎？

### 回答
在 **ultra（開區間哨兵）模板** 中，候選索引集合是 `(left, right)`，也就是 `left+1 ... right-1`。  
當執行 `right = middle` 後：

- `middle` 會成為新的右邊界 `right`
- 從「下一輪候選集合」角度，`middle` 不再位於 `(left, right)` 內
- 但在「找邊界」題（如本題第一段 lower_bound）中，`right` 本身就是最後答案可能落點，所以 `middle` 仍可能成為最後交出去的位置

簡單說：  
**被排除的是「下一輪待比較集合」身分；被保留的是「邊界候選落點」身分。**

---

## 2) 問題：第一段（lower_bound）是如何逐輪推進的？

測資：

- `nums = [1,1,1,1,2,2,2,2,5]`（索引 `0..8`）
- `target = 2`
- 初始 `left = -1`, `right = 9`
- 候選集合定義：`(left, right)`（即 `left+1 .. right-1`）

### Round 1
```text
left=-1, right=9
middle = -1 + (9-(-1))/2 = 4
nums[4] = 2 == target  => right = 4
```
更新後：
- `left=-1, right=4`
- 候選集合索引：`0..3`
- `right=4` 成為目前最左 `>= target` 的候選落點

### Round 2
```text
left=-1, right=4
middle = -1 + (4-(-1))/2 = 1
nums[1] = 1 < target  => left = 1
```
更新後：
- `left=1, right=4`
- 候選集合索引：`2..3`

### Round 3
```text
left=1, right=4
middle = 1 + (4-1)/2 = 2
nums[2] = 1 < target  => left = 2
```
更新後：
- `left=2, right=4`
- 候選集合索引：`3..3`

### Round 4
```text
left=2, right=4
middle = 2 + (4-2)/2 = 3
nums[3] = 1 < target  => left = 3
```
更新後：
- `left=3, right=4`
- 候選集合為空（`left+1 == right`）

### 第一段結束
`left + 1 == right`，故第一段收斂到 `right = 4`，即第一個 `>= target` 的位置（lower_bound）。

---

## 3) 問題：第一段結束後為什麼要檢查這段？

```java
if (right >= nums.length || nums[right] != target)
    return new int[]{-1, -1};
```

### 回答
第一段只保證 `right` 是「第一個 `>= target` 的位置」，**不保證等於 target**。  
因此要做資格驗證：

1. `right >= nums.length`：表示整個陣列都 `< target`，候選位置越界  
2. `nums[right] != target`：表示第一個 `>= target` 的值其實大於 target（陣列中沒有 target）

只要任一成立，就可直接判定答案是 `[-1, -1]`。

---

## 4) 問題：第二段（找結束位置）如何逐輪推進？

延續上例，第一段得到 `ans[0] = 4`，第二段初始化：

- `left = 4`
- `right = 8`

程式：

```java
while (left < right){
    int middle = left + (right - left + 1) / 2; // 上取整
    if (nums[middle] > target){
        right = middle - 1;
    } else {
        left = middle;
    }
}
ans[1] = left;
```

### Round 1
```text
left=4, right=8
middle = 4 + (8-4+1)/2 = 6
nums[6] = 2 <= target  => left = 6
```

### Round 2
```text
left=6, right=8
middle = 6 + (8-6+1)/2 = 7
nums[7] = 2 <= target  => left = 7
```

### Round 3
```text
left=7, right=8
middle = 7 + (8-7+1)/2 = 8
nums[8] = 5 > target   => right = 7
```

此時 `left=7, right=7`，結束，得到 `ans[1] = 7`。

---

## 5) 問題：第二段為什麼一定要上取整？

### 回答
第二段會在 `nums[middle] <= target` 時做 `left = middle`。  
若 `middle` 用下取整，在 `left=7, right=8` 時會卡住：

### 情況 A：下取整（錯誤風險：死迴圈）
```text
middle = left + (right-left)/2
       = 7 + (8-7)/2
       = 7
```
若 `nums[7] == target`，更新 `left = 7` 後狀態不變，下一輪仍是 `left=7,right=8,middle=7`，形成無窮迴圈。

### 情況 B：上取整（正確收斂）
```text
middle = left + (right-left+1)/2
       = 7 + (8-7+1)/2
       = 8
```
此時會比較右側格：
- 若 `nums[8] > target`，`right = 7`，立即收斂
- 若 `nums[8] <= target`，`left = 8`，也會收斂

**結論**：當更新規則可能是 `left = middle` 時，`middle` 要能在兩格狀態下取到右格，才能保證進度與終止。

---

## 6) 問題：可以怎麼一句話記這題兩段二分？

### 回答
- 第一段找左邊界（起點）：收縮右邊時用 `right = middle`（下取整可）  
- 第二段找右邊界（終點）：收縮左邊時用 `left = middle`，因此 `middle` 必須上取整避免卡住

最終就是：
1. 先找 `lower_bound`（第一個 `>= target`）  
2. 驗證是否真的等於 `target`  
3. 再找最右邊的 `target` 位置

