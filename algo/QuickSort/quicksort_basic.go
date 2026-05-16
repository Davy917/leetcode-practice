package main

import "fmt"

func quicksort(arr []int) {
	fmt.Println("init arr = ", arr)
	//quicksort_basic(arr, 0, len(arr)-1)
	//quicksort_twopointers(arr, 0, len(arr)-1)
	quicksort_advance(arr, 0, len(arr) - 1)
}
func quicksort_basic(arr []int, start int, end int) {
	if start >= end {
		return
	}
	middle := partition(arr, start, end)
	quicksort_basic(arr, start, middle-1)
	quicksort_basic(arr, middle+1, end)
}
func partition(arr []int, start int, end int) int {
	pivot := arr[start]
	left := start + 1
	right := end
	for left < right {
		for left < right && arr[left] <= pivot {
			left++
		}
		fmt.Printf("left = %d, right = %d\n", left, right)
		if left != right {
			arr[left], arr[right] = arr[right], arr[left]
			fmt.Println(arr)
			right--
		}
	}
	if left == right && arr[right] > pivot {
		right--
	}
	if right != start {
		arr[start], arr[right] = arr[right], arr[start]
		fmt.Println(arr)
	}
	return right
}

func quicksort_twopointers(arr []int, start int, end int) {
	if start >= end {
		return
	}
	middle := partition_twopointers(arr, start, end)
	quicksort_twopointers(arr, start, middle-1)
	quicksort_twopointers(arr, middle+1, end)
}
func partition_twopointers(arr []int, start int, end int) int {
	pivot := arr[start]
	left := start + 1
	right := end
	for left < right {
		for left < right && arr[left] <= pivot {
			left++
		}
		for left < right && arr[right] >= pivot {
			right--
		}
		fmt.Printf("left = %d, right = %d\n", left, right)
		if left != right {
			arr[left], arr[right] = arr[right], arr[left]
			fmt.Println(arr)
			left++
			right--
		}
	}
	if left == right && arr[right] > pivot {
		right--
	}
	if right != start {
		arr[start], arr[right] = arr[right], arr[start]
		fmt.Println(arr)
	}
	return right
}
func main() {
	arr := []int{4, 2, 7, 1, 6, 3, 5}
	quicksort(arr)
}

/*
代碼出處:
https://leetcode.cn/leetbook/read/sort-algorithms/eul7hm/

執行指令
go run ./algo/QuickSort
*/
