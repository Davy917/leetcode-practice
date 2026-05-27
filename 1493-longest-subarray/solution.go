// 自己寫的, 非官方解
package main

import (
	"fmt"
)

func longestSubarray(nums []int) int {
	maxLen := 0
	isDeleted := false
	var deleteIndex = 0
	l, r := 0, 0
	for r < len(nums) {
		if nums[r] == 0 && isDeleted {
			l = deleteIndex + 1
			deleteIndex = r
		} else if nums[r] == 0 {
			isDeleted = true
			deleteIndex = r
		}
		maxLen = max(maxLen, r-l+1)
		r++
	}
	return maxLen - 1
}
func main() {
	nums := []int{0, 1}
	fmt.Println("Ans = ", longestSubarray(nums))
}

/*
可以包含一個 0 的滑動窗口, 回傳結果的時候要記得 -1
遇到第二個 0, 要移動 l

看到這題時, 就聯想487題
487-find-max-consecutive-ones/Solution.java
可以先去把487題上面這版本看懂

寫了一個可讀性更好的java版本, 思路都是窗口內最多一個 0
1493-longest-subarray/Solution.java
*/
