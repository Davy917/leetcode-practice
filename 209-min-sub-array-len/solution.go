/*
自己完成
*/
package main

import (
	"fmt"
	"math"
)

func minSubArrayLen(target int, nums []int) int {
    l, r := 0, 0
	sum, minLen := 0, math.MaxInt
	for r < len(nums){
		sum += nums[r]
		for sum >= target{
			minLen = min(minLen, r-l+1)
			sum -= nums[l]
			l++
		}
		r++
	}
	if minLen == math.MaxInt{
		return 0
	} else {
		return  minLen
	}
}

func main(){
	nums := []int{2,3,1,2,4,3}
	target := 7
	fmt.Println("Ans = ", minSubArrayLen(target, nums))
}
/*
代碼執行:
go run ./209-min-sub-array-len
*/