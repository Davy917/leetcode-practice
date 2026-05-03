package main

import "fmt"

// map練習
func mapPractice() {
	var m1 map[string]string
	fmt.Print("m1 == nil ", m1 == nil)
	m1 = make(map[string]string, 1)
	m1["早上"] = "敲代碼"
	m1["中午"] = "送外賣"
	m1["晚上"] = "開滴滴"
	fmt.Println("m1 = ", m1)
	m2 := map[string]string{
		"下午": "修bug",
		"凌晨": "打電動",
	}
	fmt.Println("m2 = ", m2)
	v, ok := m2["中午"]
	if ok {
		fmt.Println("v = ", v)
	} else {
		fmt.Println("key不存在")
	}
	delete(m1, "晚上")
	fmt.Println("m1 = ", m1)
	m1 = nil
	m2 = make(map[string]string)
	for key, value := range m1 {
		fmt.Printf("m1[%v] = %v\n", key, value)
	}
}
