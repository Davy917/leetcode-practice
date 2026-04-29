package main

import "fmt"

func findMin(nums []int) int {
	left := 0
	right := len(nums) - 1
	for left < right {
		middle := left + (right-left)/2
		fmt.Printf("left = %d, right = %d, middle = %d\n", left, right, middle)
		if nums[middle] > nums[right] {
			left = middle + 1
		} else {
			right = middle
		}
	}
	return nums[right] //為什麼return right??
}

func main() {
	var nums = []int{3, 4, 5, 1, 2}
	//var nums2 = []int{4, 5, 6, 7, 0, 1, 2}
	ans := findMin(nums)
	fmt.Print("ans = ", ans)
}

/*
你這題的重點在「迴圈不變量（invariant）」，我直接用它來判斷最嚴謹的 return。
- [x] 先定義你的程式在維護什麼條件
- [x] 分析迴圈結束時 `left/right/middle` 各自代表什麼
- [x] 給出最嚴謹的 return 寫法建議

在你這個版本（`153-find-min/solution.go`）中，**最嚴謹是 `return nums[left]`（或等價的 `nums[right]`）**，**不是 `nums[middle]`**。

---

理由很簡單：

- 你的 `for left < right` 每次都把搜尋範圍縮到仍然包含最小值的區間 `[left, right]`
- 結束條件是 `left == right`
- 所以結束時，最小值索引就是這唯一位置：`left`（同時也等於 `right`）

因此：

- `return nums[left]` ✅
- `return nums[right]` ✅（因為此時 `left == right`）
- `return nums[middle]` ❌（`middle` 是最後一次迭代時的中點，迴圈結束後不保證等於答案索引）

---

如果你問「風格上哪個更清楚」：
我會建議改成 `return nums[left]`，因為你平常會把答案想成「左邊界收斂到答案」，語意更直觀。

可改成：

```go
for left < right {
    middle := left + (right-left)/2
    if nums[middle] > nums[right] {
        left = middle + 1
    } else {
        right = middle
    }
}
return nums[left]
```

你目前寫 `return nums[right]` 在邏輯上也完全正確。
*/
