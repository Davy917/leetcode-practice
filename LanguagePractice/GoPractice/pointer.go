package main

import "fmt"

func fakeIncrease(n int) {
	n++
	fmt.Printf("increase end, n = %v\nn's address = %v\n", n, &n)
}

// fakePointer是錯誤示範, 可以做對照組
func fakePointer() {
	var src = 2022
	fakeIncrease(src)
	fmt.Printf("After increase, src = %v\nsrc's address = %v\n", src, &src)
}

func realIncrease(n *int) {
	*n++
	fmt.Printf("increase end, n = %v\nn's address = %v\nn's value = %v\n", n, &n, *n)
}

func realPointer() {
	var src = 2022
	var ptr = &src
	realIncrease(ptr)
	fmt.Printf("After increase, src = %v\nsrc's address = %v\n", src, &src)
	fmt.Printf("After increase, ptr = %v\nptr's address = %v\nptr's value = %v", ptr, &ptr, *ptr)
}

// realPointer2只是為了更好的了解 new 方法才寫的
func realPointer2() {
	var src = 2022
	realIncrease(&src)
	fmt.Printf("After increase, src = %v\nsrc's address = %v\n", src, &src)
	var ptr = new(int)
	fmt.Printf("After increase, ptr = %v\nptr's address = %v\nptr's value = %v", ptr, &ptr, *ptr)
}

/*
FAQ:
&取址符
*取值符

*是什麼??
GoPractice 裡 * 有兩種角色：
1. 放在型別前面：表示「指標型別」
	var p *int
意思是：
	p 是一個變數
	它的型別是 *int
	也就是「指向 int 的指標」
這裡的 * 不是取值符號，而是型別語法。

2. 放在變數前面：表示「解參考 / 取該地址的值」
	fmt.Println(*p)
這裡的 * 才是所謂的「取值」。


new 是什麼??
new(T) 會做兩件事：
	配置一塊可存放型別 T 的記憶體
	將內容初始化為 T 的零值
	回傳型別是 *T（指標）

var ptr = new(int)
	ptr 型別是 *int
	*ptr 初始值是 0（int 的零值）
	所以你印出會看到：ptr 是某個位址，*ptr 是 0


var ptr = new(int)
等價於
var x int      // x = 0
ptr := &x      // ptr 指向 x

一句話：new =「幫你生一個零值物件並回傳它的指標」。

影片:
指標介紹從3:40開始看, new介紹從13:00開始看
https://www.youtube.com/watch?v=e2xxQBHg1aY&list=PLBjZhzRvV2ChPTPNDx_apHdKa9Ha7LVpN&index=7

實作可以查看func Swap
gofunctool/tool.go
*/
