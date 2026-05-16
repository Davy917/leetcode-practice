package main
import "fmt"
func quicksort_advance(arr []int, start int, end int){
	if start >= end{
		return
	}
	middle := partition_advance(arr, start, end)
	quicksort_advance(arr, start, middle - 1)
	quicksort_advance(arr, middle + 1, end)
}
func partition_advance(arr []int, start int, end int) int {
	left, right := start, end
	for left < right{
		for left < right && arr[start] <= arr[right]{
			right--
		}
		for left < right && arr[start] >= arr[left]{
			left++
		}
		fmt.Printf("left = %d, right = %d\n", left, right)
		arr[left], arr[right] = arr[right], arr[left]
		fmt.Println(arr)
	}
	arr[start], arr[right] = arr[right], arr[start]
	fmt.Println(arr)
	return  right
}

/*
代碼出處
https://leetcode.cn/leetbook/read/illustration-of-algorithm/p57uhr/
*/