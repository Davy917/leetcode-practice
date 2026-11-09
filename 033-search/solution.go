/*
思路看go
優化看python
*/
package main

import "fmt"

func search(nums []int, target int) int {
	l, r := 0, len(nums)-1
	for l <= r {
		m := (l + r) / 2
		if target == nums[m] {
			return m
		}
		if nums[m] >= nums[l] {
			if target > nums[m] {
				//往右找
				l = m + 1
			} else {
				if target < nums[l] {
					//往右找
					l = m + 1
				} else {
					//往左找
					r = m - 1
				}
			}
		} else {
			if target < nums[m] {
				//往左找
				r = m - 1
			} else {
				if target > nums[r] {
					//往左找
					r = m - 1
				} else {
					//往右找
					l = m + 1
				}
			}
		}
	}
	return -1
}
func main() {
	nums := []int{4, 5, 6, 7, 0, 1, 2}
	fmt.Println("Ans = ", search(nums, 0))
}

/*
自己寫的
思路:
4,5,6,7,0,1,2, target = 0
l = 0, r = 6
nums[l] = 4, nums[r] = 2

現有資訊:
斷點發生在數字2, 4
nums[l] 必定 >= nums[r]
時間複雜度必須O(log n), 大概率還是能用二分查找

逐步推敲:
m = 3, nums[m] = 7
nums[m] > target && nums[l] > target, l ~ m不可能是答案
l = m + 1 = 4, nums[l] = 0
m = 5, nums[m] = 1
nums[m] > target && nums[r] > target, m ~ r不可能是答案
r = m - 1 = 4, nums[r] = 0
m = 0找到答案

上述推敲:
每次nums[m] 要跟target比較
	每次target 都要跟 nums[r] 或 nums[l]比較
		才能知道要往哪邊走


再看這兩組:
4,5,6,7,8,1,2,3, target = 8, nums[m] = 7
target > nums[m] && target > nums[l], 往右找

看似合理但套用到這裡就失效了
4,5,1,2,3, target = 5, nums[m] = 1
target > nums[m] && target > nums[l], 往右找(錯誤)

關鍵:
m 跟 l連在一起? 還是跟r連在一起? 如何得知?
nums[m] >= nums[l], 代表m跟l是一組的
nums[m] < nums[l], 代表m跟r是一組的

代碼結構:
target == nums[m] return m
nums[m] >= nums[l], m跟l是一組
	target > nums[m] 往右找
	target < nums[m]
		target < nums[l] 往右找
		target >= nums[l] 往左找
nums[m] < nums[l], m跟r是一組
	target < nums[m] 往左找
	target > nums[m]
		target > nums[r] 往左找
		target <= nums[r] 往右找

寫的時候一定要在紙上畫狀態圖, 把各種可能都考慮進去

再優化看python版本
033-search\solution.py
*/
