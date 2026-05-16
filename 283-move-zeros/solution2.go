/*
官方題解
*/
package main

import "fmt"

func moveZeroes_v2(nums []int) {
	left, right := 0, 0
	for right < len(nums) {
		if nums[right] != 0 {
			nums[left], nums[right] = nums[right], nums[left]
			fmt.Printf("left = %d, right = %d, nums = %v\n", left, right, nums)
			left++
		}
		right++
	}
}

/*
官方題解:
https://leetcode.cn/problems/move-zeroes/solutions/489622/yi-dong-ling-by-leetcode-solution/

左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部
0, 1, 0, 3, 12
[1 0 0 3 12]
[1 3 0 0 12]
[1 3 12 0 0]
*/
