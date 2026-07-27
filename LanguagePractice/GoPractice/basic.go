package main

import "fmt"

func basic() {
	fmt.Println("hello world")
	//算術運算:
	var g int
	g = 3*3 + 10
	fmt.Println(g)
	//指定運算:
	g = 5
	g++
	fmt.Println(g)
	//比較運算:
	var result bool = 4 > 3
	fmt.Println(result)
	//邏輯運算:
	fmt.Println("!true = ", !true)
	fmt.Println("true && false = ", true && false)
	fmt.Println("true || false", true || false)
	/*Go 沒有內建的次方運算子（不像 Python 的 **）。常見做法有兩種：
		1. 整數次方（特別是 2 的冪）→ 位元移位
		1 << k   // 等於 2^k，僅適用於整數

		2. 一般次方 → math.Pow
		import "math"
		math.Pow(2, 3)  // 2^3 = 8，回傳 float64
		若需要整數結果，需手動轉型：int(math.Pow(2, 3))

		簡單原則：
		2^k → 優先用 1 << k
		x^y（任意底數）→ 用 math.Pow(x, y)

		實戰用法:
		655-print-tree/solution.go
		*/
	//for loop pracice
	var x = 0
	for x < 3 {
		fmt.Println(x)
		x++
	}
	var count int = 5
	for i := 0; i < count; i++ {
		fmt.Println("i = ", i)
	}

	//等差級數加法
	var sum = 0
	for y := 0; y <= 50; y++ {
		sum += y
	}
	fmt.Println(sum)
}
