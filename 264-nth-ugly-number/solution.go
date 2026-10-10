/*
go 第一版, 會超時
python 第二版, 優化過後ok
*/
package main

import (
	"fmt"
)

func nthUglyNumber(n int) int {
	uglyNums := []int{1}
	curNum := 1
	for len(uglyNums) < n {
		curNum++
		if curNum%2 == 0 && isUglyNum(uglyNums, curNum/2) {
			uglyNums = append(uglyNums, curNum)
			continue
		}
		if curNum%3 == 0 && isUglyNum(uglyNums, curNum/3) {
			uglyNums = append(uglyNums, curNum)
			continue
		}
		if curNum%5 == 0 && isUglyNum(uglyNums, curNum/5) {
			uglyNums = append(uglyNums, curNum)
			continue
		}
	}
	return uglyNums[len(uglyNums)-1]
}
func isUglyNum(nums []int, target int) bool {
	left, right := 0, len(nums)-1
	for left <= right {
		middle := (left + right) / 2
		// fmt.Println(left, right, middle) // debug
		if nums[middle] > target {
			right = middle - 1
		} else if nums[middle] < target {
			left = middle + 1
		} else {
			return true
		}
	}
	return false
}
func main() {
	fmt.Println("Ans = ", nthUglyNumber(15))
	// print("Ans = ", isUglyNum([]int{1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24}, 5))
}

/*
丑數定義:
264-nth-ugly-number\ugly number definition.md

自己想的

題目給一個n, 要回答第n個丑數
想像成有一個迴圈, 我們要做同樣的事情n次
迴圈每執行一圈就能找一個丑數

1是一個例外, 從2開始

可以被2, 3, 5整除, 被7整除就不行
如果那個數可以被7整除, 就一定不是丑數, 比如14, 35
uglyNums be like:
[1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24]

轉換思路:
從1開始一直+1, 每找到一個丑數就加到uglyNums中, len(uglyNums) == n, 就代表找到答案

問題變成--->給一個數, 要怎用最快的方式確定它是否為丑數?

找共通點
12 = 3 * 2 * 2
15 = 5 * 3
16 = 2 * 2 * 2 * 2
18 = 3 * 3 * 2
20 = 5 * 2 * 2
24 = 3 * 2 * 2 * 2

舉例:
24 / 2 = 12
12是個丑數, 所以24一定也是丑數

反例:
22 / 2 = 11
11不是丑數, 所以22一定不是丑數
22 / 3除不盡
22 / 5除不盡
22不是丑數

代碼架構:
curNum = 24
for len(uglyNums) < n
	curNum / 2 有辦法整除嗎? 有
		curNum / 2 結果是丑數嗎? 是 <----二分查找
			uglyNums加入curNum
			Continue
	curNum / 3 有辦法整除嗎? 不重要
	curNum / 5 有辦法整除嗎? 不重要

現在問題變成 x = curNum / 2, x 是否出現在 uglyNums 當中
直覺寫法--->每次都回頭檢查uglyNums, 時間複雜度: O(n平方)
二分查找--->剛好可以複習二分查找

用二分查找還是超時, 優化方向直接生成丑數, 而不是一直+1再判斷

後續看python優化版
*/
