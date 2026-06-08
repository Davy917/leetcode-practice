package main

import (
	"fmt"
	"strings"
)

func isPalindrome(s string) bool {
	s = strings.ToLower(s)
	l, r := 0, len(s)-1
	for l < r {
		for l < r && !isalnum(s[l]) {
			l++
		}
		for l < r && !isalnum(s[r]) {
			r--
		}
		if s[l] != s[r] {
			return false
		}
		l++
		r--
	}
	return true
}
func isalnum(ch byte) bool {
	return (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
}
func main() {
	s := "A man, a plan, a canal: Panama"
	fmt.Println("Ans = ", isPalindrome(s))
}
