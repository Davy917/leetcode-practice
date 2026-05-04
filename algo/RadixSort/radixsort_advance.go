package main

import "fmt"

func radixsort_advance(arr []int) []int {
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
	for maxDigitLen > 0 {

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
		result := make([]int, len(arr))

		fmt.Println(result)
		dev *= 10
		maxDigitLen--
	}
	return arr
}
func main() {
	var arr = []int{3, 4, -4, 3, -6, 1, 5, -1, -9, 9, 1}
	fmt.Println(radixsort_basic(arr))
	//var arr2 = []int{520, -211, 438, -888, 7, 111, 985, 666, -996, 233, 168}
	//fmt.Println(radixsort_advance(arr2))
}
