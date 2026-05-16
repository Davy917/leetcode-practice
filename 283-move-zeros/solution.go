package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

func moveZeroes(nums []int) {
	for i := 0; i < len(nums); i++ {
		for j := 0; j < len(nums); j++ {
			if nums[j] == 0 && j+1 < len(nums) {
				gft.Swap(&nums[j], &nums[j+1])
			}
		}
	}
	fmt.Println(nums)
}

func main() {
	//nums := []int{0, 1, 0, 3, 12}
	nums2 := []int{0, 0, 1}
	moveZeroes(nums2)
	fmt.Println("Ans = ", nums2)
}

/*
0, 1, 0, 3, 12
1, 0, 3, 12, 0
1, 3, 12, 0, 0


0, 1, 0, 3, 12
1, 0, 0, 3, 12
1, 0, 0, 3, 12
1, 0, 3, 0, 12
1, 0, 3, 12, 0

1, 3, 0, 12, 0
1, 3, 12, 0, 0
*/
