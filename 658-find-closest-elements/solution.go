package main

import (
	"fmt"
	gft "gopractice/gofunctool"
	"sort"
)

func findClosestElements(arr []int, k int, x int) (result []int) {
	n := len(arr)
	l, m, r := 0, 0, n
	for l < r {
		m = l + (r-l)/2
		fmt.Printf("l = %d, r = %d, m = %d\n", l, r, m)
		if arr[m] < x {
			l = m + 1
		} else {
			r = m
		}
	}
	t := 0
	if l == 0 {
		t = 0
	} else if l == n {
		t = n - 1
	} else {
		if gft.AbsInt(arr[l]-x) < gft.AbsInt(arr[l-1]-x) {
			fmt.Println("in")
			t = l
		} else {
			t = l - 1
		}
	}
	result = append(result, arr[t])
	fmt.Println(result)
	L, R := t-1, t+1
	for i := 1; i < k; i++ {
		if L == -1 {
			result = append(result, arr[R])
			R++
		} else if R == n {
			result = append(result, arr[L])
			L--
		} else if L > -1 && R < n {
			if gft.AbsInt(arr[L]-x) < gft.AbsInt(arr[R]-x) {
				result = append(result, arr[L])
				L--
			} else if gft.AbsInt(arr[L]-x) > gft.AbsInt(arr[R]-x) {
				result = append(result, arr[R])
				R++
			} else {
				result = append(result, arr[L])
				L--
			}
		}
	}
	sort.Ints(result)
	return result
}
func main() {
	arr := []int{2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 8, 10}
	fmt.Println("Ans = ", findClosestElements(arr, 2, 5))
}

/*
第 10~34 行都在前置作業
我們在尋找arr當中,值最接近 x 並且最靠左的那個數, 的下標

FAQ:
Q: 我們有了第 12 行那個迴圈, 為什麼還要 22~32 那幾行判斷式 ??
A: 第 12 行那個二分迴圈只做「定位」：找到 l（第一個 >= x 的位置）

Q: 也就是說 l 在進入 22~32 判斷之前就已經可以確定是在正確的位置, 或是在正確位置的右邊, 我們是如何確保這件事情的 ??
A: 如果 x 真正在陣列中：
	l 會停在 第一個 x
	若 x 不存在，l 會停在 第一個比 x 大的位置
	所以 l 不會跑到比「該插入/該匹配的位置」更左邊。
*/
