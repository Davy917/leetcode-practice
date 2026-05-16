/*
自己寫的暴力解, 官方解答看solution2.go
*/
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
	nums := []int{0, 1, 0, 3, 12}
	//moveZeroes(nums)
	moveZeroes_v2(nums)
	fmt.Println("Ans = ", nums)
}
