package main

import "fmt"

func reverseVowels(s string) string {
	t := []byte(s)
	l, r := 0, len(s)-1
	for l < r {
		for l < r && !isVowel(s[l]) {
			l++
		}
		for l < r && !isVowel(s[r]) {
			r--
		}
		t[l], t[r] = t[r], t[l]
		l++
		r--
	}
	return string(t)
}
func isVowel(c byte) bool {
	switch c {
	case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U':
		return true
	default:
		return false
	}
}
func main() {
	s := "IceCreAm"
	fmt.Println("Ans = ", reverseVowels(s))
}

/*
switch case用法:
LanguagePractice/GoPractice/SwitchCase.go

官方解法是用strings.Contains效率可能更好
https://leetcode.cn/problems/reverse-vowels-of-a-string/

!strings.Contains("aeiouAEIOU", string(t[i]))
等價意思是：
「t[i] 這個字元 不在 母音集合 aeiouAEIOU 裡」。

參數對應
第一個 "aeiouAEIOU"：候選集合（你要去哪裡找）
第二個 string(t[i])：目前字元（你要找什麼）

其它 string 方法可以看
LanguagePractice/GoPractice/packageString.go
*/
