package main
import "fmt"
func maxAreaOfIsland(grid [][]int) int {
	rows := len(grid) //有幾排橫的
	cols := len(grid[0]) //有幾排直的
	color := make([][]int, len(grid))

	for i := 0; i < rows; i++{ //建二維陣列color, 來記錄是否被遍歷
		color[i] = make([]int, cols)
	}

	maxArea := 0
	for i := 0; i < rows; i++{
		for j := 0; j < cols; j++{
			if grid[i][j] == 1 && color[i][j] == 0{ //是陸地, 且該陸地還未被遍歷
				curArea := bfs(grid, i, j, rows, cols, color)
				maxArea = max(curArea, maxArea)
			}
		}
	}
	return maxArea
}
/**
	* @param grid    二维网格
	* @param i       横坐标
	* @param j       纵坐标
	* @param rows    二维网格的行数
	* @param cols    二维网格的列数
	* @param color 标记是否访问过
	* @return 当前连通分量里结点的个数
	*/

var directions = [][]int {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func bfs(grid [][]int, i int, j int, rows int, cols int, color [][]int) int {
	count := 0
	queue := make([][]int, 0)
	queue = append(queue, []int{i, j})
	fmt.Println(queue)
	for len(queue) > 0 {
		cur := queue[0]
		queue = queue[:1]
		curX := cur[0]
		curY := cur[1]
		count++
		for _, direction := range(directions){
			newX := curX + direction[0]
			newY := curY + direction[1]
			if inArea(newX, newY, rows, cols) && grid[newX][newY] == 1 && color[newX][newY] == 0 {
				queue = append(queue, []int{newX, newY})
				color[newX][newY] = 1
			}
		}
	}
	return count
}
func inArea(i int, j int, rows int, cols int) bool {
	return i >= 0 && i < rows && j >= 0 && j < cols
}
func main(){
    grid := [][]int{{0,0,1,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,1,1,1,0,0,0},
					{0,1,1,0,1,0,0,0,0,0,0,0,0},
					{0,1,0,0,1,1,0,0,1,0,1,0,0},
					{0,1,0,0,1,1,0,0,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,0,0,0,0,0,1,1,1,0,0,0},
					{0,0,0,0,0,0,0,1,1,0,0,0,0}}
	fmt.Println("Ans = ", maxAreaOfIsland(grid))
}