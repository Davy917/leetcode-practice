/*
自己寫的java, python
自己寫的優化版go
*/
package main

import "fmt"

func longestOnes(nums []int, k int) int {
	deque := make([]int, 0, len(nums)) // 一次分配到位，不 re-allocate
	front := 0
	l, r := 0, 0
	maxLen := 0
	for r < len(nums) {
		if nums[r] == 0 {
			deque = append(deque, r)
			k -= 1
			if k == -1 {
				l = deque[front] + 1
				front++ // 指標後移，不建立新 slice
				k = 0
			}
		}
		maxLen = max(maxLen, r-l+1)
		r++
	}
	return maxLen
}
func main() {
	nums := []int{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}
	fmt.Println("Ans = ", longestOnes(nums, 3))
}

/*
優化思路:
假設我們有個變數temp, r炸掉之後L可以直接挪到temp位子不用每次都L++
這樣時間複雜度會好很多

每次k == -1, L 挪到temp
temp的定義是最舊的0的右邊那個鄰居

我們可以用deque來存每個0的位置, 這樣隨時都可以知道最舊的0是誰

假設
deque = [1, 4, 5, 9]
最舊的0在1, 那下一個L就在1隔壁, L = 2

deque版本會比滑動窗口再快一點, 缺點是消耗內存

注意:
一定要靠front變數才會快, 如果正常出入隊運行起來就慢了
oldest = deque[0]
deque = deque[1:]
L = oldest + 1
*/
