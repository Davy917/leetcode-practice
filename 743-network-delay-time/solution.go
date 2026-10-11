package main

import (
	"container/heap"
	"fmt"
)

type Pair struct {
	end, dist int
}

func networkDelayTime(times [][]int, n int, k int) int {
	graph := make(map[int][]Pair)
	visited := make([]bool, n+1)
	for _, time := range times {
		start := time[0]
		var neighbor Pair = Pair{
			end:  time[1],
			dist: time[2],
		}
		graph[start] = append(graph[start], neighbor)
	}
	fmt.Println(graph) //debug
	h := &IntHeap{}
	maxVal := -2
	var bfs func(cur int)
	bfs = func(cur int) {
		heap.Push(h, Pair{cur, 0})
		for h.Len() > 0 {
			var cur Pair = heap.Pop(h).(Pair)
			if visited[cur.end] {
				continue
			}
			visited[cur.end] = true //注意visited更新的時間
			maxVal = max(maxVal, cur.dist)
			for j := 0; j < len(graph[cur.end]); j++ {
				var next Pair = Pair{
					end:  graph[cur.end][j].end,
					dist: graph[cur.end][j].dist + cur.dist,
				}
				heap.Push(h, next)
				fmt.Print(cur.end, cur.dist)
			}
		}
	}
	bfs(k)
	for i := 1; i < len(visited); i++ {
		if visited[i] == false {
			return -1
		}
	}
	return maxVal
}

type IntHeap []Pair

func (h IntHeap) Len() int            { return len(h) }
func (h IntHeap) Less(i, j int) bool  { return h[i].dist < h[j].dist } // < 為最小堆, > 為最大堆1
func (h IntHeap) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) { *h = append(*h, x.(Pair)) }
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func main() {
	times := [][]int{{1, 2, 1}, {1, 3, 2}, {2, 4, 5}, {3, 5, 3}} //dfs會出錯
	fmt.Println("Ans = ", networkDelayTime(times, 5, 1))
}

/*
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
從起點到所有節點的最短路徑的最大值

可能會使用:

BFS要做什麼?

優先隊列要怎麼做?

為什麼一般的BFS, DFS處理不了這個問題?

需要用那些資料結構?

什麼情況會return -1 ?
有一個以上的邊(含), 沒有被走訪--->用visited處理

沒有自環, 但是環形圖的狀況還沒處理--->用visited處理
*/
