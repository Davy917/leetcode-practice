package main

import "gopractice/datastructure/Tree"
import "fmt"

type TreeNode = Tree.TreeNode

func isSameTree(p *TreeNode, q *TreeNode) bool {

	if p == nil && q == nil {
		return true
	} else if p == nil || q == nil {
		return false
	} else if p.Val != q.Val {
		return false
	} else {
		return isSameTree(p.Left, q.Left) && isSameTree(p.Right, q.Right)
	}
}
func main() {
	p := []any{10, 5, 15}
	q := []any{10, 5, nil, nil, 15}
	pNode := Tree.BuildPreorderTree(p)
	qNode := Tree.BuildPreorderTree(q)
	fmt.Println("Ans = ", isSameTree(pNode, qNode))
}
