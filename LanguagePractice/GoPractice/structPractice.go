package main

import "fmt"

/*
type 結構名稱 struct{
	欄位名稱 資料型態
	欄位名稱 資料型態
	...
}
*/
type Point struct{
	x int
	y int
}

type Person struct{
	name string
	age int
}

/*
實體化
結構名稱{欄位資料, 欄位資料...}
結構名稱{欄位名稱:資料, 欄位名稱:資料,...}
*/
func structPractice(){
	//兩種寫法都可以
	var p1 Point = Point{3, 4}
	var p2 Point = Point{y:2, x:1}
	fmt.Println(p1.x, p1.y)
	fmt.Println(p2.x, p2.y)
	
	person1 := Person{"王九", 41}
	person2 := Person{name:"小林", age:22}
	fmt.Println(person1.name, person1.age)
	fmt.Println(person2.name, person2.age)
	
	person2.name = "小黃"
	fmt.Println(person2.name, person2.age)
}

/*
教學影片
https://www.youtube.com/watch?v=KFNgPTgXpBU
*/