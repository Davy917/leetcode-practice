package main

import (
	"fmt"
	"sort"
)

func intersect(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	var shorter []int
	var longer []int
	if len(nums1) >= len(nums2) {
		shorter = nums1
		longer = nums2
	} else {
		shorter = nums2
		longer = nums1
	}

	left := 0
	right := len(longer) - 1
	var result []int

	for index := 0; index < len(shorter)-1; index++ {
		target := shorter[index]
		for left <= right {
			middle := left + (right-left)/2
			fmt.Printf("left = %d, right = %d, middle = %d\n", left, right, middle)
			if longer[middle] > target {
				right = middle - 1
			} else if longer[middle] < target {
				left = middle + 1
			} else {
				//TODO
			}
		}
	}
	return result
}

func main() {
	var nums1 = []int{1, 2, 2, 1}
	var nums2 = []int{2, 2}
	ans := intersect(nums1, nums2)
	fmt.Println("ans = ", ans)
}

/*
1 1 2 2
2 3 3
2 2
*/
