package main

import (
	"fmt"
	"gopractice/datastructure/LinkedList"
	"gopractice/datastructure/Tree"
)

type ListNode = LinkedList.ListNode
type TreeNode = Tree.TreeNode

func isSubPath(head *ListNode, root *TreeNode) bool {
	if root == nil {
		return false
	}
	return isSamePath(head, root) || isSubPath(head, root.Left) || isSubPath(head, root.Right)
}

func isSamePath(head *ListNode, root *TreeNode) bool {
	if head == nil {
		return true
	} else if root == nil {
		return false
	} else if head.Val != root.Val {
		return false
	}
	return isSamePath(head.Next, root.Left) || isSamePath(head.Next, root.Right)
}

func main() {
	head := []int{4, 2, 8}
	root := []any{1, 4, nil, 2, 1, nil, nil, nil, 4, 2, 6, nil, nil, 8, 1, nil, nil, 3, nil, nil, nil}
	headNode := LinkedList.NewLinkedList().BuildLinkedList(head)
	rootNode := Tree.BuildPreorderTree(root)
	fmt.Println(isSubPath(headNode, rootNode))
}

/*
題目給的測資是層序遍歷, 要使用BuildTree方法要先自行轉成前序遍歷preorder

層序遍歷:
[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]

層序展開:
第0层: 1
第1层: 4, 4
第2层: null, 2, 2, null
第3层: 1, null, 6, 8, null, null, null, null
第4层: 1, 3
*/
