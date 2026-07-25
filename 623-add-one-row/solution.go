/*
BFS看js 自己寫的
DFS看Go 自己寫的
 */
package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)
type TreeNode = Tree.TreeNode
func addOneRow(root *TreeNode, val int, depth int) *TreeNode {
	if depth == 1 {
		newNode := &TreeNode{Val: val}
		newNode.Left = root
		return newNode
	}
	var dfs func (*TreeNode, int) *TreeNode
	dfs = func(root *TreeNode, curDepth int) *TreeNode {
		if root == nil {
			return nil
		}
		if curDepth == depth - 1 {
			nextNode := root.Left
			newNode := &TreeNode{Val: val}
			root.Left = newNode
			newNode.Left = nextNode
		}
		dfs(root.Left, curDepth + 1)
		if curDepth == depth - 1 {
			nextNode := root.Right
			newNode := &TreeNode{Val: val}
			root.Right = newNode
			newNode.Right = nextNode
		}
		dfs(root.Right, curDepth + 1)
		return  nil
	}
	dfs(root, 1)
	return root
}
func main(){
	levelOrder := []any {4, 2, 6, 3, 1, 5}
	root := Tree.BuildLevelOrderTree_v2(levelOrder)
	fmt.Println("Ans = ", addOneRow(root, 1, 2))
}
/*
思路:
前序 + 中序插入新節點

用dfs遍歷整棵樹, 在碰到depth - 1 那層時把,目標節點的左邊給新增好,
因為是dfs所以還會再回到該目標節點一次, 此時就馬上把右邊也新增好, 如此就完成單一節點的新增, 
在遍歷的過程中每次回到 depth - 1 層, 就對當下節點做上述的動作, 循環往復就能達成題目的要求
*/