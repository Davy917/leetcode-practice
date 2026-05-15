/*
雙指針, 自己寫出來的
*/
package main

import "fmt"

func removeDuplicates(nums []int) (numsLength int) {
	noDuplicated := 0
	index := 1

	for index < len(nums) {
		for index < len(nums) && nums[index] == nums[index-1] {
			index++
		}
		if index < len(nums) {
			noDuplicated++
			nums[noDuplicated] = nums[index]
			fmt.Printf("index = %d, noDuplicated = %d, nums = %v\n", index, noDuplicated, nums)
			index++
		}
	}
	return len(nums[:noDuplicated+1])
}

func main() {
	//nums := []int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}
	nums2 := []int{1, 1, 2, 3}
	fmt.Println("Ans = ", removeDuplicates(nums2))
}
