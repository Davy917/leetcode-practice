package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

func bucketsort_basic(arr []int) {
	if arr == nil {
		return
	}
	maxNum := gft.ArrMaxInt(arr)
	minNum := gft.ArrMinInt(arr)
	trueRange := maxNum - minNum
	if trueRange == 0 {
		return
	}
	const (
		bucketAmount = 10
	)
	bucketLength := make([]int, bucketAmount)
	buckets := make([][]int, bucketAmount)
	gap := trueRange / (bucketAmount - 1)
	for _, value := range arr {

	}
}
func main() {
	arr := []int{55, 80, 22, 60, 18, 90, 40, 5, 70, 30}
	bucketsort_basic(arr)
	fmt.Println("Ans = ", arr)
}

/*
什麼是const?
https://www.youtube.com/watch?v=WEoPj8HWqh8
*/
