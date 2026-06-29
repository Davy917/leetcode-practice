package main

import (
	"fmt"
	"gopractice/datastructure/Tree"
)

type TreeNode = Tree.TreeNode

func hasPathSum(root *TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	queue := []*TreeNode{root}
	queueVal := []any{root.Val}
	for len(queue) != 0 {
		curNode := queue[0]
		queue = queue[1:]

		curSum := queueVal[0].(int)
		queueVal = queueVal[1:]

		if curNode.Left == nil && curNode.Right == nil {
			if curSum == targetSum {
				return true
			}
			continue
		}

		if curNode.Left != nil {
			queue = append(queue, curNode.Left)
			queueVal = append(queueVal, curSum+curNode.Left.Val.(int))
		}

		if curNode.Right != nil {
			queue = append(queue, curNode.Right)
			queueVal = append(queueVal, curSum+curNode.Right.Val.(int))
		}
	}
	return false
}
func main() {
	nums := []any{5, 4, 8, 11, nil, 13, 4, 7, 2, nil, nil, nil, 1}
	r := Tree.BuildLevelOrderTree(nums)
	fmt.Println("Ans = ", hasPathSum(r, 22))
}

/*
官解:
BFS
https://leetcode.cn/problems/path-sum/

20, 26, 37 行都使用了類型斷言 xxx.(int) 這是因為 TreeNode 的 Val 用的是 any, 非一般的int
記得在 leetcode 提交的時候不需要這麼做

什麼是類型斷言?
基本語法:
	value := interfaceValue.(Type)        // 形式一：直接断言
	value, ok := interfaceValue.(Type)    // 形式二：带检查的断言

1.直接斷言
var i interface{} = "hello"
s := i.(string)  // s = "hello"

如果類型不匹配, 運行時會panic
var j interface{} = 123
s := j.(string)  // panic: interface conversion: interface {} is int, not string

2.帶檢查的斷言
var i interface{} = 123
if s, ok := i.(string); ok {
    fmt.Println("是字符串:", s)
} else {
    fmt.Println("不是字符串")
}
*/
