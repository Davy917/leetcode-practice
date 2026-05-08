package main

import "fmt"

func insertsort(arr []int) {
	for index := 1; index < len(arr); index++ {
		visitor := index - 1
		tempVal := arr[index]
		for visitor > -1 && arr[visitor] > tempVal {
			arr[visitor+1] = arr[visitor]
			visitor--
		}
		arr[visitor+1] = tempVal
		fmt.Println(arr)
	}
}
func main() {
	arr := []int{5, 2, 8, 6, 1, 7, 4, 3}
	insertsort(arr)
	fmt.Println("Ans = ", arr)
}
