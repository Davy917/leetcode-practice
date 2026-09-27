package main

import (
	"fmt"
)

var directions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
var rows int
var cols int

func updateMatrix(mat [][]int) [][]int {
	rows = len(mat)
	cols = len(mat[0])
	deque := [][]int{}
	result := make([][]int, rows)
	inGrid := func(x int, y int) bool {
		return x >= 0 && y >= 0 && x < rows && y < cols
	}
	bfs := func() {
		for len(deque) > 0 {
			layerSize := len(deque)
			for j := 0; j < layerSize; j++ {
				cur := deque[0]
				deque = deque[1:]
				curX := cur[0]
				curY := cur[1]
				for _, direction := range directions {
					newX := curX + direction[0]
					newY := curY + direction[1]
					if inGrid(newX, newY) && mat[newX][newY] == 1 {
						deque = append(deque, []int{newX, newY})
						mat[newX][newY] = 0
						result[newX][newY] = result[curX][curY] + 1
					}
				}
			}
		}
	}
	rows := len(mat)
	cols := len(mat[0])
	for i := range rows {
		result[i] = make([]int, cols)
	}
	for i := 0; i < rows; i++ {
		for j := 0; j < cols; j++ {
			if mat[i][j] == 0 {
				deque = append(deque, []int{i, j})
			}
		}
	}
	bfs()
	return result
}
func main() {
	mat := [][]int{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}
	fmt.Println("Ans = ", updateMatrix(mat))
}

/*
[[0, 0, 0], [0, 1, 0], [1, 2, 1]]
思路:
求最短路徑 -> bfs, 用多源bfs 否則會超時

題目一開始沒給定座標
一定要自己走一遍, 遍歷的同時順便入隊

誰要入隊 mat[i][j] == 1 還是 mat[i][j] == 0
1跟0入隊都可以, 但是後面bfs的邏輯會不同
另外要注意, 走過的節點不再走, 所以走過之後要把 mat[i][j] 改 0
選擇0入隊, deque = [[0, 0], [0, 1], [0, 2], [1, 0], [1, 2]]

bfs入隊邏輯
如果 mat[newX][newY] == 1
	mat[curX][curY] == 0
		result
result[newX][newY] += 1
*/
