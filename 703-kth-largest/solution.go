package main

import "container/heap"

type KthLargest struct {
	arr []int
	k   int
}

func Constructor(k int, nums []int) KthLargest {
	kl := KthLargest{arr: []int{}, k: k}
	for _, val := range nums {
		kl.Add(val)
	}
	return kl
}

func (kl *KthLargest) Len() int {
	return len(kl.arr)
}

func (kl *KthLargest) Less(i, j int) bool {
	return kl.arr[i] < kl.arr[j]
}

func (kl *KthLargest) Swap(i, j int) {
	kl.arr[i], kl.arr[j] = kl.arr[j], kl.arr[i]
}

func (kl *KthLargest) Push(val interface{}) {
	kl.arr = append(kl.arr, val.(int))
}

func (kl *KthLargest) Pop() interface{} {
	origin := kl.arr
	n := len(origin)
	val := kl.arr[n-1]       //取末位值
	kl.arr = origin[0 : n-1] //剔除末位
	return val
}

func (kl *KthLargest) Add(val int) int {
	heap.Push(kl, val)
	for kl.Len() > kl.k {
		heap.Pop(kl)
	}
	return kl.arr[0]
}

/*
sort 套件（sort.go）
└── Interface
    ├── Len()
    ├── Less()
    └── Swap()

container/heap 套件（heap.go）
└── Interface
    ├── sort.Interface   ← 嵌入上面的，繼承 3 個方法
    ├── Push()           ← heap 自己新增的
    └── Pop()            ← heap 自己新增的

必讀:
703-kth-largest\Interface and PriorityQueue deep dive.md
*/
