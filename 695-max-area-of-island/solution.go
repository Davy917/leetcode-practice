/*
bfs 看 Go (使用二維座標)
dfs 看 java
 */
package main

import "fmt"

var (
	rows int
	cols int
)

func maxAreaOfIsland(grid [][]int) int {
	rows = len(grid)    //有幾排橫的
	cols = len(grid[0]) //有幾排直的
	color := make([][]int, len(grid))

	for i := 0; i < rows; i++ { //建二維陣列color, 來記錄是否被遍歷
		color[i] = make([]int, cols)
	}

	maxArea := 0
	for i := 0; i < rows; i++ {
		for j := 0; j < cols; j++ {
			if grid[i][j] == 1 && color[i][j] == 0 { //是陸地, 且該陸地還未被遍歷
				curArea := bfs(grid, color, i, j)
				maxArea = max(curArea, maxArea)
			}
		}
	}
	return maxArea
}

/**
* @param grid    二維網格
* @param i       橫座標
* @param j       縱座標
* @param rows    grid的總行數
* @param cols    grid的總列數
* @param color 標記是否訪問過
* @return 當前連通分量的個數
							上		下		左		右
*/
var directions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func bfs(grid [][]int, color [][]int, i int, j int) int {
	count := 0
	// color[i][j] = 1 //初始節點已訪問, 這行容易被忽略
	queue := [][]int{}
	queue = append(queue, []int{i, j})
	fmt.Println("queue before = ", queue)
	for len(queue) > 0 {
		cur := queue[0]
		queue = queue[1:]
		curX := cur[0]
		curY := cur[1]
		count++                                                             //應該在「節點被取出隊列時」執行，而不是在「檢查其鄰居時」執行。
		fmt.Printf("curX = %d, curY = %d, count = %d\n", curX, curY, count) //把當前座標給標出來
		for _, direction := range directions {
			newX := curX + direction[0]
			newY := curY + direction[1]
			fmt.Printf("newX = %d, newY = %d\n", newX, newY)                           //依次檢查cur的左右前後座標
			if inGrid(newX, newY) && grid[newX][newY] == 1 && color[newX][newY] == 0 { //確認還在grid中, 且該座標是土地, 且沒被訪問過
				queue = append(queue, []int{newX, newY}) //將此座標加入queue
				color[newX][newY] = 1                    //訪問過了, 標記為1
				fmt.Println("queue after = ", queue)
			}
		}
	}
	return count
}

/*
inGrid用來檢測newX, newY 是否還在grid的範圍中
rows 以及 cols 在這邊擔任邊界的腳色,如果i, j超出邊界就return False
*/
func inGrid(i int, j int) bool {
	return i >= 0 && i < rows && j >= 0 && j < cols
}
func main() {
	grid := [][]int{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
		{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
		{0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}
	fmt.Println("Ans = ", maxAreaOfIsland(grid))
}

/*
Leetbook 二維座標 BFS 解答:
https://leetcode.cn/leetbook/read/bfs/e60fld/

一維座標法, 可以再找時間練習

相似題:
200-num-Islands/solution.js
*/
