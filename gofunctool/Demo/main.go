package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)

// test func right here
func main() {
	arr := []int{4, 5, 2, 1, 6}
	fmt.Println(gft.MaxInt(4, 5, 2, 1, 6))
	fmt.Println(gft.MaxInt(arr...))
}
