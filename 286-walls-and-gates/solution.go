package main

var directions = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}
var m int
var n int

func wallsAndGates(rooms [][]int) {
	m = len(rooms)
	n = len(rooms[0])
	deque := [][]int{}
	inGrid := func(i int, j int) bool {
		return i >= 0 && j >= 0 && i < m && j < n
	}
	bfs := func() {
		path := 0
		for len(deque) > 0 {
			layerSize := len(deque)
			path += 1
			for i := 0; i < layerSize; i++ {
				cur := deque[0]
				deque = deque[1:]
				curX := cur[0]
				curY := cur[1]
				for _, direction := range directions {
					newX := curX + direction[0]
					newY := curY + direction[1]
					if inGrid(newX, newY) && rooms[newX][newY] > rooms[curX][curY] {
						deque = append(deque, []int{newX, newY})
						rooms[newX][newY] = path
					}
				}
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if rooms[i][j] == 0 {
				deque = append(deque, []int{i, j})
			}
		}
	}
	bfs()
}
func main() {
	rooms := [][]int{{2147483647, -1, 0, 2147483647},
		{2147483647, 2147483647, 2147483647, -1},
		{2147483647, -1, 2147483647, -1},
		{0, -1, 2147483647, 2147483647}}
	wallsAndGates(rooms)
}

/*
自己寫的
基本思路:
一定要從門出發才能把沿路上的座標都標上正確長度
如果從空座標出發尋找門, 非常難寫

進階思路:
這題要使用多源bfs, 而不是使用單源bfs

舉例:
  0  INF INF INF
INF   0 INF INF
INF INF   0 INF
INF INF INF   0

上面這張圖用單源bfs, 從第二次進入bfs時(也就是rooms[1, 1])
會把第一次(也就是rooms[0, 0])入隊過的座標再入隊一次, 試想
rooms 放大到變成20 * 20的二維座標, 那這個時候就會因為入隊太多次重覆座標導致out of memory(OOM)

修改方式:
使用多源bfs, 見下方多源bfs vs 單源bfs介紹
286-walls-and-gates\multi bfs vs singly bfs.md
*/
