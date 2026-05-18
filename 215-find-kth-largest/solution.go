package main

import (
	"fmt"
	"sort"
)

func findKthLargest(nums []int, k int) (result int) {
	sort.Ints(nums)
	fmt.Println(nums)
	return
}

func partition(nums []int, left int, right int) int {
	//randomIndex := left + rand.Intn(right-left+1)
	//nums[left], nums[randomIndex] = nums[randomIndex], nums[left]
	//fmt.Printf("randomIndex = %d, nums = %v\n", randomIndex, nums)

	pivot, le := nums[left], left
	for i := left + 1; i <= right; i++ {
		fmt.Printf("out i = %d, le = %d, pivot = %d\n", i, le, pivot)
		if nums[i] <= pivot {
			le++
			fmt.Printf("in i = %d, le = %d, pivot = %d\n", i, le, pivot)
			nums[le], nums[i] = nums[i], nums[le]
			fmt.Println(nums)
		}
	}
	nums[left], nums[le] = nums[le], nums[left]
	fmt.Println(nums)
	return le
}
func main() {
	nums := []int{3, 2, 3, 1, 2, 4, 5, 5, 6}
	fmt.Println(partition(nums, 0, len(nums)-1))
	//k := 4
	//fmt.Println("Ans = ", findKthLargest(nums, k))
}

/*
官方解法:
https://leetcode.cn/leetbook/read/sliding-window-and-two-pointers/rli5s3/
*/
