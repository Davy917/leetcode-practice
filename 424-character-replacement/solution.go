/*
官方解答java
自己寫的失敗版本js
自己寫的成功版本go
*/
package main

import (
	"fmt"
)

func characterReplacement(s string, k int) int {
	reset := k
	set := make(map[byte]struct{})
	for i := 0; i < len(s); i++ {
		if _, exist := set[s[i]]; !exist {
			set[s[i]] = struct{}{}
		}
	}
	maxLen := 0
	for char, _ := range set {
		left, right := 0, 0
		k = reset
		for right < len(s) {
			if char != s[right] {
				k--
				for k < 0 {
					if s[left] != char {
						k++
					}
					left++
				}
			}
			maxLen = max(maxLen, right-left+1)
			right++
		}
	}
	return maxLen
}
func main() {
	s := "ABBB"
	// s2 := "AABACBBB"
	fmt.Println("Ans = ", characterReplacement(s, 2))
}

/*
自己寫的
用標準滑動窗口 + 哈希集合

先把AABACBBB 存成 set[A, B, C]
開始測A
left = 0, right = 0
A
AA
AAB
AABA
AABAC
AABACB炸掉
left = 0, right = 5 第一次炸掉

當前窗口 index 0 ~ 4 全部是A, 下一步要做什麼?

試著擴展
left = 1, right = 5
left = 2, right = 5
left = 3, right = 5
left = 4, right = 5
left = 5, right = 6
left = 6, right = 7
全部測完maxLen = 5, 接著測B以此類推....

代碼結構:
for 遍歷 set[A,B,C]
	初始化:
		指針left, right
		常數k
	for 用right當下標, 遍歷整個s字串
		s[right]跟當前char不一樣
			花掉一個k額度
			k沒有額度了
				left++
				嘗試讓k回到0
		maxLen比大小
		right往後移動

進階:
用標準滑動窗口 + 哈希映射, 尚未嘗試
*/
