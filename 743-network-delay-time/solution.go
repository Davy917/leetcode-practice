package main

import (
	"container/heap"
	"fmt"
)

func networkDelayTime(times [][]int, n int, k int) int {
	neighbors := make([][]int, n+1)
	edges := make(map[int][]int)
	visited := make([]bool, n+1)
	notVisited := len(times)
	fmt.Println(visited) //debug
	for _, time := range times {
		start := time[0]
		end := time[1]
		duration := time[2]
		neighbors[start] = append(neighbors[start], end)
		edges[end] = append(edges[end], duration)
	}
	fmt.Println("edges = ", edges) //debug
	fmt.Println(neighbors)         //debug
	h := &IntHeap{}
	curLen := 0
	var dfs func(cur int)
	dfs = func(cur int) {
		visited[cur] = true
		for _, next := range neighbors[cur] {
			notVisited--
			if visited[next] == true {
				return
			}
			curLen += edges[next][0]
			edges[next] = edges[next][1:]
			heap.Push(h, curLen)
			dfs(next)
		}
		curLen = 0
	}
	dfs(k)
	fmt.Println(h)
	if notVisited == 0 {
		return heap.Pop(h).(int)
	}
	return -1
}

func networkDelayTime2(times [][]int, n int, k int) int {
	neighbors := make([][]int, n+1)
	edges := make(map[int][]int)
	visited := make([]bool, n+1)
	fmt.Println(visited) //debug
	for _, time := range times {
		start := time[0]
		end := time[1]
		duration := time[2]
		neighbors[start] = append(neighbors[start], end)
		edges[end] = append(edges[end], duration)
	}
	fmt.Println("edges = ", edges) //debug
	fmt.Println(neighbors)         //debug
	h := &IntHeap{}
	curLen := 0
	var bfs func(cur int)
	bfs = func(cur int) {
		deque := []int{cur}
		for len(deque) > 0 {
			visited[cur] = true
			cur := deque[0]
			deque = deque[1:]
			layerSize := len(neighbors[cur])
			for i := 0; i < layerSize; i++ {
				next := neighbors[cur][i]
				if visited[next] == false {
					deque = append(deque, next)
					visited[next] = true
					curLen = edges[next][0]
					edges[next] = edges[next][1:]
					heap.Push(h, curLen)
				}
			}
		}
	}
	bfs(k)
	fmt.Println(h)
	for i := 1; i < len(visited); i++ {
		if visited[i] == false {
			return -1
		}
	}
	return heap.Pop(h).(int)
}

type IntHeap []int

func (h IntHeap) Len() int            { return len(h) }
func (h IntHeap) Less(i, j int) bool  { return h[i] > h[j] } // < 為最小堆, > 為最大堆1
func (h IntHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) { *h = append(*h, x.(int)) }
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func main() {
	times := [][]int{{2, 1, 10}, {2, 3, 20}, {3, 4, 30}} //bfs會出錯
	fmt.Println("Ans = ", networkDelayTime(times, 4, 2))
	// times_2 := [][]int{{1, 2, 1}, {2, 3, 2}, {1, 3, 2}} //dfs會出錯
	// fmt.Println("Ans = ", networkDelayTime2(times_2, 3, 1))
}

/*
提示:
1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n 就是1-base
0 <= wi <= 100
不含重複邊, 不存在自環

times本質是什麼?
有權有向圖
聯想到什麼?
BFS, DFS

轉鄰接表(1-base)
[], [], [1, 3], [4]

因為
2---1, 花10分鐘
2---3, 花20分鐘

所以
可知, 2---3完成的時候, 2---1 這條訊號一定也完成了

結論
我們只需統計整張圖最長路徑, 並返回

可能會使用:
DFS, 搭配優先隊列

DFS要做什麼?
1.遍歷整張圖
2.統計每條邊的長度, 加入堆中

優先隊列要怎麼做?
最大堆, 存放路徑長度總和

需要用一個map來輔助, {邊: 邊長}

什麼情況會return -1
有一個以上的邊(含), 沒有被走訪--->用notVisited統計

沒有自環, 但是環形圖的狀況還沒處理--->用Visited處理
*/
