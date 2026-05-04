package main

import "fmt"

func radixsort_basic(arr []int) []int {
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
	counting := make([]int, 19)
	var radixArr []int

	for maxDigitLen > 0 {
		for _, value := range arr {
			radix := value/dev%10 + 9
			radixArr = append(radixArr, radix)
			counting[radix]++
		}
		fmt.Printf("radixArr = %v\ncounting = %v\n", radixArr, counting)

		preSum := 0
		for index := 0; index < len(counting); index++ {
			temp := counting[index]
			counting[index] = preSum
			preSum += temp
		}

		fmt.Println("counting after preSum = ", counting)

		result := make([]int, len(arr))
		for _, value := range arr {
			result[counting[value]] = value
			counting[value]++
		}
		fmt.Println("result = ", result)
		dev *= 10
		maxDigitLen--
	}
	return arr
}

func absInt(value *int) int {
	if *value < 0 {
		*value = -*value
	}
	return *value
}
