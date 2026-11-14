/*
第一次練習python
第二次練習go
*/
package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
	"math"
)

type TreeNode = Tree.TreeNode

func closestValue(root *TreeNode, target float64) (ans int) {
	ans = math.MaxInt
	minDiff := math.MaxFloat64
	var dfs func(node *TreeNode) int
	dfs = func(node *TreeNode) int {
		if node == nil {
			return ans
		}
		diff := math.Abs(float64(node.Val.(int)) - target)
		if diff < minDiff || diff == minDiff && node.Val.(int) < ans {
			minDiff = diff
			ans = node.Val.(int)
		}
		if float64(node.Val.(int)) < target {
			return dfs(node.Right)
		}
		return dfs(node.Left)
	}
	return dfs(root)
}

func main() {
	nums := []any{1, nil, 2}
	root := Tree.BuildLevelOrderTree_v2(nums)
	fmt.Println("Ans = ", closestValue(root, 3.428571))
}

//自己寫的
