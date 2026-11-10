/*
第一次練習java
第二次練習python
第三次練習js
第四次練習go
*/
package main

import "fmt"

var bad int = 4

func isBadVersion(version int) bool {
	return version >= bad
}
func firstBadVersion(n int) int {
	l, r := 0, n
	for l+1 < r {
		m := (l + r) / 2
		if isBadVersion(m) {
			r = m
		} else {
			l = m
		}
	}
	return r
}
func main() {
	fmt.Println("Ans = ", firstBadVersion(5))
}

/*
自己寫的
思路:
首個錯誤版本 => 當前isBadVersion = false, 但前一個isBadVersion = True
找這樣特徵的下標, 硬找一定能解, 但偏暴力, 有聰明解嗎?

想到什麼?
二分查找的變化題, 先定義l, r

定義:
首版本從1開始, 所以isBadVersion(0) 必為true!

初始化
l = 0, l本身 & 排在它前面的, 不可能是答案
r = n, r本身, 始終可能是答案

循環:
	isBadVersion(m) 有可能是答案
		r移到可能是答案的版本, r = m
	isBadVersion(m) 不可能是答案
		l移到不可能是答案的版本, l = m

l, r相鄰的時候退出, r一定是答案
因為:
循環過程確保了, l不可能是答案, r始終可能是答案
*/
