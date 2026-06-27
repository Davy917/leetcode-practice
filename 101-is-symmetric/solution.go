//這版本是拿java版本來改的, 還是鏡像遍歷, 但是改用for迴圈來寫
package main

import "fmt"
import "gopractice/datastructure/Tree"
type TreeNode = Tree.TreeNode
func isSymmetric(root *TreeNode) bool {
	if root == nil{ //理論上不會走到這裡
		return  false
	}
	leftside := leftSide(root.Left)
	rightside := rightSide(root.Right)
	fmt.Println("leftside = ", leftside)
	fmt.Println("rightside = ", rightside)
	
	if len(leftside) != len(rightside){ //要先檢查長度是否相等, 否則會報錯
		return false
	}
	for i := 0; i < len(leftside); i++ {
		if leftside[i] != rightside[i] {
			return false
		}
	}
	return true
}

func leftSide(root *TreeNode) []interface{} {
	if root == nil{
		return []interface{}{}
	}
	var result []interface{}
	queue := []*TreeNode{root}
	for len(queue) > 0 {
		curNode := queue[0]
		queue = queue[1:] // 彈出
		if curNode != nil {
			result = append(result, curNode.Val)
			queue = append(queue, curNode.Left)
			queue = append(queue, curNode.Right)
		} else {
			result = append(result, nil)
		}
	}
	return result
}

func rightSide(root *TreeNode) []interface{} {
	if root == nil{
		return []interface{}{}
	}
	var result []interface{}
	queue := []*TreeNode{root}
	for len(queue) > 0 {
		curNode := queue[0]
		queue = queue[1:] // 彈出
		if curNode != nil {
			result = append(result, curNode.Val)
			queue = append(queue, curNode.Right)
			queue = append(queue, curNode.Left)
		} else {
			result = append(result, nil)
		}
	}
	return result
}
func main(){
	nums := []any {1,0}
	root := Tree.BuildLevelOrderTree(nums)
	fmt.Println("Ans = ", isSymmetric(root))
}
/*
建議先看過102題, 了解層序遍歷是如何運作的
102-level-order

執行代碼
go run ./101-is-symmetric
*/