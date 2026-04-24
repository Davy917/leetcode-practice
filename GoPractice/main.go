package main

import "fmt"

func main() {
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
}
