/*
使用set
*/
package main

import "fmt"

func intersection2(nums1 []int, nums2 []int) (result []int) {
	set := make(map[int]struct{})
	for _, value := range nums1 {
		set[value] = struct{}{}
	}
	fmt.Println("result = ", result)
	fmt.Println("set = ", set)
	for _, value := range nums2 {
		if _, exists := set[value]; exists {
			result = append(result, value)
			delete(set, value)
		}
	}
	return
}

/*
Python-like sets in GoPractice with golang-set
https://www.youtube.com/watch?v=QNHOyOuLhFo
*/
