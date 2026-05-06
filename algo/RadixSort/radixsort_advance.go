package main

import "fmt"

func radixsort_advance(arr []int) (result []int) {
	maxNum := 0
	for _, value := range arr {
		if absInt(&value) > absInt(&maxNum) {
			maxNum = value
		}
	}

	maxDigitLen := 0
	for maxNum > 0 {
		maxNum /= 10
		maxDigitLen++
	}

	dev := 1
	for maxDigitLen > 0 {
		counting := make([]int, 19)
		for _, value := range arr {
			radix := value/dev%10 + 9
			counting[radix]++
		}

		preSum := 0
		for index := 0; index < 19; index++ {
			temp := counting[index]
			counting[index] = preSum
			preSum += temp
		}

		fmt.Println(counting)
		result = make([]int, len(arr))
		for _, value := range(arr){
			radix := value / dev % 10 + 9
			result[counting[radix]] = value
			counting[radix]++
		}
		dev *= 10
		maxDigitLen--
	}
	arr = result //只是讓 arr 指到 result 的底層 array。 並不是深拷貝
	return
}