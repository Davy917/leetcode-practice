package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func maxDepth(root *TreeNode) int {
	return recMaxDepth(root, 0)
}

func recMaxDepth(root *TreeNode, Depth int) int {
	if root == nil {
		return Depth
	}
	leftDepth := recMaxDepth(root.Left, Depth+1)
	rightDepth := recMaxDepth(root.Right, Depth+1)
	return max(leftDepth, rightDepth)
}
func main() {
	nums := []any{3, 9, 20, nil, nil, 15, 7}
	root := Tree.BuildLevelOrderTree(nums)
	fmt.Println("Ans = ", maxDepth(root))
}

/*
自頂向下:
https://leetcode.cn/leetbook/read/data-structure-binary-tree/xefb4e/
*/
