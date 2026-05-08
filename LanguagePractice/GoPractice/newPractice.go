package main

import "fmt"

func newPractice() {
	/*
		new以後得到的是一個指針類型, 並且該指針對應的值是0值
		a是一個指針變量, 類型是 *int的指針類型, 值是0
	*/

	var a = new(int)
	fmt.Printf("a = %v, *a = %v, &a = %v\n", a, *a, &a)
	/*
		錯誤的寫法:
		var a *int
		*a = 100
		fmt.Println(*a)
	*/

	//new方法給指針變量分配存儲空間
	var b *int
	b = new(int)
	*b = 100
	fmt.Printf("b = %v, *b = %v, &b = %v\n", b, *b, &b)

	var c = new(bool)
	fmt.Printf("c = %v, *c = %v, &c = %v", c, *c, &c)
}

/*
16:30開始
https://www.youtube.com/watch?v=OGcY_yt7r4k

new 和 make 的區別:
兩個都是用來做內存分配的
make只用於slice, map, channel 返回的還是這三個引用類型本身
new用於類型的內存分配, 並且內存對應的值為0值, 返回的是指向類型的指針
*/
