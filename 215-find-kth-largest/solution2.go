// 堆排序實現
package main

import "fmt"

func heapify(nums []int, size int, parent int) {
	if parent >= size {
		return
	}
	leftChild := parent*2 + 1
	rightChild := parent*2 + 2
	largest := parent
	if leftChild < size && nums[leftChild] > nums[parent] {
		largest = leftChild
	}
	if rightChild < size && nums[rightChild] > nums[largest] {
		largest = rightChild
	}
	if largest != parent {
		nums[largest], nums[parent] = nums[parent], nums[largest]
		heapify(nums, size, largest)
	}
}
func buildHeap(nums []int, size int) {
	lastNode := len(nums) - 1
	lastNodeParent := (lastNode - 1) / 2
	for i := lastNodeParent; i >= 0; i-- {
		heapify(nums, size, i)
	}
	fmt.Println("MaxHeap = ", nums)
}

func findKth(nums []int, k int) (result int) {

	buildHeap(nums, len(nums))
	n := len(nums) - 1 //為了後面代碼的美觀, 這邊先-1

	for i:=0; i<k && n>=0; i++{
		nums[0], nums[n] = nums[n], nums[0]
		result = nums[n]
		heapify(nums, n, 0)
		n--
	}
	return
}
