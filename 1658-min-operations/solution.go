package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

func minOperations(nums []int, x int) int {
	sum := gft.ArrSumInt(nums)
	if x > sum {
		return -1
	}
	target := sum - x

	l := 0
	curVal, maxVal := 0, -1
	for r := 0; r < len(nums); r++ {
		curVal += nums[r]
		fmt.Println("c = ", curVal)
		for curVal > target {
			curVal -= nums[l]
			l++
		}
		if curVal == target {
			maxVal = max(maxVal, r-l+1)
		}
	}
	if maxVal == -1 {
		return -1
	} else {
		return len(nums) - maxVal
	}
}
func main() {
	nums := []int{1, 1, 1, 1}
	x := 4
	fmt.Println("Ans = ", minOperations(nums, x))
}

/*
執行代碼
go run ./1658-min-operations

寫第二次了, 卡在上面這個test case寫不出來
一開始寫了第16行初始化maxVal = 0, 第28行if maxVal == 0, 導致上面這test case一直跑不過
修正成現在這樣才對
*/
