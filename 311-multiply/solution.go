package main

import "fmt"

func multiply(mat1 [][]int, mat2 [][]int) [][]int {
	rows := len(mat1)
	cols := len(mat2[0])
	result := make([][]int, rows)
	for i := range result {
		result[i] = make([]int, cols)
	}
	fmt.Println(result)
	for m := 0; m < rows; m++ {
		for n := 0; n < cols; n++ {
			for k := 0; k < len(mat1[0]); k++ {
				result[m][n] += mat1[m][k] * mat2[k][n]
			}
		}
	}
	return result
}
func main() {
	mat1 := [][]int{{1, 0, 0}, {-1, 0, 3}}
	mat2 := [][]int{{7, 0, 0}, {0, 0, 0}, {0, 0, 1}}
	fmt.Println("Ans = ", multiply(mat1, mat2))
}

/*
自己寫的
完整推理過程看js版本

務必先把計算過程寫下來, 否則太抽象

mat1
[1, 0, 0]
[-1, 0, 3]
mat2
[7, 0, 0]
[0, 0, 0]
[0, 0, 1]

mat1 * mat2會得到:
[7, 0, 0]
[-7, 0, 3]

算法:
[0][0] = 1*7 + 0*0 + 0*0
[0][1] = 1*0 + 0*0 + 0*0
[0][2] = 1*0 + 0*0 + 0*1
[1][0] = -1*7 + 0*0 + 3*0
[1][1] = -1*0 + 0*0 + 3*0
[1][2] = -1*0 + 0*0 + 3*1
*/
