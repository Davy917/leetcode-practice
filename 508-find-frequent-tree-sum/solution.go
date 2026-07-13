package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func findFrequentTreeSum(root *TreeNode) []int {
	count := map[int]int{}
	dfs(root, count)
	ans := findMaxFreq(count)
	return ans
}
func dfs(root *TreeNode, count map[int]int) int {
	if root == nil {
		return 0
	}
	leftSum := dfs(root.Left, count)
	rightSum := dfs(root.Right, count)
	count[leftSum+rightSum+root.Val.(int)] += 1
	return leftSum + rightSum + root.Val.(int)
}
func findMaxFreq(count map[int]int) (result []int) {
	maxFreq := 1
	for _, curFreq := range count { //找到最大的那個 value, aka maxFreq
		if curFreq > maxFreq {
			maxFreq = curFreq
		}
	}
	for k, curFreq := range count { //跟 maxFreq 一樣大的都是答案, 全部裝進 result
		if curFreq == maxFreq {
			result = append(result, k)
		}
	}
	return result
}
func main() {
	levelOrder := []any{5, 2, -3}
	root := Tree.BuildLevelOrderTree(levelOrder)
	fmt.Println("Ans = ", findFrequentTreeSum(root))
}

/*
關鍵點：
root.Val.(int) 把 any 轉成 int
不要像原本那樣 int(root.Val)（這樣會當 root.Val 是可以轉型的型別，但 any 不行）

比較這兩種寫法的差異
int(root.Val)
root.Val.(int)

這兩種寫法在 Go 裡意思完全不同。

- [x] `int(root.Val)`：**型別轉換**（type conversion）
- [x] `root.Val.(int)`：**型別斷言**（type assertion）

---

## 1) `int(root.Val)` — 型別轉換

這是把某個值轉成另一個型別。
但因為 `root.Val` 是 `any` 型別，Go 不知道怎麼轉，所以會編譯失敗。

```go
root.Val.(int)  // 不能這樣寫
```

你會看到錯誤類似：
```
cannot convert root.Val (type any) to type int
```

---

## 2) `root.Val.(int)` — 型別斷言

這是告訴 Go：「我知道 `root.Val` 裡面存的是 `int`，請幫我拿出來」。

```go
val := root.Val.(int)  // 正確
```

如果實際存的不是 `int`，會 panic。

---

## 為什麼你的 `any` 要用斷言？

因為 `any` 是空介面，Go 編譯期不知道裡面是什麼型別。
所以只能用 **型別斷言** 在執行期確認並提取。

---

## 兩種對比

| 情況 | 寫法 | 說明 |
|------|------|------|
| 從 `int` 轉到 `float64` | `float64(x)` | 型別轉換，直接轉 |
| 從 `any` 拿出 `int` | `x.(int)` | 型別斷言，執行期檢查 |
| 從 `any` 拿出 `int`（安全） | `x, ok := x.(int)` | 帶 ok 的斷言，不會 panic |

---

## 你的程式裡

```go
count[leftSum+rightSum+root.Val.(int)] += 1
```

這裡用 `.(int)` 是對的，因為 `root.Val` 是 `any`。

如果你要更安全，也可以改成：

```go
val, ok := root.Val.(int)
if !ok {
	return 0 // 或 panic
}
count[leftSum+rightSum+val] += 1
```
*/
