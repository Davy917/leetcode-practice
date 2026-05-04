package main

import (
	"fmt"
)

func radixsort_basic(arr []int) (result []int) {
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
		radixArr := make([]int, len(arr))
		for index, value := range arr {
			radix := value/dev%10 + 9
			radixArr[index] = radix
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

		result = make([]int, len(arr))
		for index, value := range(arr){
			result[counting[radixArr[index]]] = value
			counting[radixArr[index]]++
		}
		
		dev *= 10
		maxDigitLen--
		arr = result //只是讓 arr 指到 result 的底層 array。 並不是深拷貝
	}
	return
}

func absInt(value *int) int {
	if *value < 0 {
		*value = -*value
	}
	return *value
}