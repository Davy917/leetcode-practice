package main

import "fmt"

var directions [][]int = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
var rows int
var cols int

func numIslands(grid [][]byte) int {
	count := 0
	rows = len(grid)
	cols = len(grid[0])
	inGrid := func(i int, j int) bool {
		return i >= 0 && j >= 0 && i < rows && j < cols
	}
	var dfs func(i int, j int)
	dfs = func(i int, j int) {
		grid[i][j] = '0'
		for _, direction := range directions {
			newX := i + direction[0]
			newY := j + direction[1]
			if inGrid(newX, newY) && grid[newX][newY] == '1' {
				dfs(newX, newY)
			}
		}
	}
	for i := 0; i < rows; i++ {
		for j := 0; j < cols; j++ {
			if grid[i][j] == '1' {
				dfs(i, j)
				count++
			}
		}
	}
	return count
}
func main() {
	grid := [][]byte{
		{'1', '1', '1', '1', '0'},
		{'1', '1', '0', '1', '0'},
		{'1', '1', '0', '0', '0'},
		{'0', '0', '0', '1', '0'},
	}
	fmt.Println("Ans = ", numIslands(grid))
}

/*
自己寫的

走過的地方就不應該再走, 如果是'1'的話, 直接改成'0'
從當前座標出發, 遍歷四個方向的所有座標, 在grid範圍內值為'1'的座標才值得被遍歷
如果一個座標的上下左右都被遍歷過代表這個島嶼已經被我們用dfs走完了, 就可以返回了

bfs看js
dfs看go
*/
