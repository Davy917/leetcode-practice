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
	gap := (trueRange + 1) / bucketAmount

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

	resultIndex := 0
	result = make([]int, len(arr))
	for index := 0; index < bucketAmount; index++ {
		if bucketLength[index] == 0 {
			continue
		}
		arrInBucket := buckets[index][:bucketLength[index]] //一開始看到這句很懵
		gft.Insertsort(arrInBucket)
		copy(result[resultIndex:resultIndex+bucketLength[index]], arrInBucket)
		resultIndex += bucketLength[index]
		fmt.Println("result = ", result)
	}
	return
}
func main() {
	arr := []int{55, 12, 80, 22, 14, 60, 18, 90, 16, 40, 5, 70, 30, 17, 0, 99}
	//fmt.Println("Ans = ", bucketsort_basic(arr))
	//fmt.Println("Ans = ", buckesort_advance(arr))
	fmt.Println("Ans = ", buckesort_linkedlist(arr))
}

/*
什麼是const?
https://www.youtube.com/watch?v=WEoPj8HWqh8

sop:
algo/BucketSort/BucketSort_SOP.md
algo/BucketSort/FAQ-what is gap.md

gap aka bucketWidth
bucket[0] 0 ~ 9
bucket[1] 10 ~ 19
bucket[2] 20 ~ 29
bucket[3] 30 ~ 39
bucket[4] 40 ~ 49
bucket[5] 50 ~ 59
bucket[6] 60 ~ 69
bucket[7] 70 ~ 79
bucket[8] 80 ~ 89
bucket[9] 90 ~ 99

執行方式
go run ./algo/BucketSort
*/
