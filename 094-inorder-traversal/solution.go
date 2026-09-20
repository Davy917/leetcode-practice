/*
中序遍歷, 迭代版
*/
package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func inorderTraversal(root *TreeNode) (result []int) {
	stack := make([]*TreeNode, 0)
	cur := root
	for cur != nil || len(stack) > 0 {
		for cur != nil {
			stack = append(stack, cur)
			cur = cur.Left
		}
		//出棧 + 塞result
		n := len(stack)
		cur = stack[n-1]
		stack = stack[:n-1]
		result = append(result, cur.Val.(int))

		cur = cur.Right
		fmt.Printf("stack = %v\n result = %v,", stack, result)
	}
	return
}
func main() {
	array := []any{1, 2, nil, 3, 5, nil, nil, 4}
	root := Tree.BuildLevelOrderTree_v2(array)
	inorderTraversal(root)
}

/*
左根右

stack = [1], result = []
stack = [], result = [1]
stack = [2], result = [1]
stack = [2, 3], result = [1]
stack = [2], result = [1, 3]
stack = [], result = [1, 3, 2]

用標準的迭代中序（先往左一路推入 stack，然後逐個 pop 並處理，最後移到右子樹）。
*/
