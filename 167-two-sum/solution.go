/*
自己想的時間複雜度 nlogn, java, python版本估計是問AI寫出來的
*/
package main

import "fmt"

func twoSum(numbers []int, target int) (result []int) {

	for index, value := range numbers {
		left := index + 1 //自己寫的時候卡在這, 問了AI才知道要 +1
		right := len(numbers) - 1
		for left <= right {
			middle := left + (right-left)/2
			fmt.Printf("left = %d, right = %d, middle = %d\n", left, right, middle)
			if numbers[middle] < target-value {
				left = middle + 1
			} else if numbers[middle] > target-value {
				right = middle - 1
			} else {
				result = append(result, index+1, middle+1)
				return
			}
		}
	}
	return
}
func main() {
	var numbers = []int{1, 2, 3, 4, 4, 9, 56, 90}
	var target int = 8
	var ans []int = twoSum(numbers, target)
	fmt.Println("Ans = ", ans)
}

/*
為什麼 left := index + 1 就對了？
在第 index 輪，你已經固定第一個數是 numbers[index]。
那第二個數就只能在它右邊找，才能保證是另一個元素：
	合法搜尋範圍：[index+1, n-1]
	不合法搜尋範圍：[index, n-1]（包含自己）

所以改成 left := index + 1 等於直接把「自己配自己」這條路封掉。
*/
