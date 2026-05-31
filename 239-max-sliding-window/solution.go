package main

import (
	"fmt"
	"math"
)

func maxSlidingWindow(nums []int, k int) (result []int) {
	maxVal := math.MinInt
	counting := make(map[int]int)
	l, r := 0, 0
	for r < len(nums) {
		maxVal = max(maxVal, nums[r], nums[l])
		counting[]
		if r-l == k-1 {
			result = append(result, maxVal)
			l++
		}
		r++
	}
	return
}

func main() {
	nums := []int{7, -9, 6, 2, -7, 6}
	k := 3
	fmt.Println("Ans = ", maxSlidingWindow(nums, k))
}

/*
7 -9 6
7走了, 最大值變成6, -9變成第二大, 拿6跟nums[r]比較 6 > nums[r]但 nums[r] > -9 所以nums[r]變第2大
*/