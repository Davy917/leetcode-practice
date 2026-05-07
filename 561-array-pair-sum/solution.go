package main

import (
	"fmt"
	"sort"
)

func arrayPairSum(nums []int) int {
	sort.Ints(nums)
	fmt.Println(nums)
	result := 0
	for index := 0; index < len(nums); index += 2 {
		result += nums[index]
	}
	return result
}

func main() {
	nums := []int{1, 4, 3, 2, -56, -77, -21}
	fmt.Println("Ans = ", arrayPairSum(nums))
}
