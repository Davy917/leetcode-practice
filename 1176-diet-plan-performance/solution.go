// 自己寫的
package main

import "fmt"

func dietPlanPerformance(calories []int, k int, lower int, upper int) (score int) {
	T := 0
	for i := 0; i < k; i++ {
		T += calories[i]
	}
	if T < lower {
		score--
	} else if T > upper {
		score++
	}

	l, r := 0, k
	for r < len(calories) {
		T -= calories[l]
		T += calories[r]
		if T < lower {
			score--
		} else if T > upper {
			score++
		}
		l++
		r++
	}
	return
}
func main() {
	calories := []int{6, 13, 8, 7, 10, 1, 12, 11}
	k := 6
	lower := 5
	upper := 37
	fmt.Println("Ans = ", dietPlanPerformance(calories, k, lower, upper))
}
