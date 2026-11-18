/*
第一次練習java
第二次練習python
第三次練習go, 含思路
*/
package main

import "fmt"

func findMin(nums []int) int {
	l, r := 0, len(nums)-1
	change := false
	if nums[0] < nums[r] {
		return nums[0]
	}
	for l+1 < r {
		m := (l + r) / 2
		if nums[m] == nums[l] {
			for i := m; i > l; i-- {
				if nums[i] > nums[i-1] {
					r = i - 1
					change = true
				}
			}
			if change {
				return nums[r]
			} else {
				l = m
			}
		} else if nums[m] < nums[l] {
			r = m
		} else {
			l = m
		}
	}
	return nums[r]
}
func main() {
	nums := []int{1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	fmt.Println("Ans = ", findMin(nums))
}

/*
先把 153-find-min 寫出來

自己寫的:
定義:
l = 0
r = len(nums) - 1
l在這邊仍然是過濾器, 初始nums[l]有可能是答案,
但又使用nums[0] < nums[r] 把這種可能性排除
所以nums[l]仍然不是答案

模擬:
l不斷踩上最小值的前一個, 代表l之前的元素, 包含它自己都不是答案
r則不斷踩上可能是答案者, 最後l, r相鄰時, l必非答案, r必為答案

這題跟153的差異就在於會有重複元素, 要額外處理這種情況:
		 l	   m	 r
2, 2, 2, 2, 0, 2, 2, 2

m直接把答案跳過去了

解法直接看這段 if nums[m] == nums[l]
*/
