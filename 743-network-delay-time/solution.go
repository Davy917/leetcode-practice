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
BFS, DFS, 轉鄰接表

拿紙筆把圖畫出來, 嘗試過BFS, DFS都沒寫出來

寫不出來的原因-->有權圖存在「繞路反而更短」的情形
times = [[1,2,10], [1,3,1], [3,2,1]], n = 3, k = 1

需要使用--->Dijkstra演算法
743-network-delay-time/Dijkstra VS BFS.md

結論
從起點到所有節點的最短路徑的最大值, 同樣都是找最短路徑
BFS用在無權圖
Dijkstra用在有權圖


需要哪些資料結構?
Pair struct{終點, 邊長}
graph: map{起點: {終點, 邊長}}--->鄰接表
h: heap{終點, 邊長}--->最大堆
visited: slice[布林值]

什麼情況會return -1 ?
有一個以上的邊(含), 沒有被走訪--->用visited處理

沒有自環, 但是環形圖的狀況還沒處理--->用visited處理

必做:
在紙上畫出times以及全部的數據是怎麼跑的, 搭配調適器驗證
*/
