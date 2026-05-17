package main
import "fmt"

func mergesort(arr []int){
	if arr == nil{
		return
	}
	fmt.Println("init arr = ", arr)
	// mergesort_basic(arr, 0, len(arr) - 1)
	result := make([]int, len(arr))
	mergesort_advance(arr, 0, len(arr) - 1, result)
}

func mergesort_basic(arr []int, start int, end int) []int{
	if start == end{
		return []int{arr[start]}
	}
	middle := start + (end - start) / 2
	left := mergesort_basic(arr, start, middle)
	right := mergesort_basic(arr, middle + 1, end)
	return merge(left, right)
}
//推倒merge, 先想著把[2, 6, 1][3, 5, 4] 變成 [1, 2, 3, 4, 5 ,6]
func merge(arr1 []int, arr2 []int) []int {
	result := make([]int, len(arr1) + len(arr2))
	index1, index2 := 0, 0
	for index1 < len(arr1) && index2 < len(arr2){
		if arr1[index1] <= arr2[index2] {
			result[index1 + index2] = arr1[index1]
			index1++
		} else {
			result[index1 + index2] = arr2[index2]
			index2++
		}
	}
	if index1 < len(arr1){
		copy(result[index1+index2:], arr1[index1:])
	}
	if index2 < len(arr2){
		copy(result[index1+index2:], arr2[index2:])
	}
	fmt.Println("result = ", result)
	return result
}
func main(){	
	/*
	單獨測試merge
	merge([]int{2, 6, 1}, []int{3, 5, 4})
	*/
	//arr := []int{2, 6, 1, 3, 5, 4}
	arr2 := []int{7, 3, 6, 2, 0, 1, 5, 4}
	mergesort(arr2)
}

/*
代碼出處
https://leetcode.cn/leetbook/read/sort-algorithms/euivj1/

執行指令
go run ./algo/MergeSort
*/