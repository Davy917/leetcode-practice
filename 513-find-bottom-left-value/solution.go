/*
BFS看js
DFS看Go
*/
package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func findBottomLeftValue(root *TreeNode) (ans int) {
	ans = root.Val.(int)
	maxDepth := 1
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, depth int) int {
		if root == nil {
			return 0
		}
		if depth > maxDepth {
			maxDepth = depth
			ans = root.Val.(int)
		}
		dfs(root.Left, depth+1)
		dfs(root.Right, depth+1)
		return 0
	}
	dfs(root, 1)
	return
}
func main() {
	levelOrder := []any{1, 2, 3, 4, nil, 5, 6, nil, nil, 7}
	root := Tree.BuildLevelOrderTree_v2(levelOrder)
	fmt.Println("Ans = ", findBottomLeftValue(root))
}

/*
靈感來自自頂向下的解決方案
https://leetcode.cn/leetbook/read/data-structure-binary-tree/xefb4e/

手寫遞迴樹
dfs(root, 1)
	dfs(root.Left, 2)
		dfs(root.Left, 3)
			dfs(root.Left, 4)(x)
				return 0
				return 0
		dfs(root.Right, 3)(x)
			return 0
	dfs(root.Right, 2)
		dfs(root.Left, 3)
			dfs(root.Left, 4)
				dfs(root.Left, 5)(x)
					return 0
					return 0
			dfs(root.Right, 4)(x)
				return 0
		dfs(root.Right, 3)
			dfs(root.Left, 4)(x)
				return 0
				return 0
*/
