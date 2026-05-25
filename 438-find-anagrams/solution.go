/*
非官方解, 代碼參考76題
*/
package main

import "fmt"
func findAnagrams(s string, p string) []int {
	needCount := len(p)
	counting := make([]int, 26)
	result := make([]int, 0)

	for _, c := range(p){
		counting[c - 'a']++
	}

	l, r := 0, 0
	for r < len(s){
		if counting[s[r]-'a'] > 0 {
			needCount--
		}
		counting[s[r]-'a']--

		for needCount == 0 {
			if r-l+1 == len(p) { //不理解這行的作用
				result = append(result, l)
			}
			counting[s[l]-'a']++
			if counting[s[l]-'a'] > 0{
				needCount++
			}
			l++
		}
		r++
	}
	return result
}
func main(){
	s := "aababa"
	p := "aab"
	fmt.Println("Ans = ", findAnagrams(s, p))
}
/*
寫到一半發現跟76題很像, 直接照抄竟然還過了

執行代碼:
go run ./438-find-anagrams
*/