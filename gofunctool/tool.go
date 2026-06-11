/*
測試功能請到
gofunctool/Demo/main.go
*/

package gofunctool

func AbsInt(num int) int {
	if num > 0 {
		return num
	} else {
		return -num
	}
}
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

// ArrSumInt 可單獨接收一個陣列, 並回傳該陣列的所有元素加總
// ArrSumInt 也可接收陣列以及一個整數
func ArrSumInt(arr []int, end ...int) (sum int) {
	limit := len(arr)
	if len(end) > 0 {
		limit = end[0]
	}
	for i := 0; i < limit && i < len(arr); i++ {
		sum += arr[i]
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

func Swap(a *int, b *int) {
	temp := *a
	*a = *b
	*b = temp
}

/*
FAQ:

Q1:
我們從外面傳遞參數到MaxInt, 就是傳遞一堆整數進來, 而不是slice, 但是nums看起來是一個slice這樣不會發生型別不匹配嗎
A1:
不會，因為 ...int 在 Go 裡不是「真的只收 slice」，而是 可變參數 語法。
Go 會幫你把外面傳進來的一堆 int 打包成 []int 給函式內部使用

Q2:
ArrSumInt 中的 可變參數：end ...int 是什麼意思
A2:
可以傳 0 個 end, 也可以傳 1 個或多個 end, 在函式內部，end 會是一個 []int
	沒傳：len(end) == 0
	有傳：取 end[0]

可變參數練習:
LanguagePractice/GoPractice/changeableParameter.go
*/
