package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

func findMaxAverage(nums []int, k int) float64 {
	maxVal := gft.ArrSumInt(nums[:k])
	windowSum := maxVal

	for left, index := 0, k-1; index < len(nums); left, index = left+1, index+1 {
		if index > k-1 {
			windowSum += nums[index]
		}
		maxVal = max(maxVal, windowSum)
		windowSum -= nums[left]
	}
	return float64(maxVal) / float64(k)
}

func main() {
	nums := []int{1, 12, -5, -6, 50, 3}
	k := 4
	fmt.Println("Ans = ", findMaxAverage(nums, k))
}
