package main

import "fmt"

// 數組練習
func Array() {
	var a = [...]int{1, 456, 789}
	a[0] = 123
	for i := 0; i < len(a); i++ {
		fmt.Printf("a[%v] = %v\n", i, a[i])
	}
	for i, v := range a {
		fmt.Printf("a[%v] = %v\n", i, v)
	}
	var twoDimensionArray [3][4]int = [3][4]int{
		{1, 2, 3, 4},
		{3, 4, 5, 6},
		{5, 6, 7, 8},
	}
	for i, v := range twoDimensionArray {
		for i2, v2 := range v {
			fmt.Printf("a[%v][%v] = %v\t", i, i2, v2)
		}
		fmt.Println()
	}
}

/*
教學:
https://www.youtube.com/watch?v=NbZBOJ4Vqyk&t=59s

%v 跟其他格式符差在哪
%v：通用、懶人最常用（讓 Go 決定預設格式）
%d：十進位整數（int）
%f：浮點數（預設小數 6 位）
%s：字串
%t：布林值 true/false
*/
