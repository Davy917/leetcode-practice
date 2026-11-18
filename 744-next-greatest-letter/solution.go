/*
第一次練習python
第二次練習go
*/
package main

import "fmt"

func nextGreatestLetter(letters []byte, target byte) byte {
	l, r := -1, len(letters)-1
	for l+1 < r {
		m := (l + r) / 2
		if letters[m] > target {
			r = m
		} else {
			l = m
		}
	}
	if letters[r] > target {
		return letters[r]
	}
	return letters[0]
}
func main() {
	letters := []byte{'c', 'f', 'j'}
	fmt.Println("Ans = ", nextGreatestLetter(letters, 'a'))
}

/*
自己寫的
定義:
l = -1
r = len(letters)-1

letters[m] > target 才有機會成為答案
letters[m] <= target 不可能是答案
*/
