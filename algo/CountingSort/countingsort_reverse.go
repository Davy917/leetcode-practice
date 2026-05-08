package main

import "fmt"

func countingsort_reverse(arr []int) (result []int) {
	maxNum := maxInt(arr)
	minNum := minInt(arr)
	trueRange := maxNum - minNum + 1

	counting := make([]int, trueRange)
	for _, value := range arr {
		counting[value-minNum]++
	}
	counting[0] -= 1
	for index := 1; index < len(counting); index++ {
		counting[index] += counting[index-1]
	}
	result = make([]int, len(arr))
	for index := len(arr) - 1; index > -1; index-- {
		value := arr[index]
		result[counting[value-minNum]] = value
		counting[value-minNum]--
		fmt.Println(result)
	}
	return
}
