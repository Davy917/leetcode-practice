package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

// test func right here
func main() {
	arr := []int{4, 2, 1, 1, 1}
	//fmt.Println(gft.MaxInt(4, 5, 2, 1, 6))
	//fmt.Println(gft.MaxInt(arr...))

	//a := 5
	//b := 3
	//gft.Swap(&a, &b)
	//fmt.Println(a, b)

	fmt.Println(gft.ArrSumInt(arr[1:4]))
}
