/*
官方解答
*/
package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

func maximumGap(nums []int) (maxGap int) {
	if len(nums) < 2 {
		return
	}

	minNum := gft.ArrMinInt(nums)
	maxNum := gft.ArrMaxInt(nums)
	if maxNum-minNum == 0 {
		return
	}
	gap := gft.MaxInt(1, (maxNum-minNum)/(len(nums)-1))
	bucketAmount := (maxNum-minNum)/gap + 1
	buckets := make([][2]int, bucketAmount)
	fmt.Printf("gap = %d, bucketAmount = %d\nbuckets = %v\n", gap, bucketAmount, buckets)

	for _, num := range nums {
		index := (num - minNum) / gap
		if buckets[index][0] == 0 {
			buckets[index][0] = num
			buckets[index][1] = num
		} else {
			buckets[index][0] = gft.MinInt(num, buckets[index][0])
			buckets[index][1] = gft.MaxInt(num, buckets[index][1])
		}
	}
	fmt.Println("After sync buckets = ", buckets)

	maxGap = buckets[1][0] - buckets[0][1]
	prev := buckets[0][1]
	for index := 1; index < bucketAmount; index++ {
		if buckets[index][0] == 0 {
			continue
		}
		curGap := buckets[index][0] - prev
		maxGap = gft.MaxInt(maxGap, curGap)
		fmt.Println("maxGap = ", maxGap)
		prev = buckets[index][1]
	}
	return
}

func main() {
	//nums := []int{55, 12, 80, 22, 14, 60, 18, 90, 16, 40, 5, 70, 30, 17, 0, 99}
	nums2 := []int{1, 1, 1, 1, 1, 5, 5, 5, 5, 5}
	fmt.Println("Ans = ", maximumGap(nums2))
}

/*
官方題解:
https://leetcode.cn/leetbook/read/sort-algorithms/ph60kd/
*/
