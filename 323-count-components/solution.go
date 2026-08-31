package main
import "fmt"
func countComponents(n int, edges [][]int) (result int) {
	graph := make([][]int, n)
	for i := 0; i < len(edges); i++ {
		pre := edges[i][0]
		next := edges[i][1]
		graph[pre] = append(graph[pre], next)
		graph[next] = append(graph[next], pre)
	}
	color := make([]int, n)
	for i := 0; i < n; i++ {
		if color[i] == 0 {
			bfs(graph, color, i)
			result++
		}
	}
	return result
}
func bfs(graph [][]int, color []int, cur int){
	deque := []int{}
	deque = append(deque, cur)
	color[cur] = 1
	for len(deque) > 0 {
		cur := deque[0]
		deque = deque[1:]
		temp := graph[cur]
		for _, next := range(temp){
			if color[next] == 0 {
				deque = append(deque, next)
				color[next] = 1
			}
		}
	}
}
func main(){
	edges := [][]int{{0,1}, {1,2}, {1,3}, {3,4}}
	fmt.Println("Ans = ", countComponents(5, edges))
}

/*
leetbook解答:
https://leetcode.cn/leetbook/read/bfs/e6occ6/

看解答寫出來的, 要再自己寫過一遍
BFS看Go
並查集看js, py
*/