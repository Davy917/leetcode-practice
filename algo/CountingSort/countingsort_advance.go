package main

import "fmt"

func countingsort_advance(arr []int) (result []int) {
	minNum := minInt(arr)
	maxNum := maxInt(arr)
	trueRange := maxNum - minNum + 1

	counting := make([]int, trueRange)
	for _, value := range arr {
		counting[value-minNum]++
	}

	preSum := 0
	for index := 0; index < len(counting); index++ {
		temp := counting[index]
		counting[index] = preSum
		preSum += temp
	}

	result = make([]int, len(arr))
	for _, value := range arr {
		result[counting[value-minNum]] = value
		counting[value-minNum]++
		fmt.Println(result)
	}
	return
}

func minInt(arr []int) (minInt int) {
	minInt = arr[0]
	for _, value := range arr {
		if value < minInt {
			minInt = value
		}
	}
	return
}
func maxInt(arr []int) (maxInt int) {
	maxInt = arr[0]
	for _, value := range arr {
		if value > maxInt {
			maxInt = value
		}
	}
	return
}
