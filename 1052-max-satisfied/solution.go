//自己寫出來的
package main

import "fmt"
func maxSatisfied(customers []int, grumpy []int, minutes int) int {
	
	basicSatisfied := 0
	for idx, val := range customers {
		if grumpy[idx] == 0{
			basicSatisfied += val
		}
	}

	addSatisfied := 0
	for i := 0; i < minutes; i++ {
		if grumpy[i] == 1{
			addSatisfied += customers[i]
		}
	}
	maxAddSatisfied := addSatisfied

	left := 0
	for right := minutes; right < len(customers); right++ {
		if grumpy[right] == 1{
			addSatisfied += customers[right]
		}
		if grumpy[left] == 1 {
			addSatisfied -= customers[left]
		}
		maxAddSatisfied = max(maxAddSatisfied, addSatisfied)
		left++
	}
	return basicSatisfied + maxAddSatisfied
}

func main(){
	customers := []int {4, 10, 10}
	grumpy :=	 []int {1,1,0}
	minutes := 2
	fmt.Println("Ans = ", maxSatisfied(customers, grumpy, minutes))
}
/*
執行方式:
go run ./1025-max-satisfied
*/