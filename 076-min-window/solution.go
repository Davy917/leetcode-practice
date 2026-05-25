package main

import "fmt"

func minWindow(s string, t string) string {
	counting := make(map[byte]int)
	for _, c := range(t){
		counting[byte(c)]++
	}

	start := 0
	needCount := len(t)
	minLen := len(s)+1
	l, r := 0, 0
	for r < len(s) {
		if (counting[s[r]] > 0){
			needCount--
		}
		counting[s[r]]--
		for needCount == 0 {
			if minLen > r-l{
				minLen = r-l
				start = l
			}
			counting[s[l]]++
			if counting[s[l]] > 0 {
				needCount++
			}
			l++
		}
		r++
	}
	if minLen == len(s)+1{
		return  ""
	}else {
		return s[start:start+minLen+1]
	}
}
func main() {
	s := "ADOBECODEBANC"
	t := "ABC"
	fmt.Println("Ans = " + minWindow(s, t))
}
/*
執行代碼:
go run ./076-min-window
*/