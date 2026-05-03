package main

import "fmt"

func TypeDefintionAndTypeAlias() {

	fmt.Println("自訂義數據")
	type mesType uint16 //創建一個新的型別
	var u1000 uint16 = 1000
	var textMes mesType = mesType(u1000) //需要顯式轉型才能互相賦值
	fmt.Printf("textMes = %v, Type of textMes = %T\n", textMes, textMes)

	fmt.Println("類型別名")
	type myUint16 = uint16 //給現有型別起個別名
	var myu16 myUint16 = u1000
	fmt.Printf("textMes = %v, Type of textMes = %T\n", myu16, myu16)
}

/*
%T 是 Go 格式化字符串中的一個動詞，用於輸出值的型別。

%v 輸出 textMes 的值
%T 輸出 textMes 的型別名稱
*/
