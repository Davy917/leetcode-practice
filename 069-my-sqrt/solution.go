/*
第一次寫的版本python
主思路看js
變化看java
*/
package main

import "fmt"

func mySqrt(x int) int {
	var left int = 1
	var right int = x
	var ans int = 0
	for left <= right {
		middle := left + (right-left)/2
		fmt.Printf("left = %d, right = %d, middle666 = %d\n", left, right, middle)
		if middle*middle > x {
			right = middle - 1
		} else if middle*middle < x {
			left = middle + 1
			ans = middle
		} else {
			ans = middle
			return ans
		}
	}
	return ans
}
func main() {
	fmt.Println("ans = ", mySqrt(26))
}
