package main

import "fmt"

func mergesort_advance(arr []int, start int, end int, result []int) {
	if start >= end {
		return
	}
	middle := start + (end-start)/2
	mergesort_advance(arr, start, middle, result)
	mergesort_advance(arr, middle+1, end, result)
	/*
		擇一使用
		merge_advance(arr, start, end, result)
		merge_simplfy(arr, start, end, result)
	*/
	merge_simplfy(arr, start, end, result)
	fmt.Println("arr = ", arr)
}

func merge_advance(arr []int, start int, end int, result []int) {
	middle := start + (end-start)/2

	start1 := start
	end1 := middle

	start2 := middle + 1
	end2 := end

	index1 := start1
	index2 := start2
	resultIndex := start1

	for index1 <= end1 && index2 <= end2 {
		if arr[index1] <= arr[index2] {
			result[resultIndex] = arr[index1]
			resultIndex++
			index1++
		} else {
			result[resultIndex] = arr[index2]
			resultIndex++
			index2++
		}
	}
	if index1 <= end1 {
		copy(result[resultIndex:], arr[index1:])
	}
	if index2 <= end2 {
		copy(result[resultIndex:], arr[index2:])
	}
	fmt.Println("result = ", result)
	copy(arr[start:end+1], result[start:end+1])
}

func merge_simplfy(arr []int, start int, end int, result []int) {
	middle := start + (end-start)/2
	start2 := middle + 1
	index1 := start
	index2 := middle + 1
	for index1 <= middle && index2 <= end {
		if arr[index1] <= arr[index2] {
			result[index1+index2-start2] = arr[index1]
			index1++
		} else {
			result[index1+index2-start2] = arr[index2]
			index2++
		}
	}
	if index1 <= middle {
		copy(result[index1+index2-start2:], arr[index1:])
	}
	if index2 <= end {
		copy(result[index1+index2-start2:], arr[index2:])
	}
	fmt.Println("result = ", result)
	copy(arr[start:end+1], result[start:end+1])
}

/*
代碼出處
https://leetcode.cn/leetbook/read/sort-algorithms/euivj1

執行後觀察result以及arr之間的變化
*/
