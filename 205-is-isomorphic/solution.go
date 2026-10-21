/*
官方解答python, java
自己寫的go版本
*/
package main

import (
	"fmt"
)

func isIsomorphic(s string, t string) bool {
	stMap := make(map[byte]byte, len(s))
	tsMap := make(map[byte]byte, len(t))
	tIndex := 0
	for sIndex, _ := range s {
		sByte, tByte := s[sIndex], t[tIndex]
		fmt.Println(sByte, tByte)
		if _, exist := stMap[sByte]; exist {
			if stMap[sByte] != tByte {
				return false
			}
		}

		if _, exist := tsMap[tByte]; exist {
			if tsMap[tByte] != sByte {
				return false
			}
		}

		stMap[sByte] = tByte
		tsMap[tByte] = sByte
		tIndex++
	}
	return true
}
func main() {
	s := "badc"
	t := "baba"
	fmt.Println("Ans = ", isIsomorphic(s, t))
}

/*
自己寫的
存成map
[[e : a], [g : d]]
第三個g存進去時發現, g 已在 map中, 而且存的是 d 所以return false

寫錯了, 從頭定義倒底要找什麼?
"badc", "baba"為什麼 return false ?
sByte : tByte
b		b
a		a
d		b <---出錯, b已經是人家的值
d本身沒問題, 但是它不能配b或a

所以
要檢查sByte 是不是別人的鍵
	是, 那tByte有沒有跟m[sByte] 一樣
要檢查tByte 是不是別人的值
	是, 那sByte有沒有跟tByte的鍵一樣

用兩個map可以實現
第一個map
[sByte: tByte]
第二個map
[tByte: sByte]

兩組map都檢查完, 都通過就塞值
任一組沒通過就return false

有沒有更好的方法?
用一組Map實現, 改變檢查方式 or 改變Map存的東西

尚未實現
*/
