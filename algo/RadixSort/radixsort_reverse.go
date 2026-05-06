package main

import "fmt"
func radixsort_reverse(arr []int) (result []int) {
	maxNum := absInt(&arr[0])
	for _, value := range arr {
		if absInt(&value) > absInt(&maxNum) {
			maxNum = absInt(&value)
		}
	}
	maxDigitLen := 0
	for maxNum > 0{
		maxNum /= 10
		maxDigitLen++
	}
	dev := 1
	for digit := 0; digit < maxDigitLen; digit++ {
		counting := make([]int, 19)
		for _, value := range arr {
			radix := value / dev % 10 + 9
			counting[radix]++
		}

		counting[0] -= 1
		for index := 1; index < 19; index++ {
			counting[index] += counting[index - 1]
		}
		fmt.Println("counting after prefix = ", counting)

		result = make([]int, len(arr))

		//由前往後遍歷會遇到什麼問題? 就用註解中的for迴圈跑{211, 221}, 就能夠看到問題
		// for _, value := range arr{
		// 	radix := value / dev % 10 + 9
		// 	result[counting[radix]] = value
		// 	counting[radix]--
		// }
		// fmt.Println("result = ", result)

		for index := len(arr) - 1; index > -1; index-- {
			radix := arr[index] / dev % 10 + 9
			result[counting[radix]] = arr[index]
			counting[radix]--;
		}
		fmt.Println("result = ", result)
		arr = result
		dev *= 10
	}
	return
}