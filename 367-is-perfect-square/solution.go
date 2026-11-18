/*
第一次練習python
第二次練習go
*/
package main

import "fmt"

func isPerfectSquare(num int) bool {
	l, r := 0, num
	for l <= r {
		m := (l + r) / 2
		if m*m > num {
			r = m - 1
		} else if m*m < num {
			l = m + 1
		} else {
			return true
		}
	}
	return false
}
func main() {
	fmt.Println("Ans = ", isPerfectSquare(16))
}
