// 自己寫的
package main

import "fmt"

func numKLenSubstrNoRepeats(s string, k int) (result int) {
	l, r := 0, 0
	counting := make([]int, 26)
	for r < len(s) {
		counting[s[r]-'a']++
		for counting[s[r]-'a'] == 2 {
			counting[s[l]-'a']--
			l++
		}
		if r-l+1 == k {
			result++
			counting[s[l]-'a']--
			l++
		}
		r++
	}
	return result
}

func main() {
	S := "havefunonleetcode"
	K := 5
	fmt.Println("Ans = ", numKLenSubstrNoRepeats(S, K))
}

/*
思路:
固定長度的滑動窗口
r 與 l 的距離為K, 但沒找到重複字符, l, r往右個移動一格
r 找到重複字元字符串, 移動 l 到第二個重複字符上面

補充:
我們可以透過維護一組陣列counting來知道有沒有重複自源自串
*/
