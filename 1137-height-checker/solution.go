package main

import "fmt"

func heightChecker(heights []int) (result int) {
	const bucketAmount = 101
	buckets := make([]int, bucketAmount)
	for _, value := range heights {
		buckets[value]++
	}
	fmt.Println(buckets)

	heightsIndex := 0
	for bucketsIndex := 1; bucketsIndex < len(buckets); bucketsIndex++ {
		for buckets[bucketsIndex] > 0 {
			if heights[heightsIndex] != bucketsIndex {
				result++
			}
			fmt.Printf("heightsIndex = %d, bucketsIndex = %d, buckets[bucketsIndex] = %d, result = %d\n", heightsIndex, bucketsIndex, buckets[bucketsIndex], result)
			heightsIndex++
			buckets[bucketsIndex]--
		}
	}
	return
}

func main() {
	students := []int{1, 1, 4, 2, 1, 3}
	fmt.Println("result = ", heightChecker(students))
}

/*
sorted = {1, 1, 1, 2, 3, 4}
*/
