// 自己寫的
package main

import "fmt"

func numberOfSubstrings(s string) (result int) {
	counting := make(map[byte]int)
	l, r := 0, 0
	for r < len(s) {
		counting[s[r]]++
		for len(counting) == 3 {
			result += len(s) - r
			counting[s[l]]--
			if counting[s[l]] == 0 {
				delete(counting, s[l])
			}
			l++
		}
		r++
	}
	return
}
func main() {
	s := "abcabc"
	fmt.Println("Ans = ", numberOfSubstrings(s))
}

/*
思路:
以上面為例
r走到index 2 的時候發現湊齊了a,b,c 那麼 r=2 時能找到的所有符合條件子字串一定是len(s) - r
統計完後, 移動 l, l每次左移後發現a,b,c的狀態還是湊齊的, 就需要統計一次
l移動到a,b,c非湊齊的狀態, 移動 r, 如此循環
*/
