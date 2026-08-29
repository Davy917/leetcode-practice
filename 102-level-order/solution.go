package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)
type TreeNode = Tree.TreeNode
func levelOrder(root *TreeNode) (result [][]int) {
	if root == nil{
		return  result
	}
	deque := [] *TreeNode{}
	deque = append(deque, root)
	for len(deque) > 0 {
		layerSize := len(deque)
		var curLayer []int
		for i := 0; i < layerSize; i++{
			curNode := deque[0]
			deque = deque[1:]
			if curNode.Left != nil{
				deque = append(deque, curNode.Left)
			}
			if curNode.Right != nil{
				deque = append(deque, curNode.Right)
			}
			curLayer = append(curLayer, curNode.Val.(int))
		}
		result = append(result, curLayer)
	}
	return
}
func main(){
	root := []any {3, 9, 20, nil, nil, 15, 7}
	x := Tree.BuildLevelOrderTree_v2(root)
	fmt.Println("Ans = ", levelOrder(x))
}