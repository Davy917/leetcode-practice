package main

import "fmt"
func typePractice(){
	//rune 用來表示 Unicode code point；int 用來做一般整數運算與索引。
	var c rune = 'a'
	var n int = 97
	fmt.Println(c)
	fmt.Printf("%c\n", c)
	fmt.Println(n)
}