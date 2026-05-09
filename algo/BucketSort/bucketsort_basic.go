package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

func bucketsort_basic(arr []int) (result []int) {
	if arr == nil {
		return
	}
	maxNum := gft.ArrMaxInt(arr)
	minNum := gft.ArrMinInt(arr)
	trueRange := maxNum - minNum
	if trueRange == 0 {
		return
	}

	const bucketAmount = 10
	bucketLength := make([]int, bucketAmount)
	buckets := make([][]int, bucketAmount)
	for index := range buckets {
		buckets[index] = make([]int, len(arr))
	}
	gap := trueRange / (bucketAmount - 1)

	for _, value := range arr {
		index := (value - minNum) / gap
		buckets[index][bucketLength[index]] = value
		bucketLength[index]++
		fmt.Printf("bucket[%v] = %v\n", index, buckets[index])
		fmt.Println("bucketLength = ", bucketLength)
	}

	index := 0
	result = make([]int, len(arr))
	for i := 0; i < bucketAmount; i++ {
		if bucketLength[i] == 0 {
			continue
		}
		arrInBuckets := buckets[i][:bucketLength[i]]
		gft.Insertsort(arrInBuckets)
		copy(result[index:index+bucketLength[i]], arrInBuckets)
		index += bucketLength[i]
		fmt.Println("result = ", result)
	}
	return
}
func main() {
	arr := []int{55, 12, 80, 22, 14, 60, 18, 90, 16, 40, 5, 70, 30, 17}
	fmt.Println("Ans = ", bucketsort_basic(arr))
}

/*
什麼是const?
https://www.youtube.com/watch?v=WEoPj8HWqh8
*/
