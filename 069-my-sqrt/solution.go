package main

/*
代碼參考模板:
algo/BinarySearch/BinarySearch_basic.java
*/
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
	var x = 8
	ans := mySqrt(x)
	fmt.Println("ans = ", ans)
}
