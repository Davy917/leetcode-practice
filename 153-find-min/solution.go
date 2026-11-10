/*
第一次練習看python
完整思路看java
再優化看go
*/
package main

import "fmt"

func findMin(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		m := (l + r) / 2
		if nums[m] > nums[r] {
			l = m + 1
		} else {
			r = m
		}
	}
	return nums[l]
}

func main() {
	var nums = []int{3, 4, 5, 1, 2}
	//var nums2 = []int{4, 5, 6, 7, 0, 1, 2}
	ans := findMin(nums)
	fmt.Print("ans = ", ans)
}
