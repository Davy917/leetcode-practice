package main
import "fmt"

type person struct{
	name string
	age int
}
func printing(){
	fmt.Println("hello")
	fmt.Print("world! \n")
	person1 := person{"黃毛", 23}
	fmt.Println("person1 age is ", person1.age, "and person1 name is ", person1.name)
	person2 := person{name: "小李", age: 18}
	fmt.Printf("person2 age is %v, and person2 name is %q\n", person2.age, person2.name)
	fmt.Printf("age is of type %T\n", person1.age)

	fmt.Printf("your scored %0.1f points! \n", 225.63) //f前面的數字代表取到小數第幾位

	//Sprintf不會直接印出東西, 它會回傳 fmt.Sprintf()裡面的字串, 所以這邊用str接住它
	var str = fmt.Sprintf("person2 age is %v, and person2 name is %q\n", person2.age, person2.name)
	fmt.Printf("the saved string is %s\n", str)
}
/*
影片教學:
Sprintf直接從10:25開始看
https://www.youtube.com/watch?v=m1Uy0WQ2Xns&list=PL4cUxeGkcC9gC88BEo9czgyS72A3doDeM&index=4
*/