package main

import (
	"fmt"
	"sort"
)

func intersect(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	var shorter []int
	var longer []int
	if len(nums1) >= len(nums2) {
		shorter = nums2
		longer = nums1
	} else {
		shorter = nums1
		longer = nums2
	}
	var result []int

	for index := 0; index < len(shorter); index++ {
		target := shorter[index]
		left := 0
		right := len(longer) - 1
		for left <= right {
			middle := left + (right-left)/2
			fmt.Printf("left = %d, right = %d, middle = %d\n", left, right, middle)
			if longer[middle] > target {
				right = middle - 1
			} else if longer[middle] < target {
				left = middle + 1
			} else {
				result = append(result, target)
				longer = append(longer[:middle], longer[middle+1:]...)
				break
			}
		}
	}
	return result
}

func main() {
	var nums1 = []int{1, 2, 2, 1}
	var nums2 = []int{2, 2}
	//ans := intersect(nums1, nums2)
	ans := intersect2(nums1, nums2)
	fmt.Println("ans = ", ans)
}

/*
longer = append(longer[:middle], longer[middle+1:]...)
意思是：
	1. longer[:middle] 取的是索引 0 到 middle-1（不包含 middle）。
	2. longer[middle+1:] 取的是索引 middle+1 到最後。
	3. append(..., ......) 把這兩段接起來，等於把 middle 那個元素「跳過」。
	4. 最後再把結果賦值回 longer。

加上 ... 的意思是「把這個切片展開成多個元素」再傳進 append。
如果不加 ...，編譯器會把 longer[middle+1:] 當成「單一參數（型別 []int）」丟進去，但 append 這裡預期的是 int 元素，因此會報型別錯誤。
*/
