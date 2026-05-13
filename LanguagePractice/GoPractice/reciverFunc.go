package main

import "fmt"

type bill struct{
	name string
	items map[string]float64
	tip float64
}

func newBill(name string) bill {
	//實例化bill
	b := bill{
		name: name,
		items: map[string]float64{"pie": 5.99, "cake": 3.99},
		tip: 0,
	}
	return b
}

//限制format()跟 b bill 這個物件綁定
func (b bill) format() string{

	var fs string ="Bill breakdown: \n"
	var total float64 = 0
	
	for key, val := range(b.items){
		fs += fmt.Sprintf("%s ...$%f \n", key + ":", val)
		total += val
	}

	fs += fmt.Sprintf("%s ...$%f\n", "tip:", b.tip)
	fs += fmt.Sprintf("%s ...$%0.2f", "total:", total + b.tip)
	return fs
}

/*
struct有疑問可以先看
LanguagePractice/GoPractice/structPractice.go

Sprintf有疑問可以先看
LanguagePractice/GoPractice/printing.go

reciverFunc 教學影片:
https://www.youtube.com/watch?v=HE6tbWlymmk
*/

//update tip[]
func (c *bill) updateTip(tip float64){
	c.tip = tip
}

//add an item to the bill
func(c *bill) addItem(name string, price float64){
	c.items[name] = price
}

/*
先看完pointer
LanguagePractice/GoPractice/pointer.go

帶指針的 reciverFunc 教學影片
https://www.youtube.com/watch?v=cgBA5k50At8
*/