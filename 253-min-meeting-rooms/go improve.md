你目前用了 `map` + 額外的 `temp` slice 來排序，步驟比較多。其實可以直接用 `sort.Slice` 對 `intervals` 排序，一行就搞定：

```go
sort.Slice(intervals, func(i, j int) bool {
    return intervals[i][0] < intervals[j][0]
})
```

這樣可以：
- ❌ 不需要額外的 `map`
- ❌ 不需要 `temp` slice
- ❌ 不需要重新組合 `intervals`
- ✅ 空間複雜度從 O(n) 降到 O(1)（排序本身除外）
`sort.Slice` 的使用方式很簡單，需要傳入兩個參數：

### 基本語法
```go
sort.Slice(要排序的 slice, 比較函式)
```


```go
sort.Slice(intervals, func(i, j int) bool {
    return intervals[i][0] < intervals[j][0]
})
```

### 參數說明

| 參數 | 說明 |
|------|------|
| `intervals` | 你要排序的 slice |
| `func(i, j int) bool` | 比較函式，回傳 `true` 代表 `i` 應該排在 `j` 前面 |

### 比較函式的邏輯

```go
// 升序（小到大）：i 的值 < j 的值
return intervals[i][0] < intervals[j][0]

// 降序（大到小）：i 的值 > j 的值
return intervals[i][0] > intervals[j][0]
```

### 你的題目中的用法

```go
// 按會議開始時間升序排序
sort.Slice(intervals, func(i, j int) bool {
    return intervals[i][0] < intervals[j][0]
})
// [[7,10],[2,4]] → [[2,4],[7,10]]
```

排序完成後就可以直接遍歷 `intervals`，不需要再透過 map 重新組合了。



## 這題如果不把 [[7,10], [2,4]] 轉成 [[2,4], [7,10]] 有辦法做嗎?


有的！有一個思路叫**掃描線（Sweep Line）**，不需要對區間本身排序：

### 核心想法

把每個會議拆成兩個**事件**：
- 會議開始 → `+1`（需要一間會議室）
- 會議結束 → `-1`（釋放一間會議室）

然後按時間順序處理這些事件，**同一時間的最大值**就是答案。

### 範例：`[[7,10],[2,4]]`

| 時間 | 事件 | 累計會議室 |
|------|------|-----------|
| 2 | +1 | 1 |
| 4 | -1 | 0 |
| 7 | +1 | 1 |
| 10 | -1 | 0 |

最大值 = **1**

### 實作提示

在 Go 中你可以用 `map[int]int` 來記錄每個時間點的變化：

```go
events := make(map[int]int)
for _, interval := range intervals {
    events[interval[0]]++   // 開始 +1
    events[interval[1]]--   // 結束 -1
}
```

然後收集所有時間點，排序時間點，再依序掃描累加。

### 需要注意

- 如果同一個時間點同時有 `+1` 和 `-1`，要**先處理 -1**（會議先結束再開始），這樣才不會多算會議室。

這個方法雖然還是要排序時間點，但概念上不需要先對區間做排序。有興趣的話試試看！