package main

import "fmt"

func SwitchCase() {
	//直接替代if...else的用法
	var age uint8
	fmt.Println("請輸入你的年齡")
	fmt.Scanln(&age)
	switch {
	case age < 13:
		fmt.Println("小朋友要學編程喔")
	case age < 25:
		fmt.Println("大朋友要學編程喔")
	default:
		fmt.Println("老朋友要學編程喔")
	}
	//值匹配
	var weekday uint8
	fmt.Println("請輸入星期")
	fmt.Scanln(&weekday)
	switch weekday {
	case 1:
		fmt.Println("醬油炒飯")
	case 2:
		fmt.Println("醬油炒麵")
	default:
		fmt.Println("輸入有誤")
	}
}

/*
case結尾會自動break, 如果需要匹配下一項可以加入fallthrough, default可以省略
教學:
https://www.youtube.com/watch?v=HTw49C9FaTI&list=PLBjZhzRvV2ChPTPNDx_apHdKa9Ha7LVpN&index=12
*/
