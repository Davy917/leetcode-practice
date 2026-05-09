package main

import (
	gft "gopractice/gofunctool"
)

func buckesort_advance(arr []int) []int {
	if arr == nil {
		return arr
	}
	maxNum := gft.ArrMaxInt(arr)
	minNum := gft.ArrMinInt(arr)
	trueRange := maxNum - minNum
	if trueRange == 0 {
		return arr
	}
	const bucketAmount = 10
	buckets := make([][]int, bucketAmount)
	gap := (trueRange + 1) / bucketAmount

	for _, value := range arr {
		index := value / gap
		buckets[index] = append(buckets[index], value)
	}

	arrIndex := 0
	for index := 0; index < bucketAmount; index++ {
		arrInBuckets := buckets[index]
		gft.Insertsort(arrInBuckets)
		copy(arr[arrIndex:arrIndex+len(buckets[index])], arrInBuckets)
		arrIndex += len(buckets[index])
	}
	return arr
}

/*
{55, 12, 80, 22, 14, 60, 18, 90, 16, 40, 5, 70, 30, 17, 0, 99}
trueRange = 85
gap

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
*/
