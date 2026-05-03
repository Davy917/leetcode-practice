package main

import "fmt"

func intersect2(nums1 []int, nums2 []int) (result []int) {
	countMap := make(map[int]int, len(nums1)) //why not :=
	fmt.Println(countMap[0])                  //沒賦值的時候並不是nil,而是0, 這點跟java不一樣
	for _, value := range nums1 {
		countMap[value]++
	}
	fmt.Println(countMap)
	for _, value := range nums2 {
		_, exists := countMap[value]
		if exists && countMap[value] != 0 {
			result = append(result, value)
			countMap[value]--
		}
	}
	return
}
