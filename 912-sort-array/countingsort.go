/*
參考代碼:
algo/CountingSort/countingsort_advance.go
*/
package main

import "fmt"

func countingsort(nums []int) []int {
	minNum := minInt(nums)
	maxNum := maxInt(nums)
	trueRange := maxNum - minNum + 1

	counting := make([]int, trueRange)
	for _, value := range nums {
		counting[value-minNum]++
	}

	preSum := 0
	for index := 0; index < len(counting); index++ {
		temp := counting[index]
		counting[index] = preSum
		preSum += temp
	}

	result := make([]int, len(nums))
	for _, value := range nums {
		result[counting[value-minNum]] = value
		counting[value-minNum]++
	}
	return result
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

func main() {
	arr := []int{5, 7, 3, 1, 6, 8, 9, 4, 7}
	fmt.Println("Result = ", countingsort(arr))
}
