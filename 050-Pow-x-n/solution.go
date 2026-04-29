package main

import (
	"fmt"
)

func myPow(x float64, n int) float64 {
	N := n
	if N < 0 {
		x = 1 / x
		N = -n
	}
	var result = 1.0
	fmt.Printf("init\nN = %d, x = %f, result = %f\n", N, x, result)
	for N > 0 {
		if N&1 == 1 {
			fmt.Println("N is odd")
			result *= x
		}
		x *= x
		N /= 2
		fmt.Printf("N = %d, x = %f, result = %f\n", N, x, result)
	}
	return result
}

func main() {
	x := 2.0
	n := 10
	ans := myPow(x, n)
	fmt.Println("ans = ", ans)
}

/*
快速冪影片教學
https://youtu.be/GbDtCFhq20A?si=tWEqyB_81Zd6tiRe
*/
