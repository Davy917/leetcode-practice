/*
第一次練習看java
第二次練習思路看go
*/
package main

import (
	"fmt"
)

func findPeakElement(nums []int) int {
	l, r := -1, len(nums)
	for l < r {
		m := (l + r) / 2
		if m+1 < len(nums) && nums[m] < nums[m+1] {
			l = m
		} else if m-1 > -1 && nums[m-1] > nums[m] {
			r = m
		} else {
			return m
		}
	}
	return -1
}
func main() {
	nums := []int{1, 2, 1, 3, 5, 6, 4}
	fmt.Println("Ans = ", findPeakElement(nums))
}

/*
4, 6, 5, 3, 1, 2, 1

第一個想法:
二分查找
nums[m - 1] < nums[m] > nums[m + 1]
nums[m] 就是答案

產生問題:
1, 3, 5, nums[m] = 3, 此時, 要往左還是往右?

題目提示:
nums[i] != nums[i+1]

上述問題, 一律往大的那邊走!
每次選擇的時候都選擇大的, 最後一定會走到峰值

定義:
l = -1, 		l本身 & 排在它前面的, 不可能是答案
r = len(nums),  r本身 & 排在它前面的, 不可能是答案
l, r是不斷收緊的邊界, 斷定最後一定會找到一個 m

滿足:
nums[m - 1] < nums[m] && nums[m] > nums[m + 1]
*/
