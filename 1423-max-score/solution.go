//自己寫出來的
package main

import (
	"fmt"
	gft "gopractice/gofunctool"
)
func maxScore(cardPoints []int, k int) int {
	sum := gft.ArrSumInt(cardPoints)
	windowSize := len(cardPoints) - k
	if windowSize == 0 {
		return sum
	}

	windowVal := 0
	for i := 0; i < windowSize; i++{
		windowVal += cardPoints[i]
	}

	minWindowVal := windowVal
	left := 0
	for right := windowSize - 1; right < len(cardPoints); right++ {
		if right > windowSize - 1{
			windowVal +=cardPoints[right]
		}
		minWindowVal = min(minWindowVal, windowVal)
		windowVal -= cardPoints[left]
		left++
	}
	return sum - minWindowVal
}

func main(){
	cardPoints := []int{9,7,7,9,7,7,9}
	k := 7
	fmt.Println("Ans = ", maxScore(cardPoints, k))
}

/*
執行方式:
go run ./1423-max-score
*/