/*
BFS看js 自己寫的
DFS看Go 官解
*/
package main

import "gopractice/datastructure/Tree"

type TreeNode = Tree.TreeNode

func largestValues(root *TreeNode) []int {
	var ans []int
	if root == nil {
		return ans
	}
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, depth int) int {
		if root == nil {
			return 0
		}
		if depth == len(ans) { //檢查是不是第一次到這層
			ans = append(ans, root.Val.(int)) //是就直接塞值
		} else {
			levelMaxNum := max(root.Val.(int), ans[depth]) //不是的話, 就跟之前塞的值比大小
			ans[depth] = levelMaxNum
		}
		dfs(root.Left, depth+1)
		dfs(root.Right, depth+1)
		return 0
	}
	dfs(root, 0) //這題, 第一層是0, 因為 if depth == len(ans) 比較順暢
	return ans
}

/*
17 ~ 22行自己想不到, 看官解才寫出來
https://leetcode.cn/problems/find-largest-value-in-each-tree-row/solutions/1619294/zai-mei-ge-shu-xing-zhong-zhao-zui-da-zh-6xbs/
*/
