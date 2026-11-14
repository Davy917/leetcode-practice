/*
第一次練習python
第二次練習go
*/
package main

import (
	"fmt"
	"math"
)

type ArrayReader struct {
	arr []int
}

func (this *ArrayReader) get(index int) int {
	if 0 <= index && index < len(this.arr) {
		return this.arr[index]
	}
	return int(math.Pow(2, 31) - 1)
}

var max int = int(math.Pow(2, 31)) - 1

func search(reader ArrayReader, target int) int {
	l, r := 0, 9999
	for l <= r {
		m := l + (r-l)/2
		if reader.get(m) == max || reader.get(m) > target {
			r = m - 1
		} else if reader.get(m) < target {
			l = m + 1
		} else {
			return m
		}
	}
	return -1
}
func main() {
	reader := ArrayReader{arr: []int{-1, 0, 3, 5, 9, 12}}
	fmt.Println("Ans = ", search(reader, 4))
}

//自己寫的
