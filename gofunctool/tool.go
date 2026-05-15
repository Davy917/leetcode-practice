/*
測試功能請到
gofunctool/Demo/main.go
*/

package gofunctool

func ArrMaxInt(arr []int) (maxNum int) {
	maxNum = arr[0]
	for _, value := range arr {
		maxNum = max(maxNum, value)
	}
	return
}
func ArrMinInt(arr []int) (minNum int) {
	minNum = arr[0]
	for _, value := range arr {
		minNum = min(minNum, value)
	}
	return
}

func MaxInt(nums ...int) (maxNum int) {
	if len(nums) == 0 {
		panic("MaxInt needs at least one argument")
	}
	//在函式裡，nums 的型別其實就是 []int
	maxNum = nums[0]
	for _, num := range nums {
		if num > maxNum {
			maxNum = num
		}
	}
	return
}

func MinInt(nums ...int) (minNum int) {
	if len(nums) == 0 {
		panic("MinInt needs at least one argument")
	}
	minNum = nums[0]
	for _, num := range nums {
		if num < minNum {
			minNum = num
		}
	}
	return
}

func Insertsort(arr []int) (result []int) {
	for index := 1; index < len(arr); index++ {
		visitor := index - 1
		tempVal := arr[index]
		for visitor > -1 && arr[visitor] > tempVal {
			arr[visitor+1] = arr[visitor]
			visitor--
		}
		arr[visitor+1] = tempVal
	}
	result = arr
	return
}

/*
FAQ:

Q1:
我們從外面傳遞參數到MaxInt, 就是傳遞一堆整數進來, 而不是slice, 但是nums看起來是一個slice這樣不會發生型別不匹配嗎
A1:
不會，因為 ...int 在 Go 裡不是「真的只收 slice」，而是 可變參數 語法。
Go 會幫你把外面傳進來的一堆 int 打包成 []int 給函式內部使用
*/
