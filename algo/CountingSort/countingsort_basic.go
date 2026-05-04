package main

import "fmt"
func countingsort_stage1(arr []int)(result []int){  
	counting := [9]int{}
	for _, value := range arr {
		counting[value - 1]++
	}

	index := 0 //arr下標的迭代要另外寫一個index計算
	for i := 0; i < 9; i++ {
		for counting[i] != 0{
			result = append(result, i + 1)
			counting[i]--
			index++
		}
	}
	return
}

func countingsort_stage2(arr []int)(result []int){
	counting := [9]int{}
	var record map[int][]int //Go 沒有內建的 Queue 型別，慣用 []int (slice) 來模擬
	record = make(map[int][]int, 9)
	
	for _, value := range arr {
		counting[value - 1]++
		_, exist := record[value - 1]
		if !exist{
			record[value - 1] = []int{}
		}
		record[value - 1]  = append(record[value-1], value)
	}
	fmt.Println("record = ", record)

	for i := 0; i < 9; i++ {
		currentQueue := record[i]
		fmt.Printf("currentQueue = %v\n", currentQueue) //打印出來會清晰很多
		for _, value := range currentQueue{
			result = append(result, value)
		}
	}
	return
}

func countingsort_basic(arr []int)(Result []int){
	counting := make([]int, 9)
	for _, value := range arr {
		counting[value - 1]++
	}
	preSum := 0
	for index := 0; index < len(counting); index++ {
		temp := counting[index]
		counting[index] = preSum
		preSum +=temp
	}
	fmt.Println("counting after prefix = ", counting)

	Result = make([]int, 9)
	for _, value := range arr {
		Result[counting[value - 1]] = value
		counting[value - 1]++
	}
	return
}
func main(){
	arr := []int{5, 7, 3, 1, 6, 8, 9, 4, 7}
	// fmt.Println("Result = ", countingsort_stage1(arr))
	// fmt.Println("Result = ", countingsort_stage2(arr))
	fmt.Println("Result = ", countingsort_basic(arr))
}
/*
stage2細節:
_, exist := records[value-1]: 這是 Go 中檢查 map 中 key 是否存在的慣用模式。
	records[value-1] 會嘗試從 map 中獲取 value-1 對應的值。
	exist 是一個布林值，如果 key 存在，exist 為 true；如果 key 不存在，exist 為 false。
	_ 用於忽略獲取到的值（因為我們只關心是否存在）。
*/