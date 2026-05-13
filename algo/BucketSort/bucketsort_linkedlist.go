package main

import (
	"fmt"
	gft "gopractice/gofunctool"
	linkedlist "gopractice/datastructure/LinkedList"
)
func buckesort_linkedlist(arr []int) (result [] int) {  
	maxNum := gft.ArrMaxInt(arr)
	minNum := gft.ArrMinInt(arr)

	gap := (maxNum - minNum) / (len(arr) - 1)
	bucketAmount := (maxNum - minNum) / gap
	buckets := make(map[int]*linkedlist.DoublyLinkedList)

	for _, val := range arr {
		index := val / gap
		if _, exist := buckets[index]; !exist{
			buckets[index] = linkedlist.NewDoublyLinkedList()
		}
		buckets[index].Append(val)
	}
	for key := 0; key < bucketAmount; key++ {
		bucket, exist := buckets[key]
		if !exist{
			continue
		}
		arrInBucket := bucket.ToSlice()
		fmt.Println(arrInBucket)
		sortedBucket := gft.Insertsort(arrInBucket)
		result = append(result, sortedBucket...)
	}
	return
}

/*
if _, exist := buckets[index];
_這是「第一個回傳值」，也就是 buckets[index] 對應到的 value。
exist 這是「第二個回傳值」，型別是 bool。它表示這個 key 是否真的存在於 map 裡。
*/