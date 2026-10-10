package main

import (
	"container/heap"
	"fmt"
	"sort"
)

func minMeetingRooms(intervals [][]int) int {
	m := make(map[int][]int)
	n := len(intervals)
	temp := make([]int, n)
	for index, time := range intervals {
		start := time[0]
		end := time[1]
		m[start] = append(m[start], end)
		temp[index] = start
	}
	sort.Ints(temp)
	for i, v := range temp {
		toAdd := m[v][0]
		m[v] = m[v][1:]
		intervals[i][0] = v
		intervals[i][1] = toAdd
	}
	fmt.Println(intervals) //debug
	rooms := 1
	h := &IntHeap{intervals[0][1]}
	for i := 1; i < len(intervals); i++ {
		curStartTime := intervals[i][0]
		curEndTime := intervals[i][1]
		earliestEnd := (*h)[0]
		if curStartTime < earliestEnd { //無空會議室
			rooms++
			heap.Push(h, curEndTime)
		} else { //有
			heap.Pop(h)
			heap.Push(h, curEndTime)
		}
	}
	return rooms
}

type IntHeap []int

func (h IntHeap) Len() int            { return len(h) }
func (h IntHeap) Less(i, j int) bool  { return h[i] < h[j] } // < 為最小堆, > 為最大堆
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
	// intervals := [][]int{{0, 30}, {5, 10}, {15, 20}}
	intervals := [][]int{{9, 10}, {4, 9}, {4, 17}}
	// intervals := [][]int{{7, 10}, {2, 4}}
	fmt.Println("Ans = ", minMeetingRooms(intervals))
}

/*
[0, 30]新開一間會議室
[5, 10]第一間會議室還沒空, 新開一間會議室
[15, 20]第二間會議室已空

每次必做:
檢查有無空會議室
有 -> 占用
無 -> 新增

關鍵思路:
檢查有無空會議室--->當前會議開始時間 vs 最早結束的會議的時間

優先隊列, 會議室數量
[30], rooms = 1
[10, 30], rooms = 2
[20, 30], rooms = 2

再看這組 intervals = [[7,10],[2,4]]
用map存成[7:10, 2:4], 用鍵的大小排列
再存成 intervals = [[2,4], [7,10]]

優先隊列
[4], rooms = 1
[10], rooms = 1
*/
