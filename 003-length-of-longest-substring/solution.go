package main

import "fmt"

func lengthOfLongestSubstring(s string) int {
	l, r := 0, 0
	maxLen := 0
	counting := make([]int, 256)
	
	for r < len(s) {
		counting[s[r]]++
		for counting[s[r]] > 1 {
			counting[s[l]] -= 1
			l++
		}
		maxLen = max(maxLen, r-l+1)
		r++
	}
	return maxLen
}
func main() {
	// s := "ddvdf"
	s2 := "pzwwkaew"
	// fmt.Println("Ans = ", lengthOfLongestSubstring(s2))
	fmt.Println("Ans = ", lengthOfLongestSubstring2(s2))
}

/*
代碼執行:
go run ./003-length-of-longest-substring

我們可以確定r指針會一直往前走, 遇到重複字元時, 要開始移動l
但是l的移動軌跡要怎麼設計才是這題的困難點

最直覺的做法是 l = r, l--  但這些都不是答案, 而是要用for一步一步把l 移到正確位置上, 如上面代碼所示

自己寫的時候也是卡在這裡, 寫不出來
*/
