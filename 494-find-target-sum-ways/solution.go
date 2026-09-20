package main

import "fmt"

func findTargetSumWays(nums []int, target int) int {
	n := len(nums)
	var dfs func(i int, v int, sum int) int
	dfs = func(i int, v int, sum int) int {
		if i == n-1 {
			if sum == target {
				return 1
			} else {
				return 0
			}
		}
		return dfs(i+1, nums[i+1], sum+nums[i+1]) + dfs(i+1, nums[i+1]*-1, sum+nums[i+1]*-1)
	}
	return dfs(0, nums[0], nums[0]) + dfs(0, nums[0]*-1, nums[0]*-1)
}
func main() {
	nums := []int{1, 1, 1}
	fmt.Println("Ans = ", findTargetSumWays(nums, 3))
}

/*
自己寫的
思路:
先在紙上畫出
	  ＋
	／ ＼
   ＋   －
  ／＼  ／＼
＋   －＋  －

	 －
	／ ＼
   ＋   －
  ／＼  ／＼
＋   －＋  －

舉例: [1, 1, 1]
只要我們把上圖的組合走完, 並統計有多少次sum == target即可, 其它測資也只是等比例縮放而已
不難看出, 它是樹狀的, 所以可知要用dfs
傳入dfs的參數有個(1. 下標, 2. 當前數值, 3.總數值)

代碼結構:
dfs(1. 下標, 2. 當前數值, 3.總數值)
	走到底了嗎?
		到了, 檢查sum == target是否成立
			成立, 回傳1
			不成立, 回傳0
		還沒走到底, 繼續往下走
*/
