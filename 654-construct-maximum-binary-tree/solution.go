package main

import "gopractice/datastructure/Tree"

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
	root := &TreeNode{maxNum, nil, nil}
	root.Left = buildTree(nums, l, rootIndex-1)
	root.Right = buildTree(nums, rootIndex+1, r)
	return root
}
func main() {
	nums := []int{3, 2, 1, 6, 0, 5}
	constructMaximumBinaryTree(nums)
}
