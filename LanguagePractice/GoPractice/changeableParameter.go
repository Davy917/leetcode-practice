package main

import "fmt"

/*
可變參數:

	概念: 一個函數的參數的類型確定, 但是個數不確定, 就可以使用可變參數
	語法:
		參數名 ...參數的類型
		對於func而言, 可變函數相當於slice, 再調用函數的時候可以傳入 0~n 個參數

	注意事項:
		A.如果一個函數的參數是可變參數, 同時還有其他的參數, 可變參數要放在列表的最後
		B.一個函數的參數列表中最多只能有一個可變參數, 如底下fun1
*/
func changeableParameter() {
	getSum() //我們會看到sum = 0, 因為參數列表什麼都沒傳入, len(nums) == 0
	getSum(1, 2, 3, 4, 5)

	//切片
	s1 := []int{1, 2, 3, 4, 5}
	getSum(s1...)
}

func getSum(nums ...int) {
	//先驗證一下nums ...int 實際上它是一個切片
	fmt.Printf("%T\n", nums)
	fmt.Println("nums = ", nums)
	sum := 0
	for i := 0; i < len(nums); i++ {
		sum += nums[i]
	}
	fmt.Println("sum = ", sum)
}

func fun1(s1, s2 string, nums ...float64) {

}

/*
golang可變參數:
https://www.youtube.com/watch?v=FiUYY0iW03M

python vs golang 可變參數:
https://www.youtube.com/watch?v=qFRA2ev2waw
*/
