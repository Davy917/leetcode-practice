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
