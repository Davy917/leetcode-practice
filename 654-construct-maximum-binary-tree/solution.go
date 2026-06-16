package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func constructMaximumBinaryTree(nums []int) *TreeNode {
	return buildTree(nums, 0, len(nums)-1)
}
func buildTree(nums []int, l int, r int) *TreeNode {
	if l > r {
		return nil
	}
	rootIndex := l
	maxNum := nums[l]
	for i := l; i <= r; i++ {
		if maxNum < nums[i] {
			maxNum = nums[i]
			rootIndex = i
		}
	}
	fmt.Printf("l = %d, r = %d\n", l, r)
	node := &TreeNode{maxNum, nil, nil}
	node.Left = buildTree(nums, l, rootIndex-1)
	node.Right = buildTree(nums, rootIndex+1, r)
	return node
}
func main() {
	nums := []int{3, 2, 1, 6, 0, 5}
	constructMaximumBinaryTree(nums)
}
