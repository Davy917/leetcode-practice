package main
import "fmt"
func countComponents(n int, edges [][]int) int {
	graph := make([][]int, n)
	for i := 0; i < len(edges); i++ {
		pre := edges[i][0]
		next := edges[i][1]
		graph[pre] = append(graph[pre], next)
		graph[next] = append(graph[next], pre)
	}
	fmt.Println(graph)
	deque := []int{}
	deque = append(deque, 0)
	// for len(deque) > 0{
	// 	for i := 0; i < len(deque); i++ {
	// 		cur := deque[0]
	// 		deque = deque[:1]
	// 		deque = append(deque, graph[cur])
	// 	}
	// }
	return 0
}
func main(){
	edges := [][]int{{0,1}, {1,2}, {1,3}, {3,4}}
	fmt.Println("Ans = ", countComponents(5, edges))
}