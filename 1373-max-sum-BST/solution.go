package main

import (
	"gopractice/datastructure/Tree"
	"math"
)

type TreeNode = Tree.TreeNode

type subTree struct {
	IsBST  bool
	MinVal int
	MaxVal int
	Sum    int
}

var maxSum int

func maxSumBST(root *TreeNode) int {
	maxSum = 0
	dfs(root)
	return maxSum
}

func maxInt(max int, cur int) int {
	if cur > max {
		return cur
	}
	return max
}

func minInt(min int, cur int) int {
	if cur < min {
		return cur
	}
	return min
}

func dfs(root *TreeNode) *subTree {
	if root == nil {
		return &subTree{true, math.MaxInt, math.MinInt, 0}
	}
	left := dfs(root.Left)
	right := dfs(root.Right)
	if left.IsBST && right.IsBST && root.Val.(int) > left.MaxVal && root.Val.(int) < right.MinVal {
		curSum := left.Sum + right.Sum + root.Val.(int)
		maxSum = maxInt(maxSum, curSum)
		lower := minInt(root.Val.(int), left.MinVal)
		upper := maxInt(root.Val.(int), right.MaxVal)
		return &subTree{true, lower, upper, curSum}
	}
	return &subTree{false, 0, 0, 0}
}

/*
自己寫不出來, 看官解後自行理解
官解:
https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/solutions/2276045/er-cha-sou-suo-zi-shu-de-zui-da-jian-zhi-lii4/

必需滿足:
左子樹為BST
右子樹為BST
左子樹最大 < 當前值 < 右子樹最小

lower代表下界--->找左子樹中的最大
upper代表上界--->找右子樹中的最小

相當於由後往前推倒整顆BST的上下界
*/
