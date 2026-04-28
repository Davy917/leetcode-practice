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

	/*
		//if-else練習
		if true {
			fmt.Println("Go")
		} else {
			fmt.Println("Not Go")
		}
		var money int
		fmt.Println("How much money do u want?")
		fmt.Scanln(&money)
		if money < 100{
			fmt.Println("Too few")
		}else if money <= 100000{
			fmt.Println("OK")
		}else{
			fmt.Println("too much")
		}
		fmt.Println("Done.")
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