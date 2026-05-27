package main

import "fmt"

func minSwaps(data []int) (result int) {
	k := 0 //k 就是 windowSize
	for _, val := range data {
		if val == 1 {
			k++
		}
	}

	for i := 0; i < k; i++ {
		if data[i] == 0 {
			result++
		}
	}

	cur := result
	l, r := 0, k
	for r < len(data) {
		if data[r] == 0 {
			cur++
		}
		if data[l] == 0 {
			cur--
		}
		l++
		r++
		result = min(result, cur)
	}
	return
}
func main() {
	nums := []int{1, 0, 1, 0, 1}
	fmt.Println("Ans = ", minSwaps(nums))
}

/*
模式識別:
固定大小的滑動窗口

思路:
先統計有多少個 1, 才知道要開多大的窗口
窗口包含 N 個 0, 代表需要swap N 次
找到窗口包含最少個 0, 就是最小 swap 次數

循環不變量:
當 r 走到 r-l+1 == k, 統計 window 中有多少個 0
統計完後, l, r 繼續向右滑
不斷循環就能找到包含最少 0 的窗口

實作:
第一個for 確認窗口大小
第二個for 把第一個窗口的0值給統計出來
第三個for 專注在不斷右移 l, r
*/
