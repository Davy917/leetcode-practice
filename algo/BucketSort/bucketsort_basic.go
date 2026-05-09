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
	}
	//debug Printing
	for index := 0; index < bucketAmount; index++ {
		fmt.Printf("bucket[%v] = %v\n", index, buckets[index])
	}
	fmt.Println("bucketLength = ", bucketLength)

	resultindex := 0
	result = make([]int, len(arr))
	for index := 0; index < bucketAmount; index++ {
		if bucketLength[index] == 0 {
			continue
		}
		arrInBuckets := buckets[index][:bucketLength[index]]
		gft.Insertsort(arrInBuckets)
		copy(result[resultindex:resultindex+bucketLength[index]], arrInBuckets)
		resultindex += bucketLength[index]
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
