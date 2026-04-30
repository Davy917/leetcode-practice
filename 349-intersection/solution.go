package main

import (
	"fmt"
	"sort"
)

func intersection(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	var shorter []int
	var longer []int
	if len(nums1) >= len(nums2) {
		shorter = nums2
		longer = nums1
	} else {
		shorter = nums1
		longer = nums2
	}

	var result []int
	var index = 0
	for index < len(shorter) {
		if index > 0 && shorter[index] == shorter[index-1] {
			index++
			continue
		}
		target := shorter[index]
		left := 0
		right := len(longer) - 1
		for left <= right {
			middle := left + (right-left)/2
			if longer[middle] > target {
				right = middle - 1
			} else if longer[middle] < target {
				left = middle + 1
			} else {
				result = append(result, target)
				break
			}
		}
		index++
	}
	return result
}
func main() {
	var nums1 = []int{1, 2, 2, 1}
	var nums2 = []int{2, 2}
	fmt.Print("ans = ", intersection(nums1, nums2))
}
