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
自己寫的
思路:
求最短路徑 -> bfs, 要用多源bfs 否則會超時

題目一開始沒給定座標
一定要自己走一遍, 遍歷的同時順便入隊

Q:誰要入隊 0 還是 1 ??
A:mat[i][j] == 0, 原因看延伸討論
0全部入隊之後, 多源bfs, 擴散出去每一層都+1, 走過的不再走
要注意, 走過的節點不再走, 所以走過之後要把 mat[i][j] 改 0

bfs入隊邏輯
如果在範圍內且mat[newX][newY] == 1
	把mat[newX][newY]入隊 & mat [newX][newY] 改成 0
	result[newX][newY] = result[curX][curY] + 1

注意 bfs的入隊邏輯是把值為1的入隊, 也就是從 0 走到 1
這邊的語意--->把終點當成起點去反推距離, 每次都把距離+1不斷擴散, 直到整張圖都遍歷過


延伸討論:
如果反過來從 1 走到 0 會有什麼問題?

看此例:
01111
11111
11111

從最右下角那個1, 走到0, 距離為6, 之後要把 6 寫進去result, 我們就需要紀錄座標才能把6正確填到result中
如果題目再複雜一點代碼就會變得很難維護, 很容易出錯

從 0 走到 1, 每次往外擴散一次就把當下result + 1, 走到最右下角那一格自然就會是6
*/
