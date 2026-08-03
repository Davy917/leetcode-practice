package main

import (
	"fmt"
	"math"
	"reflect"
)

// 定義幾何接口
type geometry interface {
	area() float64
	perim() float64
}

// 定義方形結構體
type rect struct {
	width, height float64
}

// 實現計算方形面積
func (r rect) area() float64 {
	return r.width * r.height
}

// 實現計算方形周長
func (r rect) perim() float64 {
	return 2 * (r.width + r.height)
}

// 定義圓形結構體
type circle struct {
	radius float64
}

// 實現計算圓形面積
func (c circle) area() float64 {
	return math.Pi * c.radius * c.radius
}

// 實現計算圓形周長
func (c circle) perim() float64 {
	return math.Pi * c.radius * 2
}

func measure(g geometry) {
	fmt.Println(reflect.TypeOf(g), g)
	fmt.Println(g.area())
	fmt.Println(g.perim())
}

// 空接口示範
func emptyInterface(val interface{}) {
	fmt.Println(val)
	switch val.(type) {
	case bool:
		fmt.Println("布林值")
	case int:
		fmt.Println("整數型")
	case string:
		fmt.Println("字符型")
	default:
		fmt.Println("未知型")
	}
}
func main() {
	fmt.Println("接口的使用")
	//創建一方形
	r := rect{width: 4, height: 5}
	//創建一圓形
	c := circle{radius: 10}
	//用measure函數計算周長, 面積
	measure(r)
	measure(c)

	fmt.Println("開始空接口測試")
	emptyInterface(123)
	emptyInterface("abc")
	emptyInterface(true)

	var x interface{} = 100
	X := x.(int)
	fmt.Println(X)
	fmt.Println(X + 100)

	fmt.Println("浮點")
	x = 3.14
	F := x.(float64)
	fmt.Println(F)
	fmt.Println(F + 100)

	fmt.Println("轉換結果判斷")
	T, isFloat64 := x.(float64)
	fmt.Println("isFloat64 =", isFloat64)
	fmt.Println("T = ", T)

	fmt.Println("賦值成為字符")
	x = "I love go."
	fmt.Println(x)
}

/*
switch case用法可以先看這邊
LanguagePractice/GoPractice/SwitchCase.go

先看接口的使用
https://www.youtube.com/watch?v=QD6u_aWTfaM

再看

空接口的類型轉換
https://www.youtube.com/watch?v=PRY6uDyyDNY

interface{} 實戰案例:
101-is-symmetric/solution.go

空接口類型轉換:
112-has-path-sum/solution.go

接口的多態性:
PriorityQueue的實現方式
703-kth-largest\solution.go
*/
