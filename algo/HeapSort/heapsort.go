package main

import "fmt"

func heapify(tree []int, size int, parent int) {
	if parent >= size{
		return
	}

	leftchild := parent*2+1
	rightchild := parent*2+2
	largest := parent
	if leftchild < size && tree[leftchild] > tree[parent] {
		largest = leftchild
	}
	if rightchild < size && tree[rightchild] > tree[largest] {
		largest = rightchild
	}
	if largest != parent {
		tree[largest], tree[parent] = tree[parent], tree[largest]
		heapify(tree, size, largest)
	}
}

func buildHeap(tree []int, size int) {
	lastNode := len(tree)-1
	lastNodeParent:= (lastNode-1)/2
	for i := lastNodeParent; i >= 0; i-- {
		heapify(tree, size, i)
	}
}

func HeapSort(tree []int, size int) []int{
	buildHeap(tree, size)
	for i := size-1; i >= 0; i-- {
		tree[i], tree[0] = tree[0], tree[i]
		heapify(tree, i, 0)
	}
	fmt.Println("result = ", tree)
	return tree
}

func main(){
	arr := []int{4, 10, 3, 5, 6, 1}
	size := 6
	heapify(arr, size, 0)
	HeapSort(arr, size)
}

/*
執行
go run ./algo/HeapSort
*/