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
