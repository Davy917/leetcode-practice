/*
前序遍歷看js
中序遍歷看java
後序便利看golang
*/

package main

import (
	"gopractice/datastructure/Tree"
	"math"
)

type TreeNode = Tree.TreeNode

func isValidBST(root *TreeNode) bool {
	return dfs(root, math.MinInt, math.MaxInt)
}

func dfs(root *TreeNode, lower int, upper int) bool {
	if root == nil {
		return true
	}
	isLeftBST := dfs(root.Left, lower, root.Val.(int))
	isRightBST := dfs(root.Right, root.Val.(int), upper)
	if root.Val.(int) <= lower || root.Val.(int) >= upper {
		return false
	}
	return isLeftBST && isRightBST
}
