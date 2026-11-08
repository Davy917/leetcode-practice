package main

import "fmt"

func search(nums []int, target int) int {
	l, r := -1, len(nums)
	for l+1 < r {
		m := (l + r) / 2
		if target > nums[m] {
			l = m
		} else if target < nums[m] {
			r = m
		} else {
			return m
		}
	}
	return -1
}
func main() {
	nums := []int{-1, 0, 3, 5, 9, 12}
	fmt.Println("Ans = ", search(nums, 9))
}
