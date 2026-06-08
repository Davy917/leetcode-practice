package main

import (
	"fmt"
	"strings"
)

func packageString() {
	str := "helloll"
	fmt.Println(strings.Contains(str, "o"))        //檢查str有沒有包含o
	fmt.Println(strings.Index(str, "ll"))          //子串第一次出現的位置, 沒有則返回-1
	fmt.Println(strings.LastIndex(str, "ll"))      //子串最後一次出現的位置, 沒有則返回-1
	fmt.Println(strings.Replace(str, "l", "d", 2)) //替換前n個子串, n<0全部替換
	fmt.Println(strings.ReplaceAll(str, "l", "d")) //全部替換
	fmt.Println(strings.Repeat("woo", 5))
	fmt.Println(strings.Trim("aooaooa", "a")) //修剪字符串兩邊有包含'a'的字符
}

/*
教學影片:
https://www.youtube.com/watch?v=ZhepRnrS84A&list=PLBjZhzRvV2ChPTPNDx_apHdKa9Ha7LVpN&index=31

檢查字串中是否包含a,e,i,o,u
345-reverse-vowels/solution.go

檢查字符是否在'a' ~ 'z'這區間
125-is-palindrome/solution.go
*/
