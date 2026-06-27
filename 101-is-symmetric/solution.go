// 這版本是拿java版本來改的, 還是鏡像遍歷, 但是改用for迴圈來寫
package main

import "fmt"
import "gopractice/datastructure/Tree"

type TreeNode = Tree.TreeNode

func isSymmetric(root *TreeNode) bool {
	if root == nil { //理論上不會走到這裡
		return false
	}
	Lefts := leftSide(root.Left)
	Rights := rightSide(root.Right)
	fmt.Println("Lefts = ", Lefts)
	fmt.Println("Rights = ", Rights)

	if len(Lefts) != len(Rights) { //要先檢查長度是否相等, 否則會報錯
		return false
	}
	for i := 0; i < len(Lefts); i++ {
		if Lefts[i] != Rights[i] {
			return false
		}
	}
	return true
}

func leftSide(root *TreeNode) []interface{} {
	if root == nil {
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
	if root == nil {
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
func main() {
	//nums := []any{1, 0}
	//root := Tree.BuildLevelOrderTree(nums)
	//fmt.Println("Ans = ", isSymmetric(root))
	nums2 := []any{1, 2, 2, 3, 4, 4, 3}
	root2 := Tree.BuildLevelOrderTree(nums2)
	fmt.Println("Ans = ", isSymmetric_v2(root2))
}

/*
建議先看過102題, 了解層序遍歷是如何運作的
102-level-order

執行代碼
go run ./101-is-symmetric

interface教學
LanguagePractice/GoPractice/interfacePractice/interfacePractice.go

golang deque說明
datastructure/deque/deque-practice-list.md

[FAQ]:
[]interface{} vs []any
本质相同：
	any 是 interface{} 的类型别名（从 Go 1.18 开始引入）
	[]any 和 []interface{} 完全等价，编译后生成相同的代码

Go 1.18 之前只能写：
var list []interface{}

Go 1.18+ 可以写（更简洁）：
var list []any

如果使用 Go 1.18+，推荐用 []any（代码更简洁易读）
如果需要兼容旧版本，用 []interface{}
当前代码用 []interface{} 完全正确，只是语法风格上的选择。
*/
